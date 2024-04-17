package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmModificationEvenement extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNomEvenement;
	private JTextField textFieldDateEvenement;
	private JTextField textFieldTexteEvenement;
	private Controle controle;
	
	private String s_idevenement;
	private String s_nom;
	private String s_date;
	private String s_texte;
	
	public void setTextFieldNomEvenement(String s) {
		textFieldNomEvenement.setText(s);
	}

	public void setTextFieldDateEvenement(String s) {
		textFieldDateEvenement.setText(s);
	}
	
	public void setTextFieldTexteEvenement(String s) {
		textFieldTexteEvenement.setText(s);
	}
	
	public void setSIdevenement(String s_idevenement) {
		this.s_idevenement = s_idevenement;
	}
	
	public void setSNom(String s_nom) {
		this.s_nom = s_nom;
	}
	
	public void setSDate(String s_date) {
		this.s_date = s_date;
	}
	
	public void setSTexte(String s_texte) {
		this.s_texte = s_texte;
	}
	
	/**
	 * Permet de vérifier que l'information donnée est bel et bien une date.
	 * @param s: le String qu'on essaye de convertir en Date.
	 * @return true si l'information donnée est une date, false sinon.
	 */
	public Boolean estUneDate(String s) {
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(s);
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmModificationEvenement frame = new FrmModificationEvenement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmModificationEvenement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomEvenement = new JLabel("Nom");
		lblNomEvenement.setBounds(10, 11, 49, 14);
		contentPane.add(lblNomEvenement);
		
		JLabel lblDateEvenement = new JLabel("Date");
		lblDateEvenement.setBounds(10, 36, 49, 14);
		contentPane.add(lblDateEvenement);
		
		JLabel lblTexteEvenement = new JLabel("Texte");
		lblTexteEvenement.setBounds(10, 61, 49, 14);
		contentPane.add(lblTexteEvenement);
		
		textFieldNomEvenement = new JTextField();
		textFieldNomEvenement.setBounds(146, 8, 150, 20);
		contentPane.add(textFieldNomEvenement);
		textFieldNomEvenement.setColumns(10);
		
		textFieldDateEvenement = new JTextField();
		textFieldDateEvenement.setColumns(10);
		textFieldDateEvenement.setBounds(146, 33, 150, 20);
		contentPane.add(textFieldDateEvenement);
		
		textFieldTexteEvenement = new JTextField();
		textFieldTexteEvenement.setColumns(10);
		textFieldTexteEvenement.setBounds(146, 58, 150, 20);
		contentPane.add(textFieldTexteEvenement);
		
		JButton btnAjouterPersonnel = new JButton("Modifier événement");
		btnAjouterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNomEvenement.getText().isBlank() ||  textFieldDateEvenement.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Erreur: tous les champs doivent �tre remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					if (estUneDate(textFieldDateEvenement.getText())) {
						controle.modifierEnBaseEvenement(s_idevenement, s_nom, s_texte, s_date, s_idevenement, textFieldNomEvenement.getText(), textFieldTexteEvenement.getText(), textFieldDateEvenement.getText());
						JOptionPane.showMessageDialog(null, "L'événement a correctement été modifié.", "Succès", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						controle.changerFenetreMain();
						
					} else {
						JOptionPane.showMessageDialog(null, "Erreur: l'une des valeurs insérée n'est pas une date.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAjouterPersonnel.setBounds(439, 11, 137, 64);
		contentPane.add(btnAjouterPersonnel);
		
		JButton btnQuitterPersonnel = new JButton("Annuler");
		btnQuitterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controle.changerFenetreMain();
			}
		});
		btnQuitterPersonnel.setBounds(439, 86, 137, 50);
		contentPane.add(btnQuitterPersonnel);
		
		JLabel lblInformation = new JLabel("Format: Ann\u00E9e-Mois-Jour");
		lblInformation.setBounds(10, 94, 197, 14);
		contentPane.add(lblInformation);
		
		JLabel lblExemple = new JLabel("Exemple: 2002-05-17 pour le 17 mai 2002");
		lblExemple.setBounds(10, 119, 292, 14);
		contentPane.add(lblExemple);
	}

}
