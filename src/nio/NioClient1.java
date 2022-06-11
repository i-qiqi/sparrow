package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NioClient1 {
    public static void main(String[] args) {
        String userName = "christopher";

        ClientThread client = new ClientThread(userName);
        client.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        try {
            String readline;
            System.out.println("请输入用户ID:");
            userName = reader.readLine();
            if (userName == null || "".equals(userName)) {
                System.out.println("userName can not be blank.");
            }
            client.send("SIGN_IN:" + userName);
            while ((readline = reader.readLine()) != null) {
                if (readline.equals("bye")) {
                    client.close();
                    System.exit(0);
                }

                client.send("IM:" + userName + ":" + readline);
            }
        } catch (IOException e) {
            client.send("SIGN_OUT:" + userName);
            e.printStackTrace();
        }
    }

    public static void main1(String[] args) {
        String msg = "1 :2:3 4";
        String[] arr = msg.split("\\s*:\\s*");

        for (String s : arr) {
            System.out.println(s);
        }
    }
}
