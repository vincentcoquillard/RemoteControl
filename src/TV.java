
public class TV implements Device{
	
	public int Volume = 0;
	public int Station = 0;
	public boolean ON = false;
	
	Radio Radio_Device;
	
	public TV() {
		
	}

	public void ShutDown() {
		this.ON = false;
	}
		
	public void TurnOn() {
		this.ON = true;
	}
			
	public void VolumeUP() {
		if(this.Volume < 5) {
			this.Volume++;
		}
	}
			
	public void VolumeDOWN(){
		if(this.Volume > 0) {
			this.Volume--;
		}
	}
			
	public void NextStation() {
		if(this.Station < 5) {
			this.Station++;
		}
	}
			
	public void PreviousStation() {
		if(this.Station > 0) {
			this.Station--;
		}
	}
	
	public boolean getStatusONOFF() {
		return this.ON;
	}
	
	public int getVolume() {
		return this.Volume;
	}
	
	public int getStation() {
		return this.Station;
	}
}
