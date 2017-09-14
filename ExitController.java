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
	
	private enum STATE { IDLE, WAITING, PROCESSED, REJECTED, TAKEN, EXITING, EXITED, BLOCKED } 
	
	private STATE state_;
	private STATE prevState_;
	private String message_;
	//private String prevMessage;
	
	private IGate exitGate_;
	private ICarSensor is_;
	private ICarSensor os_; 
	private IExitUI ui_;
	
	private ICarpark carpark_;
	private IAdhocTicket  adhocTicket_ = null;
	private long exitTime_;
	private String seasonTicketId_ = null;
	
	

	public ExitController(Carpark carpark, IGate exitGate, 
			ICarSensor is,
			ICarSensor os, 
			IExitUI ui) {
		
		carpark = carpark_;
		exitGate = exitGate_;
		is = is_;
		os = os_;
		ui = ui_;
		
		os.registerResponder();
		is.registerResponder();
		ui.registerController();

		prevState_ = STATE.IDLE;		
		setState(STATE.IDLE);		
	}

	
	
	private void log(String message) {
		System.out.println("ExitController : " + message);
	}



	@Override
	public void carEventDetected(String detectorId, boolean carDetected) {

		log("carEventDetected: " + detectorId + ", car Detected: " + carDetected );
		
		switch (state_) {
		
		case BLOCKED: 
			if (detectorId.equals(is_.getId()) && !carDetected) {
				setState(prevState_);
			}
			break;
			
		case IDLE: 
			log("eventDetected: IDLE");
			if (detectorId.equals(is_.getId()) && carDetected) {
				log("eventDetected: setting state_ to WAITING");
				setState(STATE.WAITING);
			}
			else if (detectorId.equals(os_.getId()) && carDetected) {
				setState(STATE.BLOCKED);
			}
			break;
			
		case WAITING: 
		case PROCESSED: 
			if (detectorId.equals(is_.getId()) && !carDetected) {
				setState(STATE.IDLE);
			}
			else if (detectorId.equals(os_.getId()) && carDetected) {
				setState(STATE.BLOCKED);
			}
			break;
			
		case TAKEN: 
			if (detectorId.equals(is_.getId()) && !carDetected) {
				setState(STATE.IDLE);
			}
			else if (detectorId.equals(os_.getId()) && carDetected) {
				setState(STATE.EXITING);
			}
			break;
			
		case EXITING: 
			if (detectorId.equals(is_.getId()) && !carDetected) {
				setState(STATE.EXITED);
			}
			else if (detectorId.equals(os_.getId()) && !carDetected) {
				setState(STATE.TAKEN);
			}
			break;
			
		case EXITED: 
			if (detectorId.equals(is_.getId()) && carDetected) {
				setState(STATE.EXITING);
			}
			else if (detectorId.equals(os_.getId()) && !carDetected) {
				setState(STATE.IDLE);
			}
			break;
			
		default: 
			break;
			
		}
		
	}

	
	
	private void setState(STATE newState) {
		switch (newState) {
		
		case BLOCKED: 
			log("setState: BLOCKED");
			prevState_ = state_;
			//prevMessage = message_;
			state_ = STATE.BLOCKED;
			message_ = "Blocked";
			ui_.display(message_);
			break;
			
		case IDLE: 
			log("setState: IDLE");
			if (prevState_ == STATE.EXITED) {
				if (adhocTicket_ != null) {
					adhocTicket_.exit(exitTime_);
					carpark_.recordAdhocTicketExit();
					log(adhocTicket_.toString() );
				}
				else if (seasonTicketId_ != null) {
					carpark_.recordSeasonTicketExit(seasonTicketId_);
				}
			}
			adhocTicket_ = null;
			seasonTicketId_ = null;
			
			message_ = "Idle";
			state_ = STATE.IDLE;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			if (is_.carIsDetected()) {
				setState(STATE.WAITING);
			}
			if (exitGate_.isRaised()) {
				exitGate_.lower();
			}
			exitTime_ = 0;
			break;
			
		case WAITING: 
			log("setState: WAITING");
			message_ = "Insert Ticket";
			state_ = STATE.WAITING;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			if (!is_.carIsDetected()) {
				setState(STATE.IDLE);
			}
			break;
			
		case PROCESSED: 
			log("setState: PROCESSED");
			message_ = "Take Processed Ticket";
			state_ = STATE.PROCESSED;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			if (!is_.carIsDetected()) {
				setState(STATE.IDLE);
			}
			break;
			
		case REJECTED: 
			log("setState: REJECTED");
			message_ = "Take Rejected Ticket";
			state_ = STATE.REJECTED;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			if (!is_.carIsDetected()) {
				setState(STATE.IDLE);
			}
			break;
			
		case TAKEN: 
			log("setState: TAKEN");
			message_ = "Ticket Taken";
			state_ = STATE.TAKEN;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			break;
			
		case EXITING: 
			log("setState: EXITING");
			message_ = "Exiting";
			state_ = STATE.EXITING;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			break;
			
		case EXITED: 
			log("setState: EXITED");
			message_ = "Exited";
			state_ = STATE.EXITED;
			//prevMessage = message_;
			prevState_ = state_;
			ui_.display(message_);
			break;
			
		default: 
			break;
			
		}
				
	}

	
	
	private boolean isAdhocTicket(String barcode) {
		return barcode.substring(0,1).equals("A");
	}
	
	
	
	@Override
	public void ticketInserted(String ticketStr) {
		if (state_ == STATE.WAITING) {
			if (isAdhocTicket(ticketStr)) {
				adhocTicket_ = carpark_.getAdhocTicket(ticketStr);
				exitTime_ = System.currentTimeMillis();
				if (adhocTicket_ != null && adhocTicket_.isPaid()) {
					setState(STATE.PROCESSED);
				}
				else {
					ui_.beep();
					setState(STATE.REJECTED);						
				}
			}
			else if (carpark_.isSeasonTicketValid(ticketStr) &&
					 carpark_.isSeasonTicketInUse(ticketStr)){					
				seasonTicketId_ = ticketStr;
				setState(STATE.PROCESSED);
			}
			else {
				ui_.beep();
				setState(STATE.REJECTED);						
			}
		}
		else {
			ui_.beep();
			ui_.discardTicket();
			log("ticketInserted: called while in incorrect state_");
			setState(STATE.REJECTED);						
		}
		
	}
	
	
	
	@Override
	public void ticketTaken() {
		if (state_ == STATE.PROCESSED)  {
			exitGate_.raise();
			setState(STATE.TAKEN);
		}
		else if (state_ == STATE.REJECTED) {
			setState(STATE.WAITING);
		}
		else {
			ui_.beep();
			log("ticketTaken: called while in incorrect state_");
		}
		
	}




	

}
