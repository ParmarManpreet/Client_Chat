package clients;

import java.net.*;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ClientGenerator{
    public  ClientFrame clientFrame;
    private Socket clientSocket;
    private DataOutputStream outputToServer;
    private BufferedReader inputFromServer;
    public boolean a = true;
    
    public ClientGenerator() {
    	clientUI();
    	clickConnect();
    }

	public void clientUI() {
		clientFrame = new ClientFrame();
		clientFrame.setVisible(true);
	}

	public void clickConnect() {
		clientFrame.connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientFrame.connectButton.getText().equals("Connect")) {
					clientFrame.connectButton.setText("Disconnect");
					a = true;
					try {
						clientSocket = new Socket("localhost",2245);
						//send message to the server
						outputToServer = new DataOutputStream(clientSocket.getOutputStream());
						//reads message from server
						inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						//use \n bc we use readline and it looks for new line character
						String clientName= clientFrame.nameText.getText();
						String clientStatus=  "Connected"+ "\n";
						
						outputToServer.writeBytes(clientName+ "\n");
						
					}catch(Exception ex) {}
						
					new Thread() {
						public void run() {
							while(a) {
							try {
								String recSentence = inputFromServer.readLine();
								System.out.println("from server: "+ recSentence);
								clientFrame.chatBox.append(recSentence + "\n");
							} catch (IOException e) {}
							}
						}
					}.start();
	
				}else {
					clientFrame.connectButton.setText("Connect");
					a = false;
					try {
						String clientDisconnect=  "Disconnected"+ "\n";
						outputToServer.writeBytes(clientDisconnect);
					} catch (Exception ex) {
						System.out.print(ex.getMessage());
					}
				}
			}
	
		});
		
		clientFrame.sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientFrame.outputNameText.getText().isEmpty()) {
					String message = clientFrame.messageBox.getText();
					try {
						outputToServer.writeBytes(message+ "\n");
					} catch (IOException e1) {
					}
				}else {
					String receiverName = clientFrame.outputNameText.getText();
					String message = clientFrame.messageBox.getText();
					try {
						String nameAndMessage = receiverName + "," + message;
						outputToServer.writeBytes("YES" +"," +nameAndMessage+ "\n");
						System.out.println("name and message says: "+ nameAndMessage);
					} catch (IOException e1) {}
				}
			}
		});		
	}
  
	public static void main(String[] args) {
      
		ClientGenerator CG= new ClientGenerator();
      
	}
}
