package lairë;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.*;

public class Dictionnaire {
	
	protected HashMap<String, Mot> dictionnaire = new HashMap<String,Mot>();
	protected HashMap<String,HashMap<Integer,ArrayList<String>>> dicoCompleteur= new HashMap<String,HashMap<Integer,ArrayList<String>>>();
	
	// le constructeur de Dictionnaire va initialiser c'est deux HashMap ci-dessus; 
    public Dictionnaire(String fileName )
    {
        FileReader theFile;
        BufferedReader fileIn = null;
        String oneLine;

        try
        {
            theFile = new FileReader( fileName );//après avoir ouvert le fichier
            fileIn  = new BufferedReader( theFile ); // puis de l'avoir lu
            while( ( oneLine = fileIn.readLine( ) ) != null ){// on le parcours ligne par ligne
            	// en matchant toutes les expressions correspondant à ce pattern
            	Pattern p = Pattern.compile("([A-Za-zùëèéçÉêûæœüôáàïöŒâóî]*);([a-zBWÉùMôjO/ë.îYñCâ3FADVNKXü0T@QEê1íGLéU£ûÿ4IèïR^ýHJà7PZ2Sç-]*) *");
            	Matcher m = p.matcher(oneLine);
	            	if (m.matches()){
		            	String ecritureIn = m.group(1).toLowerCase();// le premier groupe matché est le l'écriture du mot en minuscule
		            	Phoneme prononciation= new Phoneme(m.group(2));// le deuximée la prononciation 
		            	ArrayList <Phoneme> prononciationMotIn= prononciation.phonetisation(); 
		            	Phoneme phonemeFinalIn=prononciation.phonemeFinal();
		            	// on crée le mot avec les différentes variables 
		            	Mot mot = new Mot(ecritureIn,phonemeFinalIn,prononciationMotIn);
		            	// si le phoneme final du mot est pa dans le dictionnaire de complétion
		            	if (dicoCompleteur.containsKey(phonemeFinalIn.print())){
		            		HashMap<Integer,ArrayList<String>> tripied = dicoCompleteur.get(phonemeFinalIn.print());
		            		ArrayList<Integer> nbpied= mot.nbPieds;
		            		int j=0;
		            		while(j<nbpied.size()){// alors pour chaque longueur possible du mot
		            			ArrayList<String> listeTempMot;
		            			if (tripied.containsKey(nbpied.get(j))){// si la longueur est déjà dans le dicionnaire
		            				listeTempMot= tripied.get(nbpied.get(j));
		            				listeTempMot.add(ecritureIn); // on ajoute le mot à la liste
		            				tripied.put(nbpied.get(j), listeTempMot);
		            			}else{
		            				listeTempMot = new ArrayList<String>(); // sinon on crée la liste
		            				listeTempMot.add(ecritureIn); // avec le mot dedans
		            				tripied.put(nbpied.get(j), listeTempMot);// puis on ajoute la liste
		            			}
		            			j++;
		            		}
		            		dicoCompleteur.put(phonemeFinalIn.print(), tripied);// puis on ajoute à l'écriture du phonemes le hashmap des longueurs de pieds
		            	}else{ // mais si le mot n'es pas dans le dictionnaire
		            		HashMap<Integer,ArrayList<String>> tripied= new HashMap<Integer,ArrayList<String>>();
		            		// on crée le map du ductionnaire des longueur
		            		ArrayList<Integer> nbpied= mot.nbPieds;
		            		int j=0;
		            		while(j<nbpied.size()){
		            			ArrayList<String> listeTempMot;
		            			listeTempMot = new ArrayList<String>();
		            			listeTempMot.add(ecritureIn);
		            			tripied.put(nbpied.get(j), listeTempMot); // à qui on ajoute le premier mots dans la liste 
		            			j++;
		            		}
		            		dicoCompleteur.put(phonemeFinalIn.print(), tripied);	// et on ajoute ce map au dictionnaire compléteur à l'écriture du phonemes 
		            	}
		            	dictionnaire.put(ecritureIn, mot);// et enfin on ajoute le mot à l'entrée de sont écriture
	            	}
            	}
          }   	
        
        catch( IOException e )
          {  System.out.println( e ); }
        finally
        {
            try
            {
                if(fileIn != null )
                    fileIn.close( );
            }
            catch( IOException e )
              { }
        }
       
    }
    
    
    public HashMap<String, Mot> getDictionnaire(){
    	return dictionnaire;
    }

    public HashMap<String,HashMap<Integer,ArrayList<String>>> getdicoCompleteur(){
    	return dicoCompleteur;
    }

	public static void print(HashMap<String, Mot> dico){
    	Set <Map.Entry<String, Mot>> paires = dico.entrySet(); // ensemble de paires
    	Iterator <Map.Entry<String, Mot>> iter = paires.iterator(); // itï¿½rateur
    	while (iter.hasNext()) {
    	Map.Entry <String, Mot> paire = iter.next(); // paire courante
    	//System.out.println(paire); // affichage de la paire courante
    	//String cle = paire.getKey(); // accès ï¿½ la clï¿½
    	Mot val = paire.getValue(); // accï¿½s ï¿½ la valeur
    	val.print();
    	
    	}
	}
	
	public static void main(String[] args){
		Dictionnaire d1= new Dictionnaire("FPD.txt");
		//HashMap<String, Mot> dictionnaire = d1.getDictionnaire();
		//dictionnaire.get("informatique").print(); 
		//HashMap<String,HashMap<Integer,ArrayList<String>>> dic = d1.getdicoCompleteur();
		//System.out.println(dic);
		

		 HashMap<String, Mot> dictionnaire = d1.getDictionnaire();
		 HashMap<String,HashMap<Integer,ArrayList<String>>> dicoCompleteur = d1.getdicoCompleteur();
        try
                {
                    FileOutputStream fichier = new FileOutputStream("dictionnaire.sav");
                    ObjectOutputStream fluxObjet = new ObjectOutputStream(fichier);
                    fluxObjet.writeObject(dictionnaire);
                    fluxObjet.flush();
                    fluxObjet.close();
                    fichier.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            
	
    try
            {
                FileOutputStream fichier = new FileOutputStream("dicoCompleteur.sav");
                ObjectOutputStream fluxObjet = new ObjectOutputStream(fichier);
                fluxObjet.writeObject(dicoCompleteur);
                fluxObjet.flush();
                fluxObjet.close();
                fichier.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        
		
		
    }
}
	
	
