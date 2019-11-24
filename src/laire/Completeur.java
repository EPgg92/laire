package lairë;


import java.util.*;

public class Completeur {
	protected String phoneme;
	protected ArrayList<String> listeMot= new ArrayList<String>();
	protected String sortie="Il n'y a aucun mot correspondant!";
    // on initialise la valeur à ce message sui la liste de mots est vide
	
	public Completeur(ArrayList<Integer> piedsManq, int numvers, Map<String, Phoneme> dictionnaireRime, Map<Integer, String> dictionnaireTypeRime){
		// grace à dicorime on obtient l'écriture du phonéme final du vers avec lequel ce vers doit rimer
		this.phoneme= dictionnaireRime.get(dictionnaireTypeRime.get(numvers)).print();
		int j=piedsManq.get(piedsManq.size()-1);// j est la longueur des pieds manquant possibles 
		while(!(j==-1)){// pour chaque longueur possible
			if (Interface.dicoComp.get(phoneme).containsKey(j)){// si le phoneme est dans le dictionnairs compléteur
				this.listeMot.addAll(Interface.dicoComp.get(phoneme).get(j)); // on ajoute la liste de mot correspndan à cette longueur possible
			}
			j--;
		}
		if (!(listeMot.isEmpty())){// si la liste n'est pas vide
			this.sortie=listPrint();// on crée une nouvelle sortie
		}
    }
	
	// transforme la liste de mot en string affichable
	// la liste est ordonné par longeur décroissant puis par
	// ordre alphabétique
	public String listPrint(){
		String chaine="";
		Set<String> lm= new LinkedHashSet<String>(this.listeMot); // grace à cette linkedHashSet
		Iterator<String> iter= lm.iterator(); // tout simplement
		while(iter.hasNext()){
			chaine+=iter.next()+" ";	
		}
		return chaine;
	}

	public String getSortie(){
		return sortie;
	}
	
	public static void main(String[] args){

    }

}
