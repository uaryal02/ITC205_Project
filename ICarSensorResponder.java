package bcccp.carpark;

public interface ICarSensorResponder {
	
	public void carEventDetected(String detectorId, boolean isDetected);

}
