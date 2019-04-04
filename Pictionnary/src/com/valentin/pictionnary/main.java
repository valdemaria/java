package com.valentin.pictionnary;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;


import com.valentin.pictionnary.Joueur;
import com.valentin.pictionnary.main;

import javax.swing.JLabel;
import javax.swing.JButton;

public class main {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPictionnary;
	private String nomJoueur;
	private String adresse,send,receive;
	private int score;
	private Client client;
	private Connect connect;
	private Runnable write;
	private Thread t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
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
	public main() {
		initialize();
		this.frame.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		nomJoueur="";
		adresse="";
		score=0;
		t=new Thread(write);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 83, 180, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 143, 180, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("adresse IP du serveur");
		lblNewLabel.setBounds(12, 118, 180, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom joueur");
		lblNewLabel_1.setBounds(12, 54, 170, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnStart = new JButton("start");
		btnStart.setBounds(22, 178, 97, 25);
		frame.getContentPane().add(btnStart);
		
		lblPictionnary = new JLabel("Pictionnary");
		lblPictionnary.setBounds(159, 0, 152, 16);
		frame.getContentPane().add(lblPictionnary);
	
	
	btnStart.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0)  {
		if(!textField_1.getText().equalsIgnoreCase("") && !textField.getText().equalsIgnoreCase("")  )
		{
			nomJoueur=textField.getText();
			adresse=textField_1.getText();
			score=0;
			Joueur joueur = new Joueur(nomJoueur,adresse,score);
			main.this.frame.setVisible(false);
			client = new Client(main.this.frame);
			client.initJoueur(nomJoueur,adresse,score);
			t.start();
			try{
				write();}
				catch(InterruptedException ex){
					System.out.println(ex);
				}
			 /*Connect connect = new Connect(nomJoueur,adresse);
		        connect.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        connect.frame.setVisible(true);
		        try{
		        	connect.run();
		        }
		        catch(IOException ex){
		        	System.out.println(ex);
		        }*/
		        
			/*connect = new Connect(adresse,nomJoueur,send,receive);
			try{
			connect.run();
			
			}
			catch(IOException ex)
			{
				System.out.println(ex);
			}*/
			
			
		}
	}
	});
	}
	public void write() throws InterruptedException{
	while(true){
		t.sleep(2000);
	
	System.out.println("ok");
	}
	}
	/*public void gestionMsg()
	{
		
		client.chat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	connect.sendChat(client.chat.getText());
                client.chat.setText("");
                System.out.println("enter");
            }
        });
		
	}
	public void receptionMsg()
	{
		while(true){
			try{
				connect.receiveChat();
			}
			catch(IOException ex){
				System.out.println(ex);
			}
			
		}
	}*/
	
}

	
