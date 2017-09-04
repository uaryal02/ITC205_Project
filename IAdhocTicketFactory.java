package bcccp.tickets.adhoc;

public interface IAdhocTicketFactory {
	
	public IAdhocTicket make(String carparkId_, int ticketNo_);		
}
