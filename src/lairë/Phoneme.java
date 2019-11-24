package lairë;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

public class Phoneme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String prononciation;
	protected static char[] phonemes;
	protected static Set<Character> ensC = new HashSet<Character>(Arrays.asList('n','N','s','l','m','c','t','Z','w','d','f','r','b','j','k','£','v','z','g','p','\'','/'));
	protected static Set<Character> ensV = new HashSet<Character>(Arrays.asList('ï','û','è','é','E','O','ê','î','@','â','e','a','í','u','ô','A','y','^','o','ÿ','ý','i'));
	
	// le constructeur de phoneme set juste un String
	public Phoneme(String phonemeIn){
		this.prononciation=phonemeIn;
	}
	
	// retourne une liste de de phoneme à partir du tableau de caractè de prononciation 
	 public ArrayList <Phoneme> phonetisation(){
	    	phonemes = prononciation.toCharArray(); // on 
	    	ArrayList <Phoneme> prononciationMot= new ArrayList <Phoneme>();// phoneme en création
	    	String phonemeCrea = "";
			for (int i=0; i<phonemes.length;i++){ // pour chaque lettre
	    		if (ensC.contains(phonemes[i])){ // si c'est une consonne 
	    			phonemeCrea+=phonemes[i]; // on l'ajoute au phoneme en création 
	    			prononciationMot.add(new Phoneme(phonemeCrea));// puis on l'ajoute à la liste de phonemes de la pronociation du mot
	    			phonemeCrea = "";
	    		} else if (ensV.contains(phonemes[i])){// si c'est un phoneme voyelle 
	    				phonemeCrea+=phonemes[i];// on l'ajoute 
	    				if (i==phonemes.length-1){// est si il est le denier du mot
	    	    				prononciationMot.add(new Phoneme(phonemeCrea));// on crée le phonemes
	    	    				phonemeCrea = "";
	    				}
	    		}
	    		
	    	}
			return prononciationMot;
	    	
	    }

    // retoune le dernier phoneme de phonetisation 
    public Phoneme phonemeFinal(){
    	ArrayList <Phoneme> prononciationMot= this.phonetisation();
		Phoneme phonemeFinal = prononciationMot.get(prononciationMot.size()-1);
		return phonemeFinal;
    	
    }
	
    // crée un phoneme inconnu pour si on ne connait pas la prononciation du mot 
	public static Phoneme phonemeInconnu(){
		return new Phoneme("???");
	}
	
	public String print(ArrayList <Phoneme> prononciationMot){
		String p="";
		ListIterator <Phoneme> iter = prononciationMot.listIterator();
		while (iter.hasNext()) {
		Phoneme elem = iter.next();
		p+=elem.print() +", ";
		System.out.println(p);
		}
			
		return p;
	}

	public String print(){
		return prononciation;
	}
	
	public static void main(String[] args) {


	}

}

