package bcccp.tickets.adhoc;

import java.util.List;

public class AdhocTicketDAO  implements IAdhocTicketDAO  {
        
        public String carkparkId;
        public String barcode;
        public int currentTicketNo;

	
	
	public AdhocTicketDAO(String carkparkId, String barcode,
                int currentTicketNo) {
            this.carkparkId = carkparkId;
            this.currentTicketNo= currentTicketNo;
            this.barcode= barcode;
		//TODO Implement constructor
	}



	@Override
	public IAdhocTicket createTicket(String carparkId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IAdhocTicket findTicketByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<IAdhocTicket> getCurrentTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
