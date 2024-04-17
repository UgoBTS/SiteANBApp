package dal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import connexion.ConnexionBDD;
import modele.Administrateur;
import modele.Evenement;

public abstract class AccesDonnees {
	private static String url = "jdbc:mysql://localhost/site";
	private static String login_bdd = "root";
	private static String pwd_bdd = "";
	
	public static ArrayList<Administrateur> recupAdministrateursLoginPwd(String login, String pwd) {
		String sql = "select * from Administrateurs where login = ? and pwd = ?";
		ArrayList<Administrateur> lesAdministrateurs = new ArrayList<Administrateur>();
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(login);
		lesParams.add(pwd);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, lesParams);
		while (cn.read()) {
			Administrateur unAdministrateur = new Administrateur();
			unAdministrateur.setIdadministrateur((int)cn.field("idadministrateur"));
			unAdministrateur.setLogin((String)cn.field("login"));
			unAdministrateur.setPwd((String)cn.field("pwd"));
			lesAdministrateurs.add(unAdministrateur);
		}
		cn.close();
		return lesAdministrateurs;
	}
	
	public static ArrayList<Evenement> recupEvenementsRangesASC() {
		String sql = "select * from Evenements order by idevenement";
		ArrayList<Evenement> lesEvenements = new ArrayList<Evenement>();
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqSelect(sql, null);
		while (cn.read()) {
			Evenement unEvenement = new Evenement();
			unEvenement.setIdevenement((int)cn.field("idevenement"));
			unEvenement.setNom((String)cn.field("nom"));
			unEvenement.setTexte((String)cn.field("texte"));
			unEvenement.setDate((Date)cn.field("date"));
			lesEvenements.add(unEvenement);
		}
		cn.close();
		return lesEvenements;
	}
	
	public static void addEvenement(Evenement unEvenement) {
		String sql = "insert into Evenements (idevenement, nom, texte, date) values (?, ?, ?, ?)";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(unEvenement.getIdevenement());
		lesParams.add(unEvenement.getNom());
		lesParams.add(unEvenement.getTexte());
		lesParams.add(unEvenement.getDate());
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	public static void removeEvenement(String idevenement, String nom, String texte, String date) {
		String sql = "delete from Evenements where idevenement = ? and nom = ? and texte = ? and date = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(idevenement);
		lesParams.add(nom);
		lesParams.add(texte);
		lesParams.add(date);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
	
	public static void updateEvenement(String idevenement, String nom, String texte, String date, String nouveau_idevenement, String nouveau_nom, String nouveau_texte, String nouveau_date) {
		String sql = "update Evenements set idevenement = ?, nom = ?, texte = ?, date = ? where idevenement = ? and nom = ? and texte = ? and date = ?";
		ArrayList<Object> lesParams = new ArrayList<Object>();
		lesParams.add(nouveau_idevenement);
		lesParams.add(nouveau_nom);
		lesParams.add(nouveau_texte);
		lesParams.add(nouveau_date);
		lesParams.add(idevenement);
		lesParams.add(nom);
		lesParams.add(texte);
		lesParams.add(date);
		ConnexionBDD cn = ConnexionBDD.getInstance(url, login_bdd, pwd_bdd);
		cn.reqUpdate(sql, lesParams);
	}
}
