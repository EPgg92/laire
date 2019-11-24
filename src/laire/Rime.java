package laire;



import java.util.*;

public class Rime {
    protected Map<String,Phoneme> dictionnaireRime = new HashMap<String,Phoneme>();
    protected Map<Integer,String> dictionnaireTypeRime = new HashMap<Integer,String>();

    public Rime() { // l'objet rime initialise son dictionnaire de type de rime
        setDictionnaireTypeRime();
    }

    // on set le hashmap du dictionnaire du type de rime
    public void setDictionnaireTypeRime(){
        dictionnaireTypeRime.put(Integer.valueOf(1),"A"/*!*/);
        dictionnaireTypeRime.put(Integer.valueOf(2),"B"/*!*/);
        dictionnaireTypeRime.put(Integer.valueOf(3),"B");
        dictionnaireTypeRime.put(Integer.valueOf(4),"A");
        dictionnaireTypeRime.put(Integer.valueOf(5),"A");
        dictionnaireTypeRime.put(Integer.valueOf(6),"B");
        dictionnaireTypeRime.put(Integer.valueOf(7),"B");
        dictionnaireTypeRime.put(Integer.valueOf(8),"A");
        dictionnaireTypeRime.put(Integer.valueOf(9),"C"/*!*/);
        dictionnaireTypeRime.put(Integer.valueOf(10),"C");
        dictionnaireTypeRime.put(Integer.valueOf(11),"D"/*!*/);
        dictionnaireTypeRime.put(Integer.valueOf(12),"E"/*!*/);
        dictionnaireTypeRime.put(Integer.valueOf(13),"D");
        dictionnaireTypeRime.put(Integer.valueOf(14),"E");
        // *!* repr sent les valeurs que l'on va utilis  pour associ  le type de rime au phoneme dans setRime
    }

    // on associe les phonemes au types de rime en fonction de leur place dans le sonnet
    public void setRime(Phoneme p, Integer i){
         if (i == 1 || i==2 || i==9 || i==11 || i==12){
             dictionnaireRime.put(dictionnaireTypeRime.get(i), p);
         }

    }

    // test si la rime est correcte connu ou incorrect  selon le num ro du vers donn  et le phoneme de celui-ci
    public String comparateurRime(Phoneme p, Integer i){
        String comp="";
        // si l' criture du phoneme est la m me que l' criture du phoneme obtenue en get les valures des deux dictionnaire
        if ((getDictionnaireRime().get(dictionnaireTypeRime.get(i)).print()).equals(p.print())){
            comp="ok"; // alors la rime est ok
        }else{
        	comp="notOk"; // sinon la rime n'es pas ok
        }
        if (p.print()=="???"){ // si le phoneme est inconnu alors on le retourne comme tel
        	 comp="phonemeInconnu";
        }
        return comp;
    }



	public Map<String,Phoneme> getDictionnaireRime() {
		return dictionnaireRime;
	}

	public Map<Integer,String> getDictionnaireTypeRime() {
		return dictionnaireTypeRime;
	}

    public static void main(String[] args) {

    }

}
