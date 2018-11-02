package clients;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;//imports basic layout so we don't have to create one
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;// gives you basic features of windows (title bar, maximize, minimize...)
import javax.swing.JLabel;//lets you output text and images
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;//ways for user to do something ex press enter
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame{
	private JLabel clientLabel = new JLabel("Client Name");
	private JLabel sendtoLabel = new JLabel( "Send to");
	private JTextField nameText = new JTextField();
	private JTextField outputNameText = new JTextField();
	private JTextArea chatBox = new JTextArea();
	private JTextArea messageBox = new JTextArea();
	public JButton connectButton = new JButton("Connect");
	public JButton sendButton = new JButton("Send");
	private ClientGenerator runClient = new ClientGenerator();
	
	
	public ClientFrame() {
		super("Chatting Client"); //gives title
		setLayout(null); //gives default layout
		
		defaultUI();
	}
	
	private void defaultUI() {
		setSize(400,400);
		clientLabel.setBounds(20, 20, 100, 20);
		add(clientLabel);
		nameText.setBounds(100, 20, 100, 20);
		add(nameText);
		connectButton.setBounds(210, 15, 100, 30);
		add(connectButton);
		chatBox.setBounds(20, 60, 360, 200);
		add(chatBox);
		sendtoLabel.setBounds(20, 275, 100, 20);
		add(sendtoLabel);
		outputNameText.setBounds(100, 275, 100, 20);
		add(outputNameText);
		messageBox.setBounds(20, 300, 295, 70);
		add(messageBox);
		sendButton.setBounds(318, 325, 80, 30);
		add(sendButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		//connectButton.addActionListener(new ActionListener() {

			//@Override
			//public void actionPerformed(ActionEvent e) {
				
			//	checkConnection();
		//}

		//});
		
	}
	
	public void writeinBox2(String input) {
		
		chatBox.append(input+"\n");
	
	}

	public void clickConnect() {
		
	connectButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			checkConnection();
	}

	});
	
	}
	
	public void checkConnection() {
		
		if (connectButton.getText().equals("Connect")) {
			connectButton.setText("Disconnect");
			clientLabel.setText("Connection Status: Connected" );
			clientLabel.setForeground(Color.BLUE);
			
			//runClient.startClient();//setConnection("Connected");
		
		}else {
			connectButton.setText("Connect");
			clientLabel.setText("Connection Status: Not Connected" );
			clientLabel.setForeground(Color.RED);
		//runClient.stopClient();
			
		}
	}
	
	public void setConnection(String connect) {
		
		if(connect.equals("Not Connected")) {
			clientLabel= new JLabel("Connection Status: "+ connect );
			clientLabel.setForeground(Color.RED);
		
			
		}else {
			clientLabel= new JLabel("Connection Status: "+ connect );
			clientLabel.setForeground(Color.BLUE);
		
		}
		
	}
	

	public static void main(String[] args) {
		ClientFrame CF = new ClientFrame();
	
	
	}
}