package acsse.csc03a3.blockchainInfo;

public class PendingConfirmation {
	private String employerAddress = "";
	private MyTransaction transaction = null;
	
	PendingConfirmation(String employerAddress, MyTransaction transaction){
		this.employerAddress = employerAddress;
		this.transaction = transaction;
	}

	/**
	 * @return the employerAddress
	 */
	public String getEmployerAddress() {
		return employerAddress;
	}

	/**
	 * @return the transaction
	 */
	public MyTransaction getTransaction() {
		return transaction;
	}
	
	
}
