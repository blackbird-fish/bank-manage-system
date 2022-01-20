package task9.CS;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ATMServer {
    public static void main(String[] args) {
		ServerSocket ss=null ;
		Socket socket=null ;
		try {
			ss = new ServerSocket(9000) ;
			System.out.println("服务器启动");
			while(true){
				socket = ss.accept() ;
				System.out.println("连接成功"+socket.getInetAddress());
				new SeverThread(socket) ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(socket != null){
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
    
}
