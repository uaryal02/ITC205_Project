package bcccp.carpark;

import java.util.ArrayList;
import java.util.List;

import bcccp.tickets.adhoc.IAdhocTicket;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.ISeasonTicketDAO;

public class Carpark implements ICarpark {
	
	private List<ICarparkObserver> observers_;
	private String carparkId_;
	private int capacity_;
	private int nParked_;
	private IAdhocTicketDAO adhocTicketDAO_;
	private ISeasonTicketDAO seasonTicketDAO_;
	
	
	
	public Carpark(String name, int capacity, 
			IAdhocTicketDAO adhocTicketDAO, 
			ISeasonTicketDAO seasonTicketDAO) {
		carparkId = name;
		capacity_ = capacity;
		observers_ = new ArrayList<>();
		adhocTicketDAO_ = adhocTicketDAO;
		seasonTicketDAO_ = seasonTicketDAO;
	}

	
	
	@Override
	public void register(ICarparkObserver observer) {
		if (!observers_.contains(observer)) {
			observers_.add(observer);
		}
	}

	
	
	@Override
	public void deregister(ICarparkObserver observer) {
		if (observers_.contains(observer)) {
			observers_.remove(observer);
		}
	}
	
	
	
	private void notifyObservers() {
		for (ICarparkObserver observer : observers_) {
			observer.notifyCarparkEvent();
		}
	}

	
	
	@Override
	public String getName() {
		return carparkId_;
	}
	
	
	
	@Override
	public boolean isFull() {
		return nParked_ + seasonTicketDAO_.getNumberOfTickets() == capacity_;
	}
	
	
	
	@Override
	public IAdhocTicket issueAdhocTicket() {
		return adhocTicketDAO_.createTicket(carparkId_);
	}
	
	
	@Override
	public IAdhocTicket getAdhocTicket(String barcode) {
		return adhocTicketDAO_.findTicketByBarcode(barcode);
	}
	
	
		
	@Override
	public float calculateAddHocTicketCharge(long entryDateTime) {
		//TODO Implement charge logic
		return 3.0f;
	}

	
	
	@Override
	public boolean isSeasonTicketValid(String barcode) {		
		ISeasonTicket ticket = seasonTicketDAO_.findTicketById(barcode);
		
		// TODO implement full validation logic
		return ticket != null;
	}

	
	
	@Override
	public void registerSeasonTicket(ISeasonTicket seasonTicket) {
		seasonTicketDAO_.registerTicket(seasonTicket);		
	}



	@Override
	public void deregisterSeasonTicket(ISeasonTicket seasonTicket) {
		seasonTicketDAO_.deregisterTicket(seasonTicket);		
	}

	
	
	@Override
	public void recordSeasonTicketEntry(String ticketId) {
		ISeasonTicket ticket = seasonTicketDAO_.findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketEntry: invalid ticketId - " + ticketId);
		
		seasonTicketDAO_.recordTicketEntry(ticketId);
		log(ticket.toString());
	}

	
	
	private void log(String message) {
		System.out.println("Carpark : " + message);
	}



	@Override
	public void recordAdhocTicketEntry() {
		nParked_++;
		
	}



	@Override
	public void recordAdhocTicketExit() {
		nParked_--;
		notifyObservers();		
	}



	@Override
	public void recordSeasonTicketExit(String ticketId) {
		ISeasonTicket ticket = seasonTicketDAO_.findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketExit: invalid ticketId - " + ticketId);
		
		seasonTicketDAO_.recordTicketExit(ticketId);
		log(ticket.toString());
	}



	@Override
	public boolean isSeasonTicketInUse(String ticketId) {
		ISeasonTicket ticket = seasonTicketDAO_.findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketExit: invalid ticketId - " + ticketId);
		
		return ticket.inUse();
	}

















}
