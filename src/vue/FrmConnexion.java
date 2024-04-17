package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import dal.AccesDonnees;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FrmConnexion extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldPwd;
	private Controle controle = new Controle();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConnexion frame = new FrmConnexion();
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
	public FrmConnexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 11, 69, 33);
		contentPane.add(lblLogin);
		
		JLabel lblMdp = new JLabel("Mot de passe");
		lblMdp.setBounds(10, 55, 110, 33);
		contentPane.add(lblMdp);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(164, 17, 142, 20);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		textFieldPwd = new JTextField();
		textFieldPwd.setColumns(10);
		textFieldPwd.setBounds(164, 61, 142, 20);
		contentPane.add(textFieldPwd);
		
		JButton btnConnexion = new JButton("Se connecter");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> lesArguments = new ArrayList<String>();
				lesArguments.add(textFieldLogin.getText());
				lesArguments.add(textFieldPwd.getText());
				Boolean connexion = controle.seConnecter(textFieldLogin.getText(), textFieldPwd.getText());
				if (connexion) {
					dispose();
				}
			}
		});
		btnConnexion.setBounds(343, 11, 116, 33);
		contentPane.add(btnConnexion);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(343, 55, 116, 33);
		contentPane.add(btnQuitter);
	}
}
