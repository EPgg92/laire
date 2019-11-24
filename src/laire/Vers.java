package laire;


import java.util.*;

public class Vers {
	protected ArrayList <Integer> nbpiedVers= new ArrayList<Integer>();
	protected ArrayList <Mot> compositionVers=new ArrayList<Mot>();
	protected String rime= "";
	protected int numeroVers;

// Constructeur du vers
public Vers(String v, int numeroVers){
		this.numeroVers = numeroVers; // on assigne un num ro au vers
		StringTokenizer st = new StringTokenizer(v, " :+\",!'?-()_."); // on enl ve tous les caract res de ponctuations  et on tokenise le vers
        while (st.hasMoreTokens()){ // pour chaque mot du vers
        	String mot = st.nextToken().toLowerCase().replaceAll("[/^[^a-z]*$/]", ""); // on cr e un objet mot en le passant en minscule
        	if (mot != null){ // si le mot n'est pas nul apr s le test replaceall (toutes le choses qui ne sont pas des lettres)
        		if (Interface.dico.containsKey(mot)){ //  si le mot est dans le dictionnaire de l'interface
        			this.compositionVers.add(Interface.dico.get(mot)); // alors on l'ajoute   la liste de mots du vers
        		}else{
        			this.compositionVers.add(new Mot(mot)); // sinon on le cr e grace au ce constructeur de mot
        		}
        	}
        }
        this.nbpiedVers=compterPieds(compositionVers);// on initialise le nombre de pieds du ves
        Sonnet.dicoRime.setRime(this.phonemeFinal(),numeroVers);// on ajoute la rime au dictionnaire de rime du sonnet
        this.rime=Sonnet.dicoRime.comparateurRime(this.phonemeFinal(),numeroVers); // puis on teste si le vers rime
	}

// Cette m thode compte le nombre de pieds d'un vers gr ce au nombre de pieds des mots qui le composent
public ArrayList<Integer> compterPieds(ArrayList <Mot> cV){
		int min = 0, max=0;
		ListIterator <Mot> iter = cV.listIterator();
		ListIterator <Mot> iter2 = cV.listIterator();
		// on a besoin d'un deuxi me iterateur pour savoir si le mot et le suivant font prononc  le e muet ou non
		Mot mot2 =iter2.next();
		while (iter.hasNext()) {
			Mot mot = iter.next();
			if (iter2.hasNext()){
			mot2 =iter2.next();}
			ArrayList <Integer> nbPieds= mot.nbPieds;
			min+=nbPieds.get(0);// on ajoute la valeur du nombre de pieds maximal et minimal
			max+=nbPieds.get(nbPieds.size()-1);
			if (iter.hasNext()){
				if ( mot.efinal &&  mot2.premiereConsonne){ // si on a un e pas muets on ajoute une syllabe
				min+=1;
				max+=1;
				}
			}

		}
		ArrayList <Integer> nbpiedVers= new ArrayList <Integer>();
		for (int i=min; i<=max; i++){
			nbpiedVers.add(new Integer(i));// on ajoute chaque valeurs entre le premier et le dernier  l ment   la liste du nombre de pieds possible du vers
		}

		return nbpiedVers;
	}

	public Phoneme phonemeFinal(){
		return this.compositionVers.get(compositionVers.size()-1).phofinal();
	}

	public ArrayList<Integer> getNbpiedVers(){
		return nbpiedVers;
	}

	public static void main() {

	}
}
