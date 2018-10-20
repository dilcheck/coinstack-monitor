package coinstack.monitor.model;

public class Transaction {
	public String transaction_hash;
	public String block_hash;
	public boolean coinbase;
	public boolean is_deleted;
	public String[] inputs;
	public String[] outputs;
	public String time;
	public String broadcast_time;
	public String[] addresses;
}
