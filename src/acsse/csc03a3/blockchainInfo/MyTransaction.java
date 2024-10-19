package acsse.csc03a3.blockchainInfo;

import java.io.Serializable;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

public class MyTransaction extends Transaction<UserInfo>{
	
	private long myTimestamp;
	private String strBlock = "";

	public MyTransaction(String sender, String receiver, UserInfo data) {
		super(sender, receiver, data);
		super.setData(data);
		this.myTimestamp = super.getTimestamp();
	}
	
	
	public static MyTransaction ReCreateTransaction(String strTransaction) {
		
		MyTransaction theTransaction = new MyTransaction(null,null,null);
		
		String[] parts = strTransaction.split("\t\s");
		
		UserInfo userInfo = UserInfo.RecreateUserInfo(parts[1]);
		theTransaction.setData(userInfo);
		theTransaction.setSender(parts[2]);
		theTransaction.setReceiver(parts[3]);
		theTransaction.myTimestamp = Long.parseLong(parts[4]);
		
		return theTransaction;
	}
	
	
	public static MyTransaction ReCreateTransactionFromBlockchain(String strTransaction) {
		
		MyTransaction theTransaction = new MyTransaction(null,null,null);
		
		String[] str0 = strTransaction.split("\n");
		String[] str = str0[1].split("'");
		
		if(str[1].equals("0")) {
			theTransaction = new MyTransaction("","",new UserInfo());
			theTransaction.setBlock(strTransaction);
			return theTransaction;
		}
				
		UserInfo userInfo = UserInfo.RecreateUserInfo(str[7]);
		
		theTransaction.setBlock(strTransaction);
		theTransaction.setData(userInfo);
		theTransaction.setReceiver(str[5]);
		theTransaction.setSender(str[3]);
		
		return theTransaction;
	}
	
	/**
	 * @return the string representation of the block 
	 */
	public String getBlock() {
		return strBlock;
	}

	/**
	 * @param userID the strBlock to set
	 */
	public void setBlock(String strBlock) {
		this.strBlock = strBlock;
	}
	
	
	
	@Override
	public String toString() {
		return "MyTransaction=\t\s"+super.getData().toString()+"\t\s"+super.getSender()+"\t\s"+super.getReceiver()+"\t\s"+myTimestamp;
	}
}
