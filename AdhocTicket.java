package bcccp.tickets.adhoc;

public class AdhocTicket {
	
	private String carparkId;
	private int ticketNo;
	private long EntryDateTime;
	private long PaidDateTime;
	private long exitDateTime;
	private float charge;
	private String barcode;

	
	
	public AdhocTicket(String carparkId, int ticketNo, String barcode) {
		//TDO Implement constructor
	}


	public int getTicketNo() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getBarcode() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCarparkId() {
		// TODO Auto-generated method stub
		return null;
	}


	public void enter(long dateTime) {
		// TODO Auto-generated method stub
		
	}


	public long getEntryDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isCurrent() {
		// TODO Auto-generated method stub
		return false;
	}


	public void pay(long dateTime, float charge) {
		// TODO Auto-generated method stub
		
	}


	public long getPaidDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isPaid() {
		// TODO Auto-generated method stub
		return false;
	}


	public float getCharge() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void exit(long dateTime) {
		// TODO Auto-generated method stub
		
	}


	public long getExitDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean hasExited() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
