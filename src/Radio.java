
public class Radio implements Device{
	
	public int Volume = 0;
	public int Station = 0;
	public boolean ON = false;
	
	public void ShutDown() {
		this.ON = false;
	}
		
	public void TurnOn() {
		this.ON = true;
	}
			
	public void VolumeUP() {
		if(this.Volume <= 9) {
			this.Volume++;
		}
	}
			
	public void VolumeDOWN(){
		if(this.Volume > 0) {
			this.Volume--;
		}
	}
			
	public void NextStation() {
		if(this.Station <= 9) {
			this.Station++;
		}
	}
			
	public void PreviousStation() {
		if(this.Station > 0) {
			this.Station--;
		}
	}
			
	public void ALLON() {
		
	}
			
	public void ALLOFF() {
		
	}
	
	public void UNDO() {
		
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
