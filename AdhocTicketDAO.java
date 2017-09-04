package bcccp.tickets.adhoc;

import java.util.List;

public class AdhocTicketDAO  implements IAdhocTicketDAO  {
        
        private String carkparkId_;
        private String barcode_;
        private int currentTicketNo_;

	
	
	public AdhocTicketDAO(String carkparkId_, String barcode_,
                int currentTicketNo) {
            this.carkparkId_ = carkparkId_;
            this.currentTicketNo_= currentTicketNo_;
            this.barcode_= barcode_;
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
