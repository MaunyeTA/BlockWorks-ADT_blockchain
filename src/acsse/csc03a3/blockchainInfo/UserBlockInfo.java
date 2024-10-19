package acsse.csc03a3.blockchainInfo;

public class UserBlockInfo {
	
	private MyTransaction transaction;
	private String previousHash = "";
	private String hash = "";
	private String strBlock = "";
	
	/**
	 * @param block from block's toString
	 */
	public UserBlockInfo(String block) {
			
		transaction = MyTransaction.ReCreateTransactionFromBlockchain(block);
		
		String[] str0 = block.split("\n");
		String[] str = str0[1].split("'");
		
		if(str[1].equals("0")) {
			this.hash = str[1];
			this.strBlock = str0[1];
			return ;
		}
		
		this.strBlock = str0[1];
		
		this.hash = str[10];
		this.previousHash = str[1];
		
	}

	/**
	 * @return the transaction
	 */
	public MyTransaction getTransaction() {
		return transaction;
	}

	/**
	 * @return the previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @return the strBlock
	 */
	public String getStrBlock() {
		return strBlock;
	}
	
	public String toString() {
		return strBlock;
	}
	
	
}
