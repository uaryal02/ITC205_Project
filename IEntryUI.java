package bcccp.carpark.entry;

public interface IEntryUI {
	public void registerController(IEntryController controller);
	public void deregisterController();
	
	public void display(String message);
	public void printTicket(String id, int ticketNo, long entryDatetime, String barcode);
	public boolean ticketPrinted();
	public void discardTicket();
	public void beep();

}