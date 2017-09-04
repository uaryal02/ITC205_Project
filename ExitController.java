package bcccp.carpark.exit;

import bcccp.carpark.Carpark;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.ICarSensorResponder;
import bcccp.carpark.ICarpark;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.IAdhocTicket;

public class ExitController 
		implements ICarSensorResponder,
		           IExitController {
	
	private IGate exitGate_;
	private ICarSensor insideSensor_;
	private ICarSensor outsideSensor_; 
	private IExitUI ui_;
	
	private ICarpark carpark_;
	private IAdhocTicket  adhocTicket_ = null;
	private long exitTime_;
	private String seasonTicketId_ = null;
	
	

	public ExitController(Carpark carpark, IGate exitGate, 
			ICarSensor is,
			ICarSensor os, 
			IExitUI ui) {
		//TODO Implement constructor
	}



	@Override
	public void ticketInserted(String ticketStr) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean isDetected) {
		// TODO Auto-generated method stub
		
	}

	
	
}
