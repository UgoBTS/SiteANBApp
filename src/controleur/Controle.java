package controleur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dal.AccesDonnees;
import modele.Administrateur;
import modele.Evenement;
import vue.FrmAjoutEvenement;
import vue.FrmConnexion;
import vue.FrmMain;
import vue.FrmModificationEvenement;

public class Controle {

	public static void changerFenetreConnexion(){
		FrmConnexion fenetre = new FrmConnexion();
		fenetre.setVisible(true);
	}
	
	public static Boolean seConnecter(String login, String pwd) {
		ArrayList<Administrateur> lesAdministrateurs = AccesDonnees.recupAdministrateursLoginPwd(login, pwd);
		if (lesAdministrateurs.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Erreur: mauvais login ou mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			changerFenetreMain();
			return true;
		}
	}
	
	public static void sauverEnBaseEvenements(int idevenement, String nom, String texte, Date date) {
		Evenement unEvenement = new Evenement();
		unEvenement.setIdevenement(idevenement);
		unEvenement.setNom(nom);
		unEvenement.setTexte(texte);
		unEvenement.setDate(date);
		AccesDonnees.addEvenement(unEvenement);
	}
	
	public static void changerFenetreMain(){
		FrmMain fenetre = new FrmMain();
		fenetre.setVisible(true);
	}
	
	public static void changerFenetreAjoutEvenement(){
		FrmAjoutEvenement fenetre = new FrmAjoutEvenement();
		fenetre.setVisible(true);
	}
	
	public static void changerFenetreModificationEvenement(String[] remplissage){
		FrmModificationEvenement fenetre = new FrmModificationEvenement();
		fenetre.setVisible(true);
		if (remplissage.length > 0) {
			fenetre.setTextFieldNomEvenement(remplissage[1]);
			fenetre.setTextFieldDateEvenement(remplissage[2]);
			fenetre.setTextFieldTexteEvenement(remplissage[3]);
			fenetre.setSIdevenement(remplissage[0]);
			fenetre.setSNom(remplissage[1]);
			fenetre.setSDate(remplissage[2]);
			fenetre.setSTexte(remplissage[3]);
		}
	}
	
	public static void modifierEnBaseEvenement(String idevenement, String nom, String texte, String date, String nouveau_idevenement, String nouveau_nom, String nouveau_texte, String nouveau_date) {
		AccesDonnees.updateEvenement(idevenement, nom, texte, date, nouveau_idevenement, nouveau_nom, nouveau_texte, nouveau_date);
	}
	
	public static ArrayList<ArrayList <String>> lireEvenementsRangesASC() {
		ArrayList<Evenement> lesEvenements = AccesDonnees.recupEvenementsRangesASC();
		ArrayList<ArrayList<String>> listeEvenements = new ArrayList<ArrayList<String>>();
		for (Evenement unEvenement : lesEvenements) {
			ArrayList<String> ligne = new ArrayList<String>();
			
			ligne.add(Integer.toString(unEvenement.getIdevenement()));
			ligne.add(unEvenement.getNom());
			ligne.add(unEvenement.getTexte());
			ligne.add(unEvenement.getDate().toString());

					
			listeEvenements.add(ligne);
		}
		
		return listeEvenements;
	}
	
	public static void supprimerDeLaBaseEvenements(String idevenement, String nom, String texte, String date){
		AccesDonnees.removeEvenement(idevenement, nom, texte, date);
	}
	
	public static void main(String[] args) {
		changerFenetreConnexion();
	}
}
