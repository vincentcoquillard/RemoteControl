
public class ONOFF implements Command{
	
	Device device;
	
	public ONOFF() {
		
	}
	
	public void execute(Device dev, Command cmd, String cmdType) {
		this.device = dev;
		if(cmdType == "ON") {
			this.ON();
		}else {
			this.OFF();
		}
	}
	
	private void ON() {
		this.device.TurnOn();
	}

	private void OFF() {
		this.device.ShutDown();
	}
}
