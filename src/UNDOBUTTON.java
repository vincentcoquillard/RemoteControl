
public class UNDOBUTTON implements Command{

	Device device;
	
	public UNDOBUTTON() {
		
	}
	
	public void execute(Device dev, Command cmd, String cmdType) {
		this.UndoCommand();
	}
	
	public void UndoCommand() {
		this.device.UNDO();
	}
	
}
