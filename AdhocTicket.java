package bcccp.tickets.adhoc;

import java.util.Date;

public class AdhocTicket implements IAdhocTicket {
	
	private String carparkId_;
	private int ticketNo_;
	private long entryDateTime_;
	private long paidDateTime_;
	private long exitDateTime_;
	private float charge_;
	privaye String barcode_;


	
	
	public AdhocTicket(String carparkId_, int ticketNo_, String barcode_) {
		//TDO Implement constructor
	this.carparkId_= carparkId_;
        this.ticketNo_= ticketNo_;
        this.barcode_= barcode_;
        
        
        }


	@Override
	public int getTicketNo() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getBarcode() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getCarparkId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void enter(long dateTime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getEntryDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isCurrent() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void getPay(long dateTime, float charge) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getPaidDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isPaid() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public float getCharge() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void getExit(long dateTime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getExitDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isExited() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
