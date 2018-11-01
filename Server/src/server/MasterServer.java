package server;

import java.io.IOException;
import java.net.*;
import java.io.*;

public class MasterServer {
	
	private static ServerFrame pushF1=new ServerFrame();

	public void Server() {
		
	try {
		//port #
		ServerSocket welcomeSocket = new ServerSocket(2245);
		
	while(true) {
		
		Socket connectionSocket = welcomeSocket.accept();
		
		//reads message sent by client
		BufferedReader inputFromClient = new BufferedReader
				(new InputStreamReader(connectionSocket.getInputStream()));
	
		String recSentence = inputFromClient.readLine();
		
		if(recSentence != null){
			
			new Thread() {
				public void run() {
					System.out.println(recSentence);
				}
				
			}.start();
			
		}
      	// If connection request, reply to the client 
      	// Else clear server frame if there are no clients
      	if(recSentence.startsWith("Disconnection"))
        {
        	// pushF1.defaultConnection();
        	
        	 
        }
      	else
        {
 
            pushF1.writeinBox(recSentence);
			pushF1.setConnection("Connected");
			System.out.println(recSentence);
		
            //send message to the client
            DataOutputStream outputToClient = new 
                    DataOutputStream(connectionSocket.getOutputStream());

            //use \n bc we use readline before and it looks for new line character
            String sentence= "From Server: Connected..." +'\n';

            outputToClient.writeBytes(sentence);
        }
		
	}
	//we can't close loop because we have an infinite loop to keep socket open
	//welcomeSocket.close();
	
    }catch(Exception ex) {

    }
}
	
	public static void main(String[] args) {
		
	
		pushF1.displayFrame();
		MasterServer runServer= new MasterServer();
		runServer.Server();

		
		
}

}