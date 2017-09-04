package bcccp.tickets.adhoc;

import java.util.List;

public interface IAdhocTicketDAO {
        public IAdhocTicket createTicket(String carparkId_);
        public IAdhocTicket findTicketByBarcode(String barcode_);
	public List<IAdhocTicket> getCurrentTickets();
}
