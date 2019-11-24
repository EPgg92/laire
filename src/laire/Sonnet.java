package lair�;

import java.util.*;

public class Sonnet {
	protected ArrayList<String> ALI= new ArrayList<String>();
	protected ArrayList<Vers> ALV= new ArrayList<Vers>();
	protected static Rime dicoRime;
	protected String texteEntre;
	
	//Constructeur Sonnet
	public Sonnet(String texteEntre) {
		this.texteEntre = texteEntre; // on prend le texte envoy� par l'interface
		dicoRime = new Rime(); // on initialise le dictionnaire de rime
		DiviseurVers(); // on divise le sonnet en vers pour remplir ALI (ArrayList Information) & ALV (ArrayList Vers)
		}
	
	public void DiviseurVers(){
		Vers v; // initialisation du vers
		StringTokenizer st = new StringTokenizer(texteEntre, "(\n|\r)"); // d�coupage du texte avec StringTokenizer
		int i =0;
		while (st.hasMoreTokens()){ // pour chaque vers du texte 
			String str = st.nextToken();
			i++;
			try{
				v = new Vers(str, i);// on cr�e un objet vers
				v.numeroVers+=1;
				ALV.add(v); // qu'on ajoute � ALV
				ArrayList<Integer> nbpied=v.nbpiedVers;
				String rime =v.rime;
				ALI.add(concat(nbpied, rime)); // et nous sert � ajouter des information � ALI
			}catch (Exception e){
				System.out.println(e.getMessage());
			}

		}
		
	}
	
	// Concat�ne deux chaines de caract�re pour cr�er l'information pour ALI
	public static String concat(ArrayList <Integer> nbpied, String rime ) {
		String nbP = retournerPied(nbpied);
		String nbR = retournerRime(rime);
		String elemAli = nbP + nbR;
		return elemAli;
	}
	
	// Cr�e une chaine caract�re � partir d'une liste de nombre qui repr�sente le nombre de piedss possibles
	public static String retournerPied(ArrayList<Integer> nbpied){
		String nbP = "???" ;
		try{
			nbP =""+nbpied.get(0);
            for (int i=1;i<nbpied.size(); i++){
                if (i==nbpied.size()-1){
                    nbP+=" ou "+ nbpied.get(i);
                }else{
                    nbP+=", "+ nbpied.get(i);
                }
            }
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return nbP;
	}
	
	// Cr�e une chaine caract�re � partir d'une String qui indique si la rime est connu, correcte ou incorrecte
	public static String retournerRime(String rime){
		String nbR="Mot Inconnu";
		try{
			if (rime=="ok"){
				nbR="| Rime correcte.";
			}else if(rime=="notOk"){
				nbR="| Rime incorrecte!";
			}else{
				nbR="| Rime inconnue ?";
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return nbR;
	}
	
	public ArrayList<String> getALI() {
		return ALI;
	}
	
	public ArrayList<Vers> getALV() {
		return ALV;
	}
	
	public Rime getdicoRime() {
		return dicoRime;
	}

	public static void main(String[] args) {
	}
}
