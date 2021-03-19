import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class GUI {
	private TV TV_Device;
	private Radio Radio_Device;
	private ONOFF ONOFF_Command;
	private Station Station_Command;
	private Volume Volume_Command;
	private JFrame frame;
	private JLabel TV_StationLabel;
	private JLabel TV_VolumeLabel;
	private JLabel Radio_StationLabel;
	private JLabel Radio_VolumeLabel;
	private JRadioButton Radio_POWER;
	private JRadioButton TV_POWER;
	private JButton ALLON_But;
	private JButton ALLOFF_But;
	private JLabel ErrorMessage;
	private JButton UNDO_But;
	
	private List<List>UndoArray = new ArrayList<List>(); 

	private void updateGUI() { /*Update GUI Values UNDO Array*/
		TV_StationLabel.setText(String.valueOf(TV_Device.Station));
		Radio_StationLabel.setText(String.valueOf(Radio_Device.Station));
		TV_VolumeLabel.setText(String.valueOf(TV_Device.Volume));
		Radio_VolumeLabel.setText(String.valueOf(Radio_Device.Volume));
		TV_POWER.setSelected(TV_Device.ON);
		Radio_POWER.setSelected(Radio_Device.ON);
	}
	
	private void UNDO() {
		if(UndoArray.size() > 1) {
			List<Device>Last = UndoArray.get(UndoArray.size() - 2);  
			this.TV_Device.ON = Last.get(0).getStatusONOFF();
			this.TV_Device.Volume = Last.get(0).getVolume();
			this.TV_Device.Station = Last.get(0).getStation();
			this.Radio_Device.ON = Last.get(1).getStatusONOFF();
			this.Radio_Device.Volume = Last.get(1).getVolume();
			this.Radio_Device.Station = Last.get(1).getStation();
			UndoArray.remove(UndoArray.size() - 1);
			updateGUI();
		}
		
	}
	
	private void executeCommand(Device dev, Command cmd, String cmdType) {
		if(dev.getStatusONOFF() == false && cmd != ONOFF_Command) {
			ErrorMessage.setVisible(true);
		}else {
			ErrorMessage.setVisible(false);
			cmd.execute(dev, cmd, cmdType);
			
			/*Updaye UNDO Array*/
			List<Device>DeviceArray = new ArrayList<Device>();
			TV NEWTV_Device = new TV();
			Radio NEWRadio_Device = new Radio();
			NEWTV_Device.ON = TV_Device.ON;
			NEWRadio_Device.ON = Radio_Device.ON;
			NEWTV_Device.Volume = TV_Device.Volume;
			NEWRadio_Device.Volume = Radio_Device.Volume;
			NEWTV_Device.Station = TV_Device.Station;
			NEWRadio_Device.Station = Radio_Device.Station;
			DeviceArray.add(NEWTV_Device);
			DeviceArray.add(NEWRadio_Device);
			UndoArray.add(DeviceArray);
		}
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		TV_Device = new TV();
		Radio_Device = new Radio();
		ONOFF_Command = new ONOFF();
		Station_Command = new Station();
		Volume_Command = new Volume();
		
		
		/*Initialize UNDO Array*/
		List<Device>DeviceArray = new ArrayList<Device>();
		TV NEWTV_Device = new TV();
		Radio NEWRadio_Device = new Radio();
		NEWTV_Device.ON = TV_Device.ON;
		NEWRadio_Device.ON = Radio_Device.ON;
		NEWTV_Device.Volume = TV_Device.Volume;
		NEWRadio_Device.Volume = Radio_Device.Volume;
		NEWTV_Device.Station = TV_Device.Station;
		NEWRadio_Device.Station = Radio_Device.Station;
		DeviceArray.add(NEWTV_Device);
		DeviceArray.add(NEWRadio_Device);
		UndoArray.add(DeviceArray);
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 1, true));
		panel.setBounds(69, 41, 197, 302);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton TV_ONOFF_But = new JButton("ON/OFF");
		TV_ONOFF_But.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		TV_ONOFF_But.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		TV_ONOFF_But.setBounds(118, 6, 73, 29);
		TV_ONOFF_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TV_Device.ON == true) {
					executeCommand(TV_Device, ONOFF_Command, "OFF");
					TV_POWER.setSelected(TV_Device.ON);
				}else {
					executeCommand(TV_Device, ONOFF_Command, "ON");
					TV_POWER.setSelected(TV_Device.ON);
				}
			}
		});
		panel.add(TV_ONOFF_But);
		
		JButton TV_prev_But = new JButton("<");
		TV_prev_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TV_prev_But.setBounds(18, 154, 43, 33);
		TV_prev_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(TV_Device, Station_Command, "Previous");
				TV_StationLabel.setText(String.valueOf(TV_Device.Station));
			}
		});
		panel.add(TV_prev_But);
		
		JButton TV_next_But = new JButton(">");
		TV_next_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TV_next_But.setBounds(133, 154, 43, 33);
		TV_next_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(TV_Device, Station_Command, "Next");
				TV_StationLabel.setText(String.valueOf(TV_Device.Station));
			}
		});
		panel.add(TV_next_But);
		
		JButton TV_down_But = new JButton("V");
		TV_down_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TV_down_But.setBounds(80, 263, 43, 33);
		TV_down_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(TV_Device, Volume_Command, "Down");
				TV_VolumeLabel.setText(String.valueOf(TV_Device.Volume));
			}
		});
		panel.add(TV_down_But);
		
		JButton TV_up_But = new JButton("^");
		TV_up_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TV_up_But.setBounds(80, 199, 43, 33);
		TV_up_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(TV_Device, Volume_Command, "UP");
				TV_VolumeLabel.setText(String.valueOf(TV_Device.Volume));
			}
		});
		panel.add(TV_up_But);
		
		JLabel lblNewLabel_2 = new JLabel("Station");
		lblNewLabel_2.setBounds(73, 164, 48, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Volume");
		lblNewLabel_2_1_1_1.setBounds(75, 235, 48, 16);
		panel.add(lblNewLabel_2_1_1_1);
		
		TV_POWER = new JRadioButton("POWER");
		TV_POWER.setBounds(6, 6, 141, 23);
		TV_POWER.setEnabled(false);
		panel.add(TV_POWER);
		
		JLabel lblNewLabel_3 = new JLabel("Volume:");
		lblNewLabel_3.setBounds(58, 66, 63, 16);
		panel.add(lblNewLabel_3);
		
		TV_VolumeLabel = new JLabel("0");
		TV_VolumeLabel.setBounds(118, 66, 23, 16);
		TV_VolumeLabel.setText(String.valueOf(this.TV_Device.Volume));
		panel.add(TV_VolumeLabel);
		
		TV_StationLabel = new JLabel("0");
		TV_StationLabel.setBounds(118, 94, 23, 16);
		panel.add(TV_StationLabel);
		
		JLabel lblNewLabel_3_1 = new JLabel("Station:");
		lblNewLabel_3_1.setBounds(58, 94, 63, 16);
		panel.add(lblNewLabel_3_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLACK, 1, true));
		panel_1.setBounds(350, 41, 197, 302);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton Radio_ONOFF_But = new JButton("ON/OFF");
		Radio_ONOFF_But.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		Radio_ONOFF_But.setBounds(118, 6, 73, 29);
		Radio_ONOFF_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Radio_Device.ON == true) {
					executeCommand(Radio_Device, ONOFF_Command, "OFF");
					Radio_POWER.setSelected(Radio_Device.ON);
				}else {
					executeCommand(Radio_Device, ONOFF_Command, "ON");
					Radio_POWER.setSelected(Radio_Device.ON);
				}
			}
		});
		panel_1.add(Radio_ONOFF_But);
		
		JButton Radio_next_But = new JButton(">");
		Radio_next_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Radio_next_But.setBounds(131, 154, 43, 33);
		Radio_next_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(Radio_Device, Station_Command, "Next");
				Radio_StationLabel.setText(String.valueOf(Radio_Device.Station));
			}
		});
		panel_1.add(Radio_next_But);
		
		JButton Radio_prev_But = new JButton("<");
		Radio_prev_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Radio_prev_But.setBounds(16, 154, 43, 33);
		Radio_prev_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(Radio_Device, Station_Command, "Previous");
				Radio_StationLabel.setText(String.valueOf(Radio_Device.Station));
			}
		});
		panel_1.add(Radio_prev_But);
		
		JButton Radio_up_But = new JButton("^");
		Radio_up_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Radio_up_But.setBounds(78, 199, 43, 33);
		Radio_up_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(Radio_Device, Volume_Command, "UP");
				Radio_StationLabel.setText(String.valueOf(Radio_Device.Volume));
			}
		});
		panel_1.add(Radio_up_But);
		
		JButton Radio_down_But = new JButton("V");
		Radio_down_But.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Radio_down_But.setBounds(78, 263, 43, 33);
		Radio_down_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(Radio_Device, Volume_Command, "Down");
				Radio_StationLabel.setText(String.valueOf(Radio_Device.Volume));
			}
		});
		panel_1.add(Radio_down_But);
		
		JLabel lblNewLabel_2_1 = new JLabel("Station");
		lblNewLabel_2_1.setBounds(71, 164, 48, 16);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Volume");
		lblNewLabel_2_1_1.setBounds(78, 235, 48, 16);
		panel_1.add(lblNewLabel_2_1_1);
		
		Radio_POWER = new JRadioButton("POWER");
		Radio_POWER.setBounds(6, 6, 141, 23);
		Radio_POWER.setEnabled(false);
		panel_1.add(Radio_POWER);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Station:");
		lblNewLabel_3_1_1.setBounds(64, 95, 63, 16);
		panel_1.add(lblNewLabel_3_1_1);
		
		Radio_VolumeLabel = new JLabel("0");
		Radio_VolumeLabel.setBounds(124, 95, 23, 16);
		panel_1.add(Radio_VolumeLabel);
		
		Radio_StationLabel = new JLabel("0");
		Radio_StationLabel.setBounds(124, 67, 23, 16);
		panel_1.add(Radio_StationLabel);
		
		JLabel lblNewLabel_3_2 = new JLabel("Volume:");
		lblNewLabel_3_2.setBounds(64, 67, 63, 16);
		panel_1.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel = new JLabel("TV Remote");
		lblNewLabel.setBounds(135, 20, 67, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Radio Remote");
		lblNewLabel_1.setBounds(407, 20, 86, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		UNDO_But = new JButton("UNDO");
		UNDO_But.setBounds(271, 130, 67, 29);
		UNDO_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UNDO();
			}
		});
		frame.getContentPane().add(UNDO_But);
		
		ALLON_But = new JButton("ALL ON");
		ALLON_But.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		ALLON_But.setBounds(271, 173, 67, 29);
		ALLON_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(TV_Device, ONOFF_Command, "ON");
				executeCommand(Radio_Device, ONOFF_Command, "ON");
				Radio_POWER.setSelected(Radio_Device.ON);
				TV_POWER.setSelected(TV_Device.ON);
			}
		});
		frame.getContentPane().add(ALLON_But);
		
		ALLOFF_But = new JButton("ALL OFF");
		ALLOFF_But.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		ALLOFF_But.setBounds(271, 207, 67, 29);
		ALLOFF_But.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand(TV_Device, ONOFF_Command, "OFF");
				executeCommand(Radio_Device, ONOFF_Command, "OFF");
				Radio_POWER.setSelected(Radio_Device.ON);
				TV_POWER.setSelected(TV_Device.ON);
			}
		});
		frame.getContentPane().add(ALLOFF_But);
		
		ErrorMessage = new JLabel("You have to turn on the remote to use it.");
		ErrorMessage.setForeground(Color.RED);
		ErrorMessage.setBounds(190, 350, 259, 16);
		ErrorMessage.setVisible(false);
		frame.getContentPane().add(ErrorMessage);
	}
}
