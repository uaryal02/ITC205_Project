package bcccp.tickets.season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeasonTicket implements ISeasonTicket {
	
	private List<IUsageRecord> usages_;
	private IUsageRecord currentUsage_ = null;
	
	private String ticketId_;
	private String carparkId_;
	private long startValidPeriod_;
	private long endValidPeriod_;
	
	public SeasonTicket (String ticketId_, 
			             String carparkId_, 
			             long startValidPeriod_,
			             long endValidPeriod_) {
		this.ticketId = ticketId;
		this.carparkId =carparkId;
		this.startValidPeriod = startValidPeriod;
		this.endValidPeriod = endValidPeriod;
		
		usages = new ArrayList<IUsageRecord>();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ticketId;
	}

	@Override
	public String getCarparkId() {
		// TODO Auto-generated method stub
		return startValidPeriod;
	}

	@Override
	public long getStartValidPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEndValidPeriod() {
		// TODO Auto-generated method stub
		return endValidPeriod;
	}

	@Override
	public boolean isUse() {
		// TODO Auto-generated method stub
		return currentUsage_ != null;
	}

	@Override
	public void recordUsage(IUsageRecord record) {
		currentUsage = record;
		if (!usages.contains(record) ) {
			usages.add(record);
		}
		
	}

	@Override
	public IUsageRecord getCurrentUsageRecord() {
		// TODO Auto-generated method stub
		return currentUsage;
	}

	@Override
	public void endUsage(long dateTime) {
		if (currentUsage == null) throw new RuntimeException("SeasonTicket.endUsage : ticket is not in use");
		
		currentUsage.finalise(dateTime);
		currentUsage = null;
		
	}

	@Override
	public List<IUsageRecord> getUsageRecords() {
		// TODO Auto-generated method stub
		return usageRecords;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Carpark    : " + carparkId + "\n" +
		       "Ticket No  : " + ticketId + "\n" );
		for (IUsageRecord usage : usages) {
			builder.append(usage.toString() + "\n");
		}
		return builder.toString();
	}
	


}
