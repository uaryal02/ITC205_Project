package bcccp.carpark.entry;

import bcccp.carpark.Carpark;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.ICarSensorResponder;
import bcccp.carpark.ICarpark;
import bcccp.carpark.ICarparkObserver;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.IAdhocTicket;

public class EntryController 
		implements ICarSensorResponder,
				   ICarparkObserver,
		           IEntryController {
	
	private IGate entryGate_;
	private ICarSensor outsideSensor_; 
	private ICarSensor insideSensor_;
	private IEntryUI entryUi_;
	
	private ICarpark carpark_;
	private IAdhocTicket  adhocTicket_ = null;
	private long entryTime_;
	private String seasonTicketId_ = null;
	
	

	public EntryController(Carpark carpark, IGate entryGate, 
			ICarSensor outsideSensor, 
			ICarSensor insideSensor,
			IEntryUI entryUi) {
		//TODO Implement constructor
	}



	@Override
	public void buttonPushed() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketInserted(String barcode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void notifyCarparkEvent() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean isDetected) {
		// TODO Auto-generated method stub
		
	}

	
	
}
