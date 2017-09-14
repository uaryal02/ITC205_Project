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
	
	private enum STATE { IDLE, WAITING, FULL, VALIDATED, ISSUED, TAKEN, ENTERING, ENTERED, BLOCKED } 
	
	private STATE state_;
	private STATE prevState_;
	private String message_;
	
	private IGate entryGate_;
	private ICarSensor outsideEntrySensor_; 
	private ICarSensor insideEntrySensor_;
	private IEntryUI ui_;
	
	private ICarpark carpark_;
	private IAdhocTicket  adhocTicket_ = null;
	private long entryTime_;
	private String seasonTicketId_ = null;
	
	

	public EntryController(Carpark carpark, IGate entryGate, 
			ICarSensor os, 
			ICarSensor is,
			IEntryUI ui) {
		
		carpark_ = carpark;
		entryGate_ = entryGate;
		outsideEntrySensor_ = os;
		insideEntrySensor_ = is;
		ui_ = ui;
		
		outsideEntrySensor_.registerResponder();
		insideEntrySensor_.registerResponder();
		ui.registerController();
		
		setState(STATE.IDLE);
		
	}

	
	
	private void log(String message_) {
		System.out.println("EntryController : " + message_);
	}



	@Override
	public void carEventDetected(String detectorId, boolean carDetected) {

		log("carEventDetected: " + detectorId + ", car Detected: " + carDetected );
		
		switch (state_) {
		
		case BLOCKED: 
			if (detectorId.equals(insideEntrySensor_.getId()) && !carDetected) {
				setState(prevState_);
			}
			break;
			
		case IDLE: 
			log("eventDetected: IDLE");
			if (detectorId.equals(outsideEntrySensor_.getId()) && carDetected) {
				log("eventDetected: setting state to WAITING");
				setState(STATE.WAITING);
			}
			else if (detectorId.equals(insideEntrySensor_.getId()) && carDetected) {
				setState(STATE.BLOCKED);
			}
			break;
			
		case WAITING: 
		case FULL: 
		case VALIDATED: 
		case ISSUED: 
			if (detectorId.equals(outsideEntrySensor_.getId()) && !carDetected) {
				setState(STATE.IDLE);
			}
			else if (detectorId.equals(insideEntrySensor_.getId()) && carDetected) {
				setState(STATE.BLOCKED);
			}
			break;
			
		case TAKEN: 
			if (detectorId.equals(outsideEntrySensor_.getId()) && !carDetected) {
				setState(STATE.IDLE);
			}
			else if (detectorId.equals(insideEntrySensor_.getId()) && carDetected) {
				setState(STATE.ENTERING);
			}
			break;
			
		case ENTERING: 
			if (detectorId.equals(outsideEntrySensor_.getId()) && !carDetected) {
				setState(STATE.ENTERED);
			}
			else if (detectorId.equals(insideEntrySensor_.getId()) && !carDetected) {
				setState(STATE.TAKEN);
			}
			break;
			
		case ENTERED: 
			if (detectorId.equals(outsideEntrySensor_.getId()) && carDetected) {
				setState(STATE.ENTERING);
			}
			else if (detectorId.equals(insideEntrySensor_.getId()) && !carDetected) {
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
			state_ = STATE.BLOCKED;
			message_ = "Blocked";
			ui_.display(message_);
			break;
			
		case IDLE: 
			log("setState: IDLE");
			if (prevState_ == STATE.ENTERED) {
				if (adhocTicket_ != null) {
					adhocTicket_.enter(entryTime_);
					carpark_.recordAdhocTicketEntry();
					entryTime_ = 0;
					log(adhocTicket_.toString() );
					adhocTicket_ = null;
				}
				else if (seasonTicketId_ != null) {
					carpark_.recordSeasonTicketEntry(seasonTicketId_);
					seasonTicketId_ = null;
				}
			}
			message_ = "Idle";
			state_ = STATE.IDLE;
			prevState_ = state_;
			ui_.display(message_);
			if (outsideEntrySensor_.carIsDetected()) {
				setState(STATE.WAITING);
			}
			if (entryGate_.isRaised()) {
				entryGate_.lower();
			}
			ui_.discardTicket();
			break;
			
		case WAITING: 
			log("setState: WAITING");
			message_ = "Push Button";
			state_ = STATE.WAITING;
			prevState_ = state_;
			ui_.display(message_);
			if (!outsideEntrySensor_.carIsDetected()) {
				setState(STATE.IDLE);
			}
			break;
			
		case FULL: 
			log("setState: FULL");
			message_ = "Carpark Full";
			state_ = STATE.FULL;
			prevState_ = state_;
			ui_.display(message_);
			break;
			
		case VALIDATED: 
			log("setState: VALIDATED");
			message_ = "Ticket Validated";
			state_ = STATE.VALIDATED;
			prevState_ = state_;
			ui_.display(message_);
			if (!outsideEntrySensor_.carIsDetected()) {
				setState(STATE.IDLE);
			}
			break;
			
		case ISSUED: 
			log("setState: ISSUED");
			message_ = "Take Ticket";
			state_ = STATE.ISSUED;
			prevState_ = state_;
			ui_.display(message_);
			if (!outsideEntrySensor_.carIsDetected()) {
				setState(STATE.IDLE);
			}
			break;
			
		case TAKEN: 
			log("setState: TAKEN");
			message_ = "Ticket Taken";
			state_ = STATE.TAKEN;
			prevState_ = state_;
			ui_.display(message_);
			entryGate_.raise();
			break;
			
		case ENTERING: 
			log("setState: ENTERING");
			message_ = "Entering";
			state_ = STATE.ENTERING;
			prevState_ = state_;
			ui_.display(message_);
			break;
			
		case ENTERED: 
			log("setState: ENTERED");
			message_ = "Entered";
			state_ = STATE.ENTERED;
			prevState_ = state_;
			ui_.display(message_);
			break;
			
		default: 
			break;
			
		}
				
	}

	
	
	@Override
	public void buttonPushed() {
		if (state_ == STATE.WAITING) {
			if (!carpark_.isFull()) {
				adhocTicket_ = carpark_.issueAdhocTicket();
				
				String carparkId = adhocTicket_.getCarparkId();
				int ticketNo = adhocTicket_.getTicketNo();
				entryTime_ = System.currentTimeMillis();
				//entryTime_ = adhocTicket_.getEntryDateTime();
				String barcode = adhocTicket_.getBarcode();
				
				ui_.printTicket(carparkId, ticketNo, entryTime_, barcode);
				setState(STATE.ISSUED);
			}
			else {
				setState(STATE.FULL);
			}
		}
		else {
			ui_.beep();
			log("ButtonPushed: called while in incorrect state");
		}
		
	}

	
	
	@Override
	public void ticketInserted(String barcode) {
		if (state_ == STATE.WAITING) {
			try {
				if (carpark_.isSeasonTicketValid(barcode) &&
					!carpark_.isSeasonTicketInUse(barcode)) {
					this.seasonTicketId_ = barcode;
					setState(STATE.VALIDATED);
				}
				else {
					ui_.beep();
					seasonTicketId_ = null;
					log("ticketInserted: invalid ticket id");				
				}
			}
			catch (NumberFormatException e) {
				ui_.beep();
				seasonTicketId_ = null;
				log("ticketInserted: invalid ticket id");				
			}
		}
		else {
			ui_.beep();
			log("ticketInserted: called while in incorrect state");
		}
		
	}
	
	
	
	@Override
	public void ticketTaken() {
		if (state_ == STATE.ISSUED || state_ == STATE.VALIDATED ) {
			setState(STATE.TAKEN);
		}
		else {
			ui_.beep();
			log("ticketTaken: called while in incorrect state");
		}
		
	}



	@Override
	public void notifyCarparkEvent() {
		if (state_ == STATE.FULL) {
			if (!carpark_.isFull()) {
				setState(STATE.WAITING);
			}
		}
		
	}

	

}
