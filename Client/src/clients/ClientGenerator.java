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
					
					try {
						clientSocket = new Socket("localhost",2245);
						//send message to the server
						outputToServer = new DataOutputStream(clientSocket.getOutputStream());
						//reads message from server
						inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						//use \n bc we use readline and it looks for new line character
						String clientName= clientFrame.nameText.getText();
						String clientStatus=  "Connected"+ "\n";
						outputToServer.writeBytes(clientStatus);
						
					}catch(Exception ex) {}
						
					new Thread() {
						public void run() {
							while(true) {
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
					try {
						System.out.print("in the else loop"+ "\n");
						String clientStatus=  "Disconnected"+ "\n";
						outputToServer.writeBytes("Disconnect" +"\n");
					} catch (Exception ex) {}
				}
			}
	
		});
	}
		
	public void stopClient() {
		
      try {
		
        Socket clientSocket = new Socket("localhost",2245);
        
		// Send message to the server
		DataOutputStream outputToServer = new 
				DataOutputStream(clientSocket.getOutputStream());
		
		//use \n bc we use readline and it looks for new line character
		
		String data =  "Disconnection Request: Socket[addr=" + clientSocket.getLocalAddress() + ",port=" + clientSocket.getPort() + ",localport=" + clientSocket.getLocalPort() + "]\n";
		
        // Send data to server
		outputToServer.writeBytes(data);
		
		// Client is no longer connected to Server
		
		
      }catch(Exception ex) { }
      
	}
  
  
	public static void main(String[] args) {
      
		ClientGenerator CG= new ClientGenerator();
      
	}
}
