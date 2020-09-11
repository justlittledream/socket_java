package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

//为了测试branch进行了修改
public class Client {
    private static final int PORT = 8900;
    private static String host = "10.190.180.160";
    private static Socket server;
    //private static String name = "client";

    public Client(){
        try {
            this.server = new Socket(host, PORT);
            System.out.println("客户端连接成功");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {

        new Client();
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter socketOut = new PrintWriter(server.getOutputStream());
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("");
        while (true){
            String str = systemIn.readLine();
            socketOut.println(str);
            socketOut.flush();
            if(str.equals("exit")){
                System.out.println("你已经断开了连接");
                break;
            }

            System.out.println(socketIn.readLine());
        }
        server.close();
        socketIn.close();
        socketOut.close();
        socketIn.close();
    }
}
