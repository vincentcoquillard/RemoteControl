
public class ALLONOFF implements Command{

	Device device;
	
	public ALLONOFF() {
		
	}

	public void execute(Device dev, Command cmd, String cmdType) {
		this.device = dev;
		if(cmdType == "ALLON") {
			this.ALLON();
		}else {
			this.ALLOFF();
		}
	}
	
	public void ALLON() {
		this.device.ALLON();
	}

	public void ALLOFF() {
		this.device.ALLOFF();
	}
	
}
