package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import java.awt.Dimension;

public class FrmMain extends JFrame {

	private JPanel contentPane;
	private JTable tableDonnees;
	private Controle controle = new Controle();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String[][] genererTable(){
		ArrayList<ArrayList<String>> listeEvenements = controle.lireEvenementsRangesASC();
		int taille1 = 0;
		int taille2 = 0;
		for (ArrayList<String> ligne : listeEvenements) {
			taille1++;
		}
		for (ArrayList<String> ligne : listeEvenements) {
			for (String s : ligne) {
				taille2++;
			}
		}
		String[][] data = new String[taille2][taille1*taille2];
		int j = 0;
		for (ArrayList<String> ligne : listeEvenements) {
			String[] l = new String[taille2];
			int i = 0;
			for (String s : ligne) {
				l[i] = s;
				i++;
			}
			data[j] = l;
			j++;
		}
		return data;
	}
	
	public static void scrollTableToCellAt(JTable table, int row, int column) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport) table.getParent();

        Rectangle rect = table.getCellRect(row, column, true);

        Point pt = viewport.getViewPosition();

        rect.setLocation(rect.x - pt.x, rect.y - pt.y);

        viewport.scrollRectToVisible(rect);
    }

	/**
	 * Create the frame.
	 */
	public FrmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1215, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[][] data = genererTable();
		String[] columns = {"idevenement", "nom", "texte", "date"};
		
		
		JButton btnAjouterEvenement = new JButton("Ajouter un événement");
		btnAjouterEvenement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				String[] remplissage = new String[0];
				controle.changerFenetreAjoutEvenement();
			}
		});
		btnAjouterEvenement.setBounds(21, 330, 225, 32);
		contentPane.add(btnAjouterEvenement);
		
		JButton btnSupprimerPersonnel = new JButton("Supprimer un événement");
		btnSupprimerPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Erreur: aucun événement sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: aucun événement sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						int choix = JOptionPane.showConfirmDialog(null, "Etes vous sûr de vouloir supprimer cet événement?", "Attention", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE);
						if (choix == JOptionPane.YES_OPTION){
							String recup_idevenement = data[tableDonnees.getSelectedRow()][0];
							String recup_nom = data[tableDonnees.getSelectedRow()][1];
							String recup_texte = data[tableDonnees.getSelectedRow()][2];
							String recup_date = data[tableDonnees.getSelectedRow()][3];
							controle.supprimerDeLaBaseEvenements(recup_idevenement, recup_nom, recup_texte, recup_date);
							dispose();
							controle.changerFenetreMain();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs événements sélectionnés.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimerPersonnel.setBounds(256, 330, 225, 32);
		contentPane.add(btnSupprimerPersonnel);
		
		JButton btnModifierPersonnel = new JButton("Modifier un événement");
		btnModifierPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDonnees.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Erreur: aucun événement sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if (tableDonnees.getSelectedRowCount() == 1){
					if (data[tableDonnees.getSelectedRow()][0] == null) {
						JOptionPane.showMessageDialog(null, "Erreur: aucun événement sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						if (data[tableDonnees.getSelectedRow()][0] == null) {
							JOptionPane.showMessageDialog(null, "Erreur: la ligne sélectionnée est vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							dispose();
							String[] remplissage = new String[4];
							remplissage[0] = data[tableDonnees.getSelectedRow()][0];
							remplissage[1] = data[tableDonnees.getSelectedRow()][1];
							remplissage[3] = data[tableDonnees.getSelectedRow()][2];
							remplissage[2] = data[tableDonnees.getSelectedRow()][3];
							controle.changerFenetreModificationEvenement(remplissage);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erreur: plusieurs personnels sélectionnés.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifierPersonnel.setBounds(491, 330, 225, 32);
		contentPane.add(btnModifierPersonnel);
		
		JButton btnQuitterPersonnel = new JButton("Quitter");
		btnQuitterPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controle.changerFenetreConnexion();
			}
		});
		btnQuitterPersonnel.setBounds(961, 330, 225, 32);
		contentPane.add(btnQuitterPersonnel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(21, 11, 1165, 310);
		contentPane.add(scrollPane);
		tableDonnees = new JTable(data, columns) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		
		tableDonnees.setMaximumSize(new Dimension(2147483647, 2147483647));
		scrollPane.setViewportView(tableDonnees);
		tableDonnees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDonnees.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		
		scrollTableToCellAt(tableDonnees, 15, 1);
		scrollPane.getViewport().setViewPosition(new Point(0,0));
	}
}
