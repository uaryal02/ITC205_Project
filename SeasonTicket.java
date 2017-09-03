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
		//TDO Implement constructor
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCarparkId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getStartValidPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEndValidPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recordUsage(IUsageRecord record) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IUsageRecord getCurrentUsageRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endUsage(long dateTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IUsageRecord> getUsageRecords() {
		// TODO Auto-generated method stub
		return null;
	}


}
