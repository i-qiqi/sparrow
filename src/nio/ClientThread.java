package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

public class ClientThread extends Thread{

    private CharsetDecoder decoder = Charset.forName("GBK").newDecoder();

    private CharsetEncoder encoder = Charset.forName("GBK").newEncoder();

    private Selector selector;

    private SocketChannel socket;

    private SelectionKey clientKey;

    private String userName;

    public ClientThread(String userName) {
        try {
            selector = Selector.open();

            socket = SocketChannel.open();
            socket.configureBlocking(false);

            clientKey = socket.register(selector, SelectionKey.OP_CONNECT);
            InetSocketAddress ip = new InetSocketAddress("localhost", 8080);

            socket.connect(ip);

            this.userName = userName;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            while(true) {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isConnectable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        if(channel.isConnectionPending()){
                            channel.finishConnect();
                        }
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Connect to server success.");

                        //send
                    } else if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();

                        // channel 是可读的，从channel中读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        buffer.flip();

                        String payload = decoder.decode(buffer).toString();

                        String[] arr = payload.split("\\s*:\\s*");

                        System.out.println(arr[0] + " : " + arr[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    public void send(String msg){
        try {
            SocketChannel client = (SocketChannel) clientKey.channel();
            client.write(encoder.encode(CharBuffer.wrap(msg)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            selector.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
