package acsse.csc03a3.blockchainInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

public class ServerNode {

	private static Blockchain<UserInfo> blockchain;
	private int serverPort;
	private static List<Credentials> creds = new ArrayList<>();
	private static List<PendingConfirmation> confirmation = new ArrayList<>();
	private ServerSocket serverSocket = null;			

	public ServerNode(int port) {
		this.serverPort = port;
		try {
			serverSocket = new ServerSocket(this.serverPort);
			ServerNode.blockchain = new Blockchain<>();
			ServerNode.blockchain.registerStake("localhost", 1);
		} catch (IOException e) {
			serverSocket = null;
			System.out.println("Server already running, starting client application...");
		}
	}
	
	public void startServer() {
		if(serverSocket != null) {
			System.out.println("Server running...");
			while(true) {
				try {
					Socket socket = this.serverSocket.accept();
					Thread thread = new Thread(new ClientHandler(socket));
					thread.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class ClientHandler implements Runnable{
		private Socket socket;
		private BufferedReader br;
		private PrintWriter pw;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
			try {
				br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				pw = new PrintWriter(this.socket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			boolean serverRunning = true;
			Credentials cre = new Credentials();
			boolean isLoggedIn = false;
			do {
				try {
					String input = br.readLine();
					System.out.println(input);
					switch(input) {
					case "REQUEST BLOCKCHAIN":
						
						pw.println(ServerNode.blockchain.toString());
						pw.println("END");
						break;
						
					case "ADD BLOCK":
						
						input = br.readLine();
						MyTransaction tran = MyTransaction.ReCreateTransaction(input);
						Transaction<UserInfo> transaction = new Transaction<>(tran.getSender(), tran.getReceiver(), tran.getData());
						List<Transaction<UserInfo>> list = new ArrayList<>();
						list.add(transaction);
						ServerNode.blockchain.addBlock(list);
						ServerNode.blockchain.registerStake(cre.getPublicAddress(), 1);
						
						for(int x=0; x<confirmation.size(); x++) {
							if(confirmation.get(x).getEmployerAddress().equals(cre.getPublicAddress())) {
								confirmation.remove(x);
								break;
							}
						}
						
						pw.println("Block added...");
						break;
						
					case "REGISTER":
						
						String id = br.readLine();
						String publicAddress = br.readLine();
						String privateKey = br.readLine();
						boolean isEmployer = Boolean.parseBoolean(br.readLine());
						boolean exists = false;
						for(int x=0; x<ServerNode.creds.size(); x++) {
							if(ServerNode.creds.get(x).getPublicAddress().equals(publicAddress)) {
								exists = true;
								break;
							}
						}
						
						if(!exists) {
							ServerNode.creds.add(new Credentials(id, publicAddress, privateKey, isEmployer));
							pw.println("Registered successfully");
						}else {
							pw.println("Account already registered");
						}
						break;
						
					case "LOGIN":
						
						String pubAddress = br.readLine();
						String priKey = br.readLine();
						for(int x=0; x<ServerNode.creds.size(); x++) {
							if(ServerNode.creds.get(x).getPublicAddress().equals(pubAddress)) {
								cre = ServerNode.creds.get(x);
								isLoggedIn = cre.Login(priKey);
								break;
							}
						}
						if(isLoggedIn) {
							if(cre.isEmployer()) {
								pw.println("1");
							}else {
								pw.println("2");
							}
						}else {
							pw.println("0");
						}
						break;
						
					case "GET EMPLOYEE":
						
						String employeePublicKey = br.readLine();
						List<String> results = new ArrayList<>();
						UserBlockInfo block;
						String[] line = ServerNode.blockchain.toString().split("\n");
						for(int x=0; x<line.length; x++) {
							block = new UserBlockInfo("buffer\n"+line[x]);
							if(block.getTransaction().getSender().equals(employeePublicKey) || block.getTransaction().getReceiver().equals(employeePublicKey)) {
								results.add(block.toString());
							}
						}
						pw.println(results.size());
						for(int x=0; x<results.size(); x++) {
							pw.println(results.get(x).toString());
						}
						break;
						
					case "REQUEST CONFIRMATION":
						
						input = br.readLine();
						MyTransaction tran2 = MyTransaction.ReCreateTransaction(input);
						confirmation.add(new PendingConfirmation(tran2.getReceiver(), tran2));
						pw.println("Request Revieved");
						break;
						
					case "GET REQUESTS":
						
						MyTransaction tran3 = null;
						for(int x=0; x<confirmation.size(); x++) {
							if(confirmation.get(x).getEmployerAddress().equals(cre.getPublicAddress())) {
								tran3 = confirmation.get(x).getTransaction();
								break;
							}
						}
						
						if(tran3 == null) {
							pw.println("No pending requests");
						}else {
							UserInfo ui = tran3.getData();
							ui.setCompanyName(cre.getID());
							tran3.setData(ui);
							pw.println(tran3.toString());
						}
						
						break;
						
					case "GET NAME":
						
						pw.println(cre.getID());
						
						break;
						
					case "GET PUBLIC KEY":
						
						pw.println(cre.getPublicAddress());
						
						break;
						
					case "REJECT BLOCK":
						
						for(int x=0; x<confirmation.size(); x++) {
							if(confirmation.get(x).getEmployerAddress().equals(cre.getPublicAddress())) {
								confirmation.remove(x);
								break;
							}
						}
						pw.println("Block rejected");
						
						break;
						
					case "LOGOUT":
						serverRunning = false;
						break;
						
					default:
						break;
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}while(serverRunning);
			CloseConnections();
		}
		
		private void CloseConnections() {
			try {
				br.close();
				pw.close();
				socket.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

		
}
