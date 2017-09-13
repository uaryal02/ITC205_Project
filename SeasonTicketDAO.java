package bcccp.tickets.season;

import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.IUsageRecordFactory;
import java.util.HashMap;
import java.util.Map;

public class SeasonTicketDAO implements ISeasonTicketDAO {

	private Map<String, ISeasonTicket> currentTickets;
	private IUsageRecordFactory factory;
	
	public SeasonTicketDAO(IUsageRecordFactory factory) {
		this.factory = factory;
		currentTickets = new HashMap<>();
	}



	@Override
	public void registerTicket(ISeasonTicket ticket) {
		if (!currentTickets.containsKey(ticket.getId())) {
			currentTickets.put(ticket.getId(),ticket);
		
	}

	@Override
	public void deregisterTicket(ISeasonTicket ticket) {
		// TODO Auto-generated method stub
		if (currentTickets.containsKey(ticket.getId())) {
			currentTickets.remove(ticket.getId());
		}
	}

	@Override
	public int getNumberOfTickets() {
		// TODO Auto-generated method stub
		return currentTickets.size();
	}

	@Override
	public ISeasonTicket findTicketById(String ticketId) {
		if (currentTickets.containsKey(ticketId)) {
			return currentTickets.get(ticketId);
		}
		return null;
	}

	@Override
	public void recordTicketEntry(String ticketId) {
		// TODO Auto-generated method stub
		ISeasonTicket ticket = findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordTicketUsage : no such ticket: " + ticketId);
		
		long datetime = System.currentTimeMillis();
		IUsageRecord usage = factory.make(ticketId, datetime);
		ticket.recordUsage(usage);	
		
	}

	@Override
	public void recordTicketExit(String ticketId) {
		ISeasonTicket ticket = findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("finaliseTicketUsage : no such ticket: " + ticketId);

		long dateTime = System.currentTimeMillis();
		ticket.endUsage(dateTime);
		

	}
	

	}	


}
