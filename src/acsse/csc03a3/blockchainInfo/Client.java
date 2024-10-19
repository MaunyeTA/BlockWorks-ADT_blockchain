package acsse.csc03a3.blockchainInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client{
	private Socket socket;
	private String serverAddress;
	private int serverPort;
	private BufferedReader br;
	private PrintWriter pw;
	
	public Client(String serverAddress, int serverPort) {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		try {
			this.socket = new Socket(this.serverAddress, this.serverPort);
			br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			pw = new PrintWriter(this.socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<UserBlockInfo> GetBlockchain() {
		
		List<UserBlockInfo> response = new ArrayList<>();
		try {
			pw.println("REQUEST BLOCKCHAIN");
			String line = br.readLine();
			while(!line.equals("END") && line.contains("Block")) {
				response.add(new UserBlockInfo("buffer\n"+line));
				line = br.readLine() ;
			}
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
		
	}
	
	public List<UserBlockInfo> GetEmployeeHistory(String publicKey) {
		
		List<UserBlockInfo> response = new ArrayList<>();
		try {
			pw.println("GET EMPLOYEE");
			pw.println(publicKey);
			int size = Integer.parseInt(br.readLine());
			for(int x=0; x<size; x++) {
				response.add(new UserBlockInfo("buffer\n"+br.readLine()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
		
	}
	
	public void AddBlock(MyTransaction transaction) {
		try {
			pw.println("ADD BLOCK");
			pw.println(transaction);
			System.out.println(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String Register(String id, String publicAddress, String privateKey, String isEmployer) {
		String response = "...";
		try {
			pw.println("REGISTER");
			pw.println(id);
			pw.println(publicAddress);
			pw.println(privateKey);
			pw.println(isEmployer);
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public int Login(String publicAddress, String privateKey) {
		int response = -1;
		try {
			pw.println("LOGIN");
			pw.println(publicAddress);
			pw.println(privateKey);
			response = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public String RequestConfirmation(String transaction) {
		String response = "...";
		try {
			pw.println("REQUEST CONFIRMATION");
			pw.println(transaction);
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public MyTransaction GetPendingRequest() {
		MyTransaction transaction = null;
		String response = "...";
		try {
			pw.println("GET REQUESTS");
			pw.println(transaction);
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!response.contains("No pending requests")) {
			transaction = MyTransaction.ReCreateTransaction(response);
			return transaction;
		}else {
			return null;
		}
	}
	
	public UserBlockInfo GetBlockByHash(String hash) {
		List<UserBlockInfo> blocks =  GetBlockchain();
		String EmployerPublicKey = GetPublicKey();
		UserBlockInfo results = null;
		for(int x=0; x<blocks.size(); x++) {
			if(blocks.get(x).getTransaction().getReceiver().equals(EmployerPublicKey) && blocks.get(x).getHash().equals(hash)) {
				results = blocks.get(x);
			}
		}
		return results;
	}
	
	public String GetPublicKey() {
		String response = "...";
		try {
			pw.println("GET PUBLIC KEY");
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public String Reject() {
		String response = "...";
		try {
			pw.println("REJECT BLOCK");
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetName() {
		String response = "...";
		try {
			pw.println("GET NAME");
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public void Logout() {
		pw.println("LOGOUT");
	}
	
}