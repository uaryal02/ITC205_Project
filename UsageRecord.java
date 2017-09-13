package bcccp.tickets.season;

public class UsageRecord implements IUsageRecord {
	
	private String ticketId_;
	private long startDateTime_;
	private long endDateTime_;
	
	
	
	public UsageRecord(String ticketId_, long startDateTime_) {
		//TODO Implement constructor
		this.ticketId = ticketId;
		this.startDateTime = startDateTime;
	}



	@Override
	public void finalise(long endDateTime_) {
		// TODO Auto-generated method stub
		this.endDateTime = endDateTime;
	}



	@Override
	public long getStartTime() {
		// TODO Auto-generated method stub
		return startDateTime;
	}

	@Override
	public long getEndTime() {
		// TODO Auto-generated method stub
		return endDateTime;
	}



	@Override
	public String getSeasonTicketId() {
		// TODO Auto-generated method stub
		return seasonTicketId;
	}
	public String toString() {
		return ("Usage : startDateTime : " + startDateTime + ", endDateTime: " + endDateTime);
	}
	
	
}
