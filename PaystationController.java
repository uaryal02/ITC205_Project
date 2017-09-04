package bcccp.carpark.paystation;

import bcccp.carpark.ICarpark;
import bcccp.tickets.adhoc.IAdhocTicket;

public class PaystationController 
		implements IPaystationController {
	
	private IPaystationUI ui_;	
	private ICarpark carpark_;

	private IAdhocTicket  adhocTicket_ = null;
	private float charge_;
	
	

	public PaystationController(ICarpark carpark_, IPaystationUI ui_) {
		//TODO Implement constructor
	}



	@Override
	public void ticketInserted(String barcode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketPaid() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}

	
	
}
