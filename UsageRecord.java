package bcccp.tickets.season;

public class UsageRecord implements IUsageRecord {
	
	private String ticketId_;
	private long startDateTime_;
	private long endDateTime_;
	
	
	
	public UsageRecord(String ticketId_, long startDateTime_) {
		//TODO Implement constructor
	}



	@Override
	public void finalise(long endDateTime_) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public long getEndTime() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String getSeasonTicketId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
