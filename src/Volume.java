
public class Volume implements Command{

	Device device;
	
	public Volume() {
		
	}

	public void execute(Device dev, Command cmd, String cmdType) {
		this.device = dev;
		if(cmdType == "UP") {
			this.VolumeUP();
		}else {
			this.VolumeDOWN();
		}
	}
	
	public void VolumeUP() {
		this.device.VolumeUP();
	}
	
	public void VolumeDOWN() {
		this.device.VolumeDOWN();
	}
}
