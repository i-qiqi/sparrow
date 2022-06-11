package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class NioServer {
    Map<String, SocketChannel> memberChannels;

    private static final int PORT = 8080;

    private Selector selector;

    private ServerSocketChannel server;

    private ByteBuffer buffer;

    public NioServer() throws IOException {
        this.selector = Selector.open();
        this.server = getServerChannel(selector);

        this.buffer = ByteBuffer.allocate(1024);

        memberChannels = new ConcurrentHashMap<>();
    }

    private ServerSocketChannel getServerChannel(Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

        return serverSocketChannel;
    }

    public void listen() {
        System.out.println("Server starting ...");

        while (!Thread.currentThread().isInterrupted()) {
            try {

                int count = selector.select();

                if (count == 0) {
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }

                Set<SelectionKey> keySet = selector.selectedKeys();

                Iterator<SelectionKey> iterator = keySet.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    iterator.remove();

                    key.interestOps();

                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();

                        SocketChannel channel = server.accept();

                        channel.configureBlocking(false);

                        channel.register(selector, SelectionKey.OP_READ);

                        System.out.println("Client connection : " + channel.socket().getInetAddress().getHostName()
                                + ": " + channel.socket().getPort());

                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        String payload = receiveMsg(channel);
                        if (payload != null && !"".equals(payload)) {
                            String[] arr = payload.split("\\s*:\\s*");
                            String cmd = arr[0];
                            switch (cmd) {
                                case "SIGN_IN":
                                    signIn(arr[1], channel);
                                    break;
                                case "SIGN_OUT":
                                    signOut(arr[1]);
                                    break;
                                case "IM":
                                    doInstantMessaging(arr[1], arr[2], arr[3]);
                                    break;
                                default:
                                    System.out.println("ignore msg");
                                    break;
                            }
                        }

                    }
                }


            } catch (IOException | InterruptedException e) {
                System.out.println("Server startup failed...");
                e.printStackTrace();
            }
        }
        close();
    }

    private String receiveMsg(SocketChannel channel) {
        try {
            CharsetDecoder decoder = Charset.forName("GBK").newDecoder();

            buffer.clear();
            int len = channel.read(buffer);

            if(len == -1) {
                channel.close();
                System.out.println("chanel close : " + channel.socket().getInetAddress().getHostName()
                        + ": " + channel.socket().getPort());
            }
            buffer.flip();

            String msg = decoder.decode(buffer).toString();

            return msg;
        } catch (IOException e) {
            signOut(channel);
            e.printStackTrace();
        }

        return null;
    }

    private void signIn(String userName, SocketChannel channel) {
        if (userName == null || "".equals(userName)) {
            System.out.println("userName can not be blank.");
        }
        System.out.println("USER : " + userName + " online.");
        memberChannels.put(userName, channel);
        System.out.println("在线人数: " + memberChannels.size());
    }

    private void signOut(String userName) {
        memberChannels.remove(userName);
        System.out.println("USER : " + userName + " offline.");
        System.out.println("在线人数: " + memberChannels.size());
    }

    private void signOut(SocketChannel channel) {
        try {
            memberChannels.forEach((k, v) -> {
                if (channel == v) {
                    memberChannels.remove(k);
                    System.out.println("USER : " + k + " offline.");
                }
            });
            System.out.println("在线人数: " + memberChannels.size());
            channel.close();
            System.out.println("chanel close : " + channel.socket().getInetAddress().getHostName()
                    + ": " + channel.socket().getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doInstantMessaging(String from, String to, String receivedMsg) throws IOException {


        System.out.println(from + " Send to " + to + " msg : " + receivedMsg);

        if (from.equals(to)) {
            System.out.println("ignore msg, receiver is yourself !");
            return;
        }

        if (memberChannels.containsKey(to)) {
            CharsetEncoder encoder = Charset.forName("GBK").newEncoder();
            memberChannels.get(to).write(encoder.encode(CharBuffer.wrap(from + " : " + receivedMsg)));
        }
    }


    public void close() {
        try {
            selector.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        new NioServer().listen();
    }
}
