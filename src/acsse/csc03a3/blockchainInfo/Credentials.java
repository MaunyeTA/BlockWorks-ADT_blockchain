package acsse.csc03a3.blockchainInfo;

public class Credentials {
	private String ID = "";
	private String privateKey = "";
	private String publicAddress = "";
	private boolean isEmployerID = false;
	
	public Credentials() {
	}
	
	public Credentials(String ID, String publicAddress, String privateKey, boolean isEmployerID) {
		this.privateKey = privateKey;
		this.publicAddress = publicAddress;
		this.isEmployerID = isEmployerID;
		this.ID = ID;
	}
	
	public boolean Login(String privateKey) {
		if(this.privateKey.equals(privateKey)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getPublicAddress() {
		return this.publicAddress;
	}
	
	public boolean isEmployer() {
		return this.isEmployerID;
	}
	
	public String getID() {
		return this.ID;
	}
}
