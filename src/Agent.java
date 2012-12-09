import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Agent implements Runnable {

	private Vector<Constraint> _constraints;
	private int[] _domain;
	ServerSocket providerSocket;
	Socket connection = null;
	ExecutorService executor;

	public Agent(Vector<Constraint> constraints, int[] _domain) {
		this._constraints = constraints;
		this._domain = _domain;
	}

	public Agent(){
		this._constraints = new Vector<Constraint>();
		this._domain = new int[10];
	}

	@Override
	public void run()
	{
		listen();

	}

	private void listen()
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


}
