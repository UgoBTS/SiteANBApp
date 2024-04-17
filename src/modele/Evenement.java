package modele;

import java.util.Date;

public class Evenement {
	private int idevenement;
	private String nom;
	private String texte;
	private Date date;
	
	public int getIdevenement() {
		return this.idevenement;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getTexte() {
		return this.texte;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setIdevenement(int idevenement) {
		this.idevenement = idevenement;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
