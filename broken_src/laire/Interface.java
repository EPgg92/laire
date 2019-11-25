package laire;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class Interface {

	protected JFrame frame;//La fen tre
	protected JTextField titre;//Zone de texte o  l'utilisateur peut entrer le titre de son sonnet
	protected ArrayList <JLabel> labels = new ArrayList<JLabel>();//Une liste de JLabel o  des indications sur le sonnet pourront s'afficher
	//protected static Dictionnaire ObjDictionnaire = new Dictionnaire( "FPD.txt" );//Un dictionnaire de la langue fran aise avec phon tisation des mots
	protected static HashMap<String,Mot> dico =setDico();// ObjDictionnaire.getDictionnaire();
	protected static HashMap<String,HashMap<Integer,ArrayList<String>>> dicoComp = setDicoComp();// ObjDictionnaire.getdicoCompleteur();
	protected static HashMap<String,Integer> selection = new HashMap<String,Integer>();//HashMap qui aidera au choix du nombre de pieds
	protected ImageIcon img = new ImageIcon("icoLaire.gif");//L'icone du programme
	protected String init = "----";//La valeur initiale des JLabel qui afficheront les indications de rimes et de pieds
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {//Lancement de l'interface
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interface() {
		initialize();//Constructeur de l'interface
		frame.setTitle("Lair  ~  diteur de sonnets");//Titre de l'interface
		frame.setIconImage(img.getImage());//Assignation de l'icone
		selection.put("Alexandrins (12 pieds)", 12);//Remplissage du HashMap qui permettra le choix du nombre de pieds
		selection.put("Octosyllabes (8 pieds)", 8);
		selection.put("D casyllabes (10 pieds)", 10);
	}


	/**
	 * Initialisation du contenu de frame, la fen tre du programme
	 */
	public void initialize() {//Initialisation de la fen tre, sa taille, son aspect
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		/**
		 * Initialisation des JLabels qui indiquent   l'utilisateur quel vers il est en train d' crire et quelle rime il doit respecter*/
		JLabel A1 = new JLabel("1A");
		A1.setBounds(47, 124, 22, 14);
		frame.getContentPane().add(A1);

		JLabel B1 = new JLabel("2B");
		B1.setBounds(47, 141, 22, 14);
		frame.getContentPane().add(B1);

		JLabel B2 = new JLabel("3B");
		B2.setBounds(47, 158, 22, 14);
		frame.getContentPane().add(B2);

		JLabel A2 = new JLabel("4A");
		A2.setBounds(47, 174, 22, 14);
		frame.getContentPane().add(A2);

		JLabel A3 = new JLabel("5A");
		A3.setBounds(47, 211, 22, 14);
		frame.getContentPane().add(A3);

		JLabel B3 = new JLabel("6B");
		B3.setBounds(47, 226, 22, 14);
		frame.getContentPane().add(B3);

		JLabel B4 = new JLabel("7B");
		B4.setBounds(46, 244, 23, 14);
		frame.getContentPane().add(B4);

		JLabel A4 = new JLabel("8A");
		A4.setBounds(46, 260, 23, 14);
		frame.getContentPane().add(A4);

		JLabel C1 = new JLabel("9C");
		C1.setBounds(46, 295, 23, 14);
		frame.getContentPane().add(C1);

		JLabel C2 = new JLabel("10C");
		C2.setBounds(46, 311, 23, 14);
		frame.getContentPane().add(C2);

		JLabel D1 = new JLabel("11D");
		D1.setBounds(46, 328, 23, 14);
		frame.getContentPane().add(D1);

		JLabel E1 = new JLabel("12E");
		E1.setBounds(46, 362, 23, 14);
		frame.getContentPane().add(E1);

		JLabel D2 = new JLabel("13D");
		D2.setBounds(46, 380, 23, 14);
		frame.getContentPane().add(D2);

		JLabel E2 = new JLabel("14E");
		E2.setBounds(46, 399, 23, 14);
		frame.getContentPane().add(E2);

		/**
		 * Initialisation des JLabel qui afficheront les informations de rime et de pieds pour chaque vers
		 * */

		final JLabel A1pieds = new JLabel(init);
		A1pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		A1pieds.setBounds(362, 123, 189, 14);
		frame.getContentPane().add(A1pieds);

		final JLabel B1pieds = new JLabel(init);
		B1pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		B1pieds.setBounds(362, 140, 189, 14);
		frame.getContentPane().add(B1pieds);

		final JLabel B2pieds = new JLabel(init);
		B2pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		B2pieds.setBounds(362, 157, 189, 14);
		frame.getContentPane().add(B2pieds);

		final JLabel A2pieds = new JLabel(init);
		A2pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		A2pieds.setBounds(362, 173, 189, 14);
		frame.getContentPane().add(A2pieds);

		final JLabel A3pieds = new JLabel(init);
		A3pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		A3pieds.setBounds(362, 210, 189, 14);
		frame.getContentPane().add(A3pieds);

		final JLabel B3pieds = new JLabel(init);
		B3pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		B3pieds.setBounds(362, 225, 189, 14);
		frame.getContentPane().add(B3pieds);

		final JLabel B4pieds = new JLabel(init);
		B4pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		B4pieds.setBounds(362, 243, 189, 14);
		frame.getContentPane().add(B4pieds);

		final JLabel A4pieds = new JLabel(init);
		A4pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		A4pieds.setBounds(362, 259, 189, 14);
		frame.getContentPane().add(A4pieds);

		final JLabel C1pieds = new JLabel(init);
		C1pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		C1pieds.setBounds(362, 294, 189, 14);
		frame.getContentPane().add(C1pieds);

		final JLabel C2pieds = new JLabel(init);
		C2pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		C2pieds.setBounds(362, 310, 189, 14);
		frame.getContentPane().add(C2pieds);

		final JLabel D1pieds = new JLabel(init);
		D1pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		D1pieds.setBounds(362, 327, 189, 14);
		frame.getContentPane().add(D1pieds);

		final JLabel E1pieds = new JLabel(init);
		E1pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		E1pieds.setBounds(362, 361, 189, 14);
		frame.getContentPane().add(E1pieds);

		final JLabel D2pieds = new JLabel(init);
		D2pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		D2pieds.setBounds(362, 379, 189, 14);
		frame.getContentPane().add(D2pieds);

		final JLabel E2pieds = new JLabel(init);
		E2pieds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		E2pieds.setBounds(362, 398, 189, 14);
		frame.getContentPane().add(E2pieds);


		titre = new JTextField();//La zone o  l'utilisateur peut intituler son sonnet
		titre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		titre.setBounds(79, 75, 279, 20);
		frame.getContentPane().add(titre);
		titre.setColumns(10);

		JLabel lblTitre = new JLabel("Titre:");//Un JLabel indiquant la fonction du JTextField titre
		lblTitre.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblTitre.setBounds(23, 77, 45, 14);
		frame.getContentPane().add(lblTitre);

		final JTextPane textPane = new JTextPane();//La zone o  l'utilisateur peut  crire son sonnet
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textPane.setBounds(79, 119, 279, 332);
		frame.getContentPane().add(textPane);

		JLabel lblPome = new JLabel("Po\u00E8me:");//Un JLabes indiquant o  l'utilisateur peut  crire son sonnet
		lblPome.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblPome.setBounds(23, 106, 57, 14);
		frame.getContentPane().add(lblPome);

		JScrollPane scrollPane_1 = new JScrollPane();//La barre scroll qui permet de descendre plus bas dans le JTextPane motsProposes
		scrollPane_1.setBounds(549, 112, 256, 339);
		frame.getContentPane().add(scrollPane_1);

		JTextPane motsProposes = new JTextPane();//La zone de texte contenant les mots propos s   l'utilisateur pour compl ter son vers
		motsProposes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		motsProposes.setEditable(false);
		scrollPane_1.setViewportView(motsProposes);


		JLabel lblMotsProposs = new JLabel("Mots Propos\u00E9s:");//Un Label pour indiquer la fonction du JTextPane motsProposes   l'utilisateur
		lblMotsProposs.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblMotsProposs.setBounds(549, 91, 116, 23);
		frame.getContentPane().add(lblMotsProposs);

		JComboBox<String> nbPiedsChoisi = new JComboBox<String>();//Une ComboBox o  l'utilisateur peut choisir le nombre de pieds de son sonnet
		nbPiedsChoisi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		nbPiedsChoisi.setBounds(549, 24, 256, 33);
		nbPiedsChoisi.addItem("Alexandrins (12 pieds)");
		nbPiedsChoisi.addItem("D casyllabes (10 pieds)");
		nbPiedsChoisi.addItem("Octosyllabes (8 pieds)");
		frame.getContentPane().add(nbPiedsChoisi);

		JLabel lblSlectionnerLeNombre = new JLabel("S\u00E9lectionner le nombre de pieds:");//Un label expliquant la fonction de la ComboBox
		lblSlectionnerLeNombre.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblSlectionnerLeNombre.setBounds(549, 8, 256, 14);
		frame.getContentPane().add(lblSlectionnerLeNombre);

		JTextField versN = new JTextField();//La zone de texte o  l'utilisateur indique que vers il aimerait faire compl ter
		versN.setBounds(591, 75, 86, 20);
		frame.getContentPane().add(versN);
		versN.setColumns(10);

		JLabel lblVersN = new JLabel("Vers n\u00B0:");//Le JLabel indiquant ce qu'est le textField pr c dent
		lblVersN.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblVersN.setBounds(549, 79, 46, 14);
		frame.getContentPane().add(lblVersN);

		labels.add(A1pieds);//Addition des labels dans leur tableau
		labels.add(B1pieds);
		labels.add(B2pieds);
		labels.add(A2pieds);
		labels.add(A3pieds);
		labels.add(B3pieds);
		labels.add(B4pieds);
		labels.add(A4pieds);
		labels.add(C1pieds);
		labels.add(C2pieds);
		labels.add(D1pieds);
		labels.add(E1pieds);
		labels.add(D2pieds);
		labels.add(E2pieds);

		/********************************************************************************************
		 * */

		JButton btnEnregistrer = new JButton("Enregistrer");//Le bouton Enregistrer
		btnEnregistrer.setBackground(new Color(153, 102, 153));
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String titreSonnet = titre.getText();//r cup re le titre donn  au sonnet
				String fileName = titreSonnet.replaceAll("[/^[^a-zA-Z0-9]*$/]", "");//Enl ve les caract re sp ciux et les accents
				String textePoeme = textPane.getText();//R cup re le texte du po me
				File poem = new File(fileName + ".txt");//Cr e un fichier txt avec le titre modifi  d po me
				try{
					FileWriter fw = new FileWriter(poem);
					fw.write(titreSonnet);// crit le titre original du sonnet dans le fichier txt
					fw.write("\r\n\r\n");//va   la ligne
					fw.write(textePoeme);// crit le po me dans le fichier txt
					fw.close();
				}catch(Exception ex){

				}
				JOptionPane.showMessageDialog(null, "C'est enregistr !");//Popup communiquant   l'utilisateur que son texte est enregistr
			}
		});
		btnEnregistrer.setFont(new Font("Vivaldi", Font.PLAIN, 29));
		btnEnregistrer.setBounds(79, 8, 279, 56);
		frame.getContentPane().add(btnEnregistrer);

		JButton btnCompleterVers = new JButton("Compl\u00E9ter vers");//Bouton compl ter vers
		btnCompleterVers.setFont(new Font("Vivaldi", Font.PLAIN, 29));
		btnCompleterVers.setForeground(Color.WHITE);
		btnCompleterVers.setBackground(new Color(153, 102, 153));
		btnCompleterVers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int limite=selection.get(String.valueOf(nbPiedsChoisi.getSelectedItem()));
				String texteEntre = textPane.getText();//R cup re le texte du sonnet
				Sonnet s1 = new Sonnet(texteEntre);//cr e un objet contenant ce texte
				String numVersStr = versN.getText();//R cup re le num ro du vers   compl ter

				try {
					/**Trouve des mots dans le dictionnaires dont le
					 * phon me final correspond   la rime voulue, et
					 * dont le nombre de pieds est  gal ou inf rieur au nombre de pieds
					 * manquants dans le vers   compl ter
					*/
					int numVers=Integer.parseInt(numVersStr);
					ArrayList<Integer> nbpiedVers= s1.getALV().get(numVers-1).getNbpiedVers();
					ArrayList<Integer> piedsManq = new ArrayList<Integer>();
					int j=0;
					Map<String,Phoneme> dictionnaireRime =s1.getdicoRime().getDictionnaireRime();
					Map<Integer,String> dictionnaireTypeRime=s1.getdicoRime().getDictionnaireTypeRime();
					while( j<nbpiedVers.size()){
						piedsManq.add(-(nbpiedVers.get(j)-limite));
						j++;
					}

				//Sortie des mots propos s et gestion d'erreurs
				Completeur c= new Completeur( piedsManq,  numVers,dictionnaireRime, dictionnaireTypeRime);
				motsProposes.setText(c.getSortie());
				} catch (NumberFormatException e){
					motsProposes.setText("Veuillez entrer le nombre entre 1 et 14 correspondant au vers que vous souhaitez compl ter.");
				} catch (IndexOutOfBoundsException e){
					motsProposes.setText("Veuillez entrer le nombre entre 1 et 14 correspondant au vers que vous souhaitez compl ter.");
				} catch (NullPointerException e){
					motsProposes.setText("Rime inconnue, mots introuvables.");
				}

			}
		});
		btnCompleterVers.setBounds(549, 459, 256, 56);
		frame.getContentPane().add(btnCompleterVers);

		JButton btnVrifierRimes = new JButton("V\u00E9rifier rimes et pieds");//Bouton v rifier rimes et pieds
		btnVrifierRimes.setBackground(new Color(153, 102, 153));
		btnVrifierRimes.setForeground(Color.WHITE);
		btnVrifierRimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				String texteEntre = textPane.getText();//R cup re le texte du sonnet
				Sonnet s1 = new Sonnet(texteEntre);//cr e un objet sonnet avec le texte

				//String updateText =this.beautifieur(texteEntre);
				//textPane.setText(updateText);
				ArrayList<String> ali=s1.getALI() ;
				int i=0;
				while ( i <= ali.size()-1){

					labels.get(i).setText(ali.get(i));//Modifie le texte des labels en cons quent

					i++;
				}
				while ( i < labels.size()){
					labels.get(i).setText(init);

					i++;
				}
				ali.clear();
			}

			/*private String beautifieur(String texteEntre) {
				String sortie="";
				HashSet<Integer> ligne= new HashSet<Integer>();
				ligne.add(4);
				ligne.add(9);
				ligne.add(13);

				//Pattern p1 = Pattern.compile(" *\r *| *\n *");
				StringTokenizer st = new StringTokenizer(texteEntre, "(\n)");
				int i =0;
				while (st.hasMoreTokens()){
					String str = st.nextToken();

					//Matcher m1 = p1.matcher(str);
					System.out.print(i+" ");
					//System.out.println(ligne.contains(i) && m1.matches());
					if (ligne.contains(i)){
						System.out.println("yolo");
						sortie+="\r";
					}
					sortie+=str;
					i++;
				}
				System.out.print(sortie);
				return sortie;
			}*/

		});
		btnVrifierRimes.setFont(new Font("Vivaldi", Font.PLAIN, 29));
		btnVrifierRimes.setBounds(79, 462, 279, 56);
		frame.getContentPane().add(btnVrifierRimes);





	}

	/**
	 * Les m thodes suivantes servent   lire les dictionnaire stocker dans les .sav !
	 *
	 * */
	@SuppressWarnings("unchecked")
	public static HashMap<String,Mot> setDico(){
		HashMap<String,Mot> m =new HashMap<String,Mot>();
		try
        {

           FileInputStream fichier = new FileInputStream("dictionnaire.sav");
	    	ObjectInputStream fluxObjet = new ObjectInputStream(fichier);
	    	 m = (HashMap<String,Mot>) fluxObjet.readObject();
	    	fluxObjet.close();
	    	fichier.close();

	     }
	     catch (Exception e) {
	    	e.printStackTrace();
	    	System.exit(1);
	     }

		return m;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String,HashMap<Integer,ArrayList<String>>> setDicoComp(){
		HashMap<String,HashMap<Integer,ArrayList<String>>> m =new HashMap<String,HashMap<Integer,ArrayList<String>>>();
		try
        {

           FileInputStream fichier = new FileInputStream("dicoCompleteur.sav");
	    	ObjectInputStream fluxObjet = new ObjectInputStream(fichier);
	    	 m = (HashMap<String,HashMap<Integer,ArrayList<String>>>) fluxObjet.readObject();
	    	fluxObjet.close();
	    	fichier.close();

	     }
	     catch (Exception e) {
	    	e.printStackTrace();
	    	System.exit(1);
	     }

		return m;

	}
}
