package bcccp.tickets.adhoc;

import java.util.List;

public interface IAdhocTicketDAO {
	
    /**
     *
     * @param carparkId
     * @return
     */
    public IAdhocTicket createTicket(String carparkId);
	public IAdhocTicket findTicketByBarcode(String barcode);
	public List<IAdhocTicket> getCurrentTickets();


}
