import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


public class Agent implements Runnable {

	private Vector<Constraint> _constraints;
	private int[] _domain;
	ServerSocket providerSocket;
	Socket connection = null;
	ExecutorService executor;
	
	public static Agent CreateAgent(int[] domain)
	{
		Agent newAgent = null;
		try{
			ExecutorService executor = Executors.newFixedThreadPool(10);
			ServerSocket providerSocket = new ServerSocket(2004, 10);
			newAgent= new Agent(domain,providerSocket,executor);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		return newAgent;
	}


	public Agent( int[] _domain, ServerSocket providerSocket, ExecutorService executor) {
		this._constraints = new Vector<Constraint>();
		this._domain = _domain;
		this.providerSocket = providerSocket;
		this.executor = executor;
	}


	@Override
	public void run()
	{
		listen();
		
	}

	private String listen()
	{
		String message = null;
		try {
			while(true){
				//2. Wait for connection
				System.out.println("Waiting for connection");
				connection = providerSocket.accept();
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());
				Future<String> fut = executor.submit(new ConnectionHandler(connection));
				message = fut.get();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}


}
