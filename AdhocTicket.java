package bcccp.tickets.adhoc;

import java.util.Date;

public class AdhocTicket implements IAdhocTicket {
	
	public String carparkId;
	public int ticketNo;
	public long entryDateTime;
	public long paidDateTime;
	public long exitDateTime;
	public float charge;
	public String barcode;

	
	
	public AdhocTicket(String carparkId, int ticketNo, String barcode) {
		//TDO Implement constructor
	this.carparkId= carparkId;
        this.ticketNo= ticketNo;
        this.barcode= barcode;
        
        
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
	public boolean hasExited() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
