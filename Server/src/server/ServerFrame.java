package server;

import java.awt.Color;

import java.awt.FlowLayout;//imports basic layout so we don't have to create one

import javax.swing.JButton;
import javax.swing.JFrame;// gives you basic features of windows (title bar, maximize, minimize...)
import javax.swing.JLabel;//lets you output text and images
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;//ways for user to do something ex press enter
import java.awt.event.ActionEvent;



public class ServerFrame extends JFrame{// Extends all windows features
	

	
	private JLabel connectLabel=new JLabel();
	private JTextArea box1= new JTextArea(5,20);
	
	//private TCPServer cool=new TCPServer();
	

	//Constructor
	public ServerFrame() {
		
		super("Server TCP"); //gives title
		//types of layout you can use: FlowLayout, GridLayout ...
		setLayout(new FlowLayout()); //gives default layout
		defaultConnection();
		
	}
	

	public void writeinBox(String input) {
	
			box1.append(input + '\n');	
	
		}
		
	
	
	
	public void defaultConnection() {
		
		connectLabel.setText("Connection Status: Not Connected");
		connectLabel.setForeground(Color.RED);
	}
	

	//Method checks what connection status it is and outputs it with its designated color
	public void setConnection(String connect) {
		
		if(connect.equals("Not Connected")) {
			connectLabel.setText("Connection Status: "+ connect );
			//connectLabel= new JLabel("Connection Status: "+ connect );
			connectLabel.setForeground(Color.RED);
			
		}else {
			connectLabel.setText("Connection Status: "+ connect );
			//connectLabel= new JLabel("Connection Status: "+ connect );
			connectLabel.setForeground(Color.BLUE);
			
		}
		
	}
	
	//Method that adds the different features and makes the frame visible
	public void displayFrame() {
		
		add(connectLabel);
		add(box1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(275,180);
		setVisible(true);
		
	}	

	public static void main(String[] args) {
	
	
}

}