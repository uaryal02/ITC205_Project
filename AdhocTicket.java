package bcccp.tickets.adhoc;

import java.util.Date;

public class AdhocTicket implements IAdhocTicket {
	
	private String carparkId_;
	private int ticketNo_;
	private long entryDateTime_;
	private long paidDateTime_;
	private long exitDateTime_;
	private float charge_;
	private String barcode_;
	private STATE state_;
	private enum STATE { ISSUED, CURRENT, PAID, EXITED }
	
	
	public AdhocTicket(String carparkId, int ticketNo, String barcode) {
		//TDO Implement constructor
	this.carparkId= carparkId;
        this.ticketNo= ticketNo;
        this.barcode= barcode;
        this.state_ = STATE.ISSUED;	
        
	}


	@Override
	public int getTicketNo() {
		// TODO Auto-generated method stub
		return ticketNo_;
	}


	@Override
	public String getBarcode() {
		// TODO Auto-generated method stub
		return barcode_;
	}


	@Override
	public String getCarparkId() {
		// TODO Auto-generated method stub
		return nCarparkId;
	}


	@Override
	public void enter(long entryDateTime) {
		// TODO Auto-generated method stub
		this.entryDateTime = entryDateTime;
		this.state_ = STATE.CURRENT;
	}


	@Override
	public long getEntryDateTime() {
		// TODO Auto-generated method stub
		return entryDateTime;
	}


	@Override
	public boolean isCurrent() {
		// TODO Auto-generated method stub
		return state_ == STATE.CURRENT;
	}


	@Override
	public void pay(long paiddateTime, float charge) {
		// TODO Auto-generated method stub
		this.paidDateTime = paidDateTime;
		this.charge = charge;
		state_ = STATE.PAID;
	}


	@Override
	public long getPaidDateTime() {
		// TODO Auto-generated method stub
		return paidDateTime;
	}


	@Override
	public boolean isPaid() {
		// TODO Auto-generated method stub
		return state_ == STATE.PAID;
	}


	@Override
	public float getCharge() {
		// TODO Auto-generated method stub
		return getCharge;
	}


	@Override
	public void Exit(long dateTime) {
		// TODO Auto-generated method stub
		exitDateTime = dateTime;
		state_ = STATE.EXITED;
	}


	@Override
	public long getExitDateTime() {
		// TODO Auto-generated method stub
		return exitDateTime;
	}


	@Override
	public boolean isExited() {
		// TODO Auto-generated method stub
		return state_ == STATE.EXITED;
	}

	
	
}
