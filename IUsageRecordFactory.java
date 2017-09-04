package bcccp.tickets.season;

public interface IUsageRecordFactory {

	public IUsageRecord make(String ticketId_, long startDateTime_);
}
