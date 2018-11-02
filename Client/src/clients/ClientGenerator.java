package clients;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ClientGenerator{
    private ClientFrame clientFrame;
    private Socket clientSocket;
    private DataOutputStream outputToServer;
    private BufferedReader inputFromServer;
    
    public ClientGenerator() {
    	clientFrame = new ClientFrame();
    	clientFrame.setVisible(true);
    	//startClient();
    }

	public void startClient() {
		try {
		//ip address: 127.0.0.1 ---> loopback ip address
		//^ when connecting to yourself
		//when connecting to someone else you have to know their ip address
		clientSocket = new Socket("localhost",2245);
		new Thread() {
			public void run() {
				while(true) {
				//send message to the server
				//DataOutputStream outputToServer;
					try {
						//send message to the server
						outputToServer = new DataOutputStream(clientSocket.getOutputStream());
						//System.out.print("Client Name: ");
						Scanner name = new Scanner(System.in);
						String userName= name.nextLine();
						//use \n bc we use readline and it looks for new line character
						String sentence=  userName+ "\n";
						outputToServer.writeBytes(sentence);
					} catch (IOException e) {}
				}
			}
		}.start();
		//reads message from server
		inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String recSentence = inputFromServer.readLine();
		//frame.writeinBox2(recSentence);
		System.out.println("from server: "+ recSentence);
    	}catch(Exception ex) {}
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
