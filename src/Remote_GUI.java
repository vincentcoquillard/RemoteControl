public class Remote_GUI{

	public Remote_GUI() {
		
		TV TV_Device = new TV();
		Radio Radio_Device = new Radio();
		
		ONOFF ONOFF_Command = new ONOFF();
		
		ALLONOFF ALLONOFF_Command = new ALLONOFF();
		Station Station_Command = new Station();
		Volume Volume_Command = new Volume();
		UNDOBUTTON UNDOBUTTON_Command = new UNDOBUTTON();
		
		executeCommand(TV_Device, ONOFF_Command, "ON");
	}
	
	public void executeCommand(Device dev, Command cmd, String cmdType) {
		cmd.execute(dev, cmd, cmdType);
		//reloadGUI
	}

}
