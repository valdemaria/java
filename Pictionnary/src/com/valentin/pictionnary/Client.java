package com.valentin.pictionnary;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import org.omg.CORBA.SystemException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.AttributedCharacterIterator;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Client extends JPanel{

	private JFrame frame;
	private Graphics brushTool;
	private JPanel panel;
	public JTextArea chatRead;
	public JTextField reponse,chat;
	private JColorChooser choice;
	private Color couleur;
	private JDialog dialog;
	private Button color;
	private double x;
	private double y;
	private int size,score;
	private String nomJoueur,adresse,message,msgReponse;
	private final ArrayList<Point> point = new ArrayList<>();
	private JPanel panelColor;
	private JLabel lblTailleCrayon,lblNom,lblScore,lblReponse,lblChat;


	/**
	 * @wbp.nonvisual location=66,64
	 */

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Client(JFrame frame) {
		

		size=10;
		couleur=Color.black;
		initialize();
		this.frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1047, 696);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		choice=new JColorChooser(Color.black);
		choice.setBounds(38,390,454,202);
		
		
		
		dialog=new JDialog();
		dialog.getContentPane().add(choice);
		dialog.setLocation(1147, 100);
		dialog.pack();
		dialog.setVisible(false);
		
		reponse=new JTextField();
		reponse.setBounds(74,602,201,24);
		frame.getContentPane().add(reponse);
		reponse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                //out.println(reponse.getText());
                reponse.setText("");
                //System.out.println("enter");
            }
        });
		chatRead=new JTextArea();
		chatRead.setBackground(Color.WHITE);
		chatRead.setEditable(false);
		chatRead.setBounds(794,63,223,431);
		frame.getContentPane().add(chatRead);
		chat=new JTextField();
		chat.setBounds(794,507,233,24);
		frame.getContentPane().add(chat);
		chat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //out.println(reponse.getText());
                chat.setText("");
                System.out.println("enter");
            }
        });
		
		panelColor = new JPanel();
		panelColor.setBounds(95, 10, 79, 24);
		panelColor.setBackground(couleur);
		frame.getContentPane().add(panelColor);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(1);
		panel.setForeground(Color.RED);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 36, 784, 495);
		frame.getContentPane().add(panel);
		
		color = new Button("color");
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(true);
				choice.getSelectionModel().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						couleur=choice.getColor();
						panelColor.setBackground(couleur);
						System.out.println(couleur); 
					}
				});
				
			}
		});
		color.setBounds(10, 10, 79, 24);
		frame.getContentPane().add(color);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setMinimum(5);
		scrollBar.setBounds(303, 10, 122, 24);
		frame.getContentPane().add(scrollBar);
		scrollBar.setBlockIncrement(2);
		scrollBar.setMaximum(30);
		scrollBar.setValue(10);
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setAlignmentX(CENTER_ALIGNMENT);
		
		lblTailleCrayon = new JLabel("taille crayon");
		lblTailleCrayon.setBounds(212, 18, 79, 16);
		frame.getContentPane().add(lblTailleCrayon);
		lblNom = new JLabel("nom joueur : ");
		lblNom.setBounds(10, 544, 165, 16);
		frame.getContentPane().add(lblNom);
		lblScore = new JLabel("score : ");
		lblScore.setBounds(10, 573, 165, 16);
		frame.getContentPane().add(lblScore);
		lblReponse= new JLabel("Reponse : ");
		lblReponse.setBounds(10, 602, 165, 16);
		frame.getContentPane().add(lblReponse);
		lblChat= new JLabel("Chat : ");
		lblChat.setBounds(794, 34, 165, 16);
		frame.getContentPane().add(lblChat);
		
		
		
		
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				//brushTool=panel.getGraphics();
				//point.add(event.getPoint());
				size=scrollBar.getValue();
				x=event.getX()- size/2;
	        	y=event.getY()- size/2;
				
				if(brushTool != null)
				{
					paint(brushTool);
				}
			}
		}); 
		panel.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent event) {
	        	size=scrollBar.getValue();
				x=event.getX()- size/2;
	        	y=event.getY()- size/2;
	        	
	        	if(brushTool == null)
				{
	        		brushTool=panel.getGraphics();
					paint(brushTool);
					System.out.println(brushTool);
				}
	        	else{
	        		paint(brushTool);
	        	}
	        	
	        }
	    });  
	   
	}
	public void update (Graphics g){
		paint(g);
	}
	public void paint(Graphics g)
	{
		 g.setColor(couleur);
		   g.fillRoundRect((int)x, (int)y, size, size, size, size);
	}
	public void initJoueur(String nom, String adr ,int scr)
	{
		nomJoueur=nom;
		adresse=adr;
		score=scr;
		lblNom.setText("Nom joueur : "+nomJoueur);
		lblScore.setText("Score : "+score);
		
	}	   
}




