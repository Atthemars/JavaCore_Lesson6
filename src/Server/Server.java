package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Server server  = new Server();
    }
    public Server(){
        ServerSocket server = null;
        Socket socket = null;
        try{
            server = new ServerSocket(3443);
            System.out.println("Serever  is running");
            socket = server.accept();
            System.out.println("New Client");
            //incoming stream
            Scanner in = new Scanner(socket.getInputStream());
            //outgoing
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            //sending msg from sewrver
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        System.out.println("Write ur message");
                        String msg = sc.nextLine();
                        System.out.println("Msg was sent");
                        out.println(msg);
                    }
                }
            }).start();
            //getting msg in main stream & sending echo
            while (true){
                String msg = in.nextLine();
                if (msg.equals("/end")) break;
                System.out.println("Client: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                server.close();
                System.out.println("Server is closed");
            } catch (IOException e){
                e.printStackTrace();
        }
        }
    }
}
