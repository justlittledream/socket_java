package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*
 @version：
    date：2020/09/01
    description: 能够实现双向通信，客户端输入exit后，断开连接，服务器仍然保持连接
    存在问题，必须客户端先发送消息，且无法连续发送消息。另外，只能一个客户端进行连接（通过线程解决）。
*/
public class Server {
    private static final int PORT = 8900;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        new Server().service();
    }

    public Server() throws IOException{
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("服务器启动，监听端口号为：" + PORT);
    }

//    public  String echo(String msg){
//        return "echo: " + msg;
//    }

    public void service() throws IOException {
        Socket socket = null;
        //boolean end = true;
        while (true){
            socket = serverSocket.accept();
            //System.out.print("开始监听：");
            System.out.println("New connection" + " accepted" + socket.getInetAddress() +
                    ": " + socket.getPort());
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
            BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String str = socketIn.readLine();
                if(str.equals("exit")){
                    System.out.println("client 断开了连接,等待新的client链接" );
                    break;
                }
                System.out.println("client:" + str);
                String send = systemIn.readLine();
//                if(send.equals("end")){
//                    System.out.println("??????");
//                    end = false;
//                    break;
//                }
                socketOut.println(send);
                //out.println("server has received.");
                //socketOut.println(str);
                socketOut.flush();
            }
            socket.close();
            socketIn.close();
            socketOut.close();
            socketIn.close();
            //break;
        }
    }
}
