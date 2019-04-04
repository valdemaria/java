package com.valentin.pictionnary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Connect {

	
	
	 	BufferedReader in;
	    PrintWriter out;
	    public JFrame frame = new JFrame("Chatter");
	    JTextField textField = new JTextField(40);
	    JTextArea messageArea = new JTextArea(8, 40);
	    String adresse, nom;

	    public Connect(String nomJoueur,String adresse) {
	    	this.nom=nomJoueur;
	    	this.adresse=adresse;
	        // Layout GUI
	        textField.setEditable(false);
	        messageArea.setEditable(false);
	        frame.getContentPane().add(textField, "North");
	        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
	        frame.pack();

	        // Add Listeners
	        textField.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                out.println(textField.getText());
	                textField.setText("");
	            }
	        });
	    }

	  
			// TODO Auto-generated constructor stub
		

		/**
	     * Saisie de l'adresse du sereur
	     */

	    /**
	     * Selection du Pseudo
	     */
	    

	    /**
	     *Connexion au serveur et creation de la boucle d'attente des message
	     */
	    public void run() throws IOException {

	        // Creation de la connection avec les informations saisie
	        String serverAddress = adresse;
	        Socket socket = new Socket(serverAddress, 9001);
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream(), true);

	        // Récupération de tous les message suivant le "protocole"
	        while (true) {
	            String line = in.readLine();
	            if (line.startsWith("SUBMITNAME")) {
	                out.println();
	            } else if (line.startsWith("NAMEACCEPTED")) {
	                textField.setEditable(true);
	            } else if (line.startsWith("MESSAGE")) {
	                messageArea.append(line.substring(8) + "\n");
	            }
	        }
	    }
}
	/*private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	private String adresseIp,NomJoueur,send ,receive;
	
	 public Connect(String adresse, String nomJoueur, String send, String receive) {
		 this.adresseIp=adresse;
		 this.NomJoueur=nomJoueur;
		 this.send=send;
		 this.receive=receive;
	}

	public void run() throws IOException {
		 
         // Creation de la connection avec les informations saisie
         Socket socket = new Socket(adresseIp, 9001);
         in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         out = new PrintWriter(socket.getOutputStream(), true);

         // Récupération de tous les message suivant le "protocole"
         while (true) {
            
             if (line.startsWith("SUBMITNAME")) {
                 out.println(NomJoueur);
             }
             	else if (line.startsWith("MESSAGE")) {
                 receive=(line.substring(8) + "\n");
             }
         }
     }
	public void sendChat(String send)
	{
		System.out.println(send);
		out.println(send);
	}
	public void receiveChat () throws IOException
	{
		
			
		String line = in.readLine();
		if(!line.isEmpty()){
		receive=(line.substring(8) + "\n");
		System.out.println(receive+" ok");
		}
		
	}*/

