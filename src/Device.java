
public interface Device {
	
	public void ShutDown();
	
	public void TurnOn();
	
	public void VolumeUP();
	
	public void VolumeDOWN();
	
	public void NextStation();
	
	public void PreviousStation();
	
	public boolean getStatusONOFF();
	
	public int getVolume();
	
	public int getStation();
}
