package laire;


import java.util.*;

public class Completeur {
	protected String phoneme;
	protected ArrayList<String> listeMot= new ArrayList<String>();
	protected String sortie="Il n'y a aucun mot correspondant!";
    // on initialise la valeur   ce message sui la liste de mots est vide

	public Completeur(ArrayList<Integer> piedsManq, int numvers, Map<String, Phoneme> dictionnaireRime, Map<Integer, String> dictionnaireTypeRime){
		// grace   dicorime on obtient l' criture du phon me final du vers avec lequel ce vers doit rimer
		this.phoneme= dictionnaireRime.get(dictionnaireTypeRime.get(numvers)).print();
		int j=piedsManq.get(piedsManq.size()-1);// j est la longueur des pieds manquant possibles
		while(!(j==-1)){// pour chaque longueur possible
			if (Interface.dicoComp.get(phoneme).containsKey(j)){// si le phoneme est dans le dictionnairs compl teur
				this.listeMot.addAll(Interface.dicoComp.get(phoneme).get(j)); // on ajoute la liste de mot correspndan   cette longueur possible
			}
			j--;
		}
		if (!(listeMot.isEmpty())){// si la liste n'est pas vide
			this.sortie=listPrint();// on cr e une nouvelle sortie
		}
    }

	// transforme la liste de mot en string affichable
	// la liste est ordonn  par longeur d croissant puis par
	// ordre alphab tique
	public String listPrint(){
		String chaine="";
		Set<String> lm= new LinkedHashSet<String>(this.listeMot); // grace   cette linkedHashSet
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
