
public class Station implements Command{

	Device device;
	
	public Station() {
		
	}
	
	public void execute(Device dev, Command cmd, String cmdType) {
		this.device = dev;
		if(cmdType == "Next") {
			this.GetNext();
		}else {
			this.GetPrevious();
		}
	}
	
	public void GetPrevious() {
		this.device.PreviousStation();
	}
	
	public void GetNext() {
		this.device.NextStation();
	}
	
}
