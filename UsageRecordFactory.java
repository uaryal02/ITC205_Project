package bcccp.tickets.season;

public class UsageRecordFactory implements IUsageRecordFactory {

	@Override

	public IUsageRecord make(String ticketId_, long startDateTime_)) {

	return new UsageRecord(ticketId, startDateTime);
	}


}
