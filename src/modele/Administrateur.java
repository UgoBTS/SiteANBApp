package modele;

public class Administrateur {
	private int idadministrateur;
	private String login;
	private String pwd;
	
	public int getIdadministrateur() {
		return this.idadministrateur;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setIdadministrateur(int idadministrateur) {
		this.idadministrateur = idadministrateur;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
