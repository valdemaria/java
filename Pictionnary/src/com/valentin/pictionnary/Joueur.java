package com.valentin.pictionnary;

public class Joueur {
	String nom_Joueur;
	String signe;
	int score;

	public Joueur (String nomJoueur,String adresse,int score)
	{
		this.nom_Joueur=nom_Joueur;
		this.signe=signe;
		this.score=score;
	}
	private int incrScore(){
		score++;
		return score;
	}
	
}
