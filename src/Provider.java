import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Provider{
	ServerSocket providerSocket;
	Socket connection = null;
	ExecutorService executor;
	Provider(){}
	void run()
	{
		try{
			ExecutorService executor = Executors.newFixedThreadPool(10);
			providerSocket = new ServerSocket(2004, 10);
			while(true){
				//1. creating a server socket
				
				//2. Wait for connection
				System.out.println("Waiting for connection");
				connection = providerSocket.accept();
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());
				executor.execute(new ConnectionHandler(connection));
				//4. The two parts communicate via the input and output streams
			}
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		Provider server = new Provider();
		while(true){
			server.run();
		}
	}
}
