import java.util.Date;

public class Block {
	private String hash;
	private String previousHash;
	private String data;
	private long timestamp;
	private int nonce;
	
	//Block Constructor
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String getHash(){
		return this.hash;
	}
	
	public String getPreviousHash() {
		return this.previousHash;
	}
	
	public String calculateHash() {
		String calculatedHash = BlockUtil.applySha256(
				previousHash + Long.toString(timestamp) + Integer.toString(nonce) +  data);
		return calculatedHash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
