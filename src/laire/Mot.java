package laire;

import java.util.regex.*;
import java.io.*;
import java.util.ArrayList;

public class Mot implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String ecriture;
	protected ArrayList <Integer> nbPieds;
	protected boolean dierese = false;
	protected Phoneme phonemeFinal;
	protected ArrayList <Phoneme> prononciationMot;
	protected boolean efinal=false;
	protected boolean premiereConsonne=false;

	/**
	 * On peut construire un objet Mot de plusieures fa on
	 * celle-ci est r server   Dictionnaire */
	public Mot(String ecritureIn, Phoneme phonemeFinalIn, ArrayList <Phoneme> prononciationMotIn){
		ecriture=ecritureIn.toLowerCase();
		this.analyseConsonneVoyelle();
		nbPieds=compterNbPiedsMots(ecriture);
		if (nbPieds.size()>1){dierese=true;}
		phonemeFinal=phonemeFinalIn; // car on connait les valeurs des phonemes
		prononciationMot=prononciationMotIn;

	}

	/**
	 * celle-ci a Sonnet: si on croise un mot inconnu */
	public Mot(String ecritureIn){
		ecriture=ecritureIn.toLowerCase();
		this.analyseConsonneVoyelle();
		nbPieds=compterNbPiedsMots(ecriture);
		if (nbPieds.size()>1){dierese=true;}
		phonemeFinal=Phoneme.phonemeInconnu();
		prononciationMot= null;
	}

	/**cette m thode nous permet de compter le nombre de pieds  de chaque mots
	 * en rempla ant toutes les voyelles qui comptes par un grand V puis en suite les d nombres
	 * dans une liste augment  par les valeurs du mot si il contient des di r ses
	 * Ici on ne compte pas bien sur la prononciation du euh muets car on nettre que mot par mot
	 * on le traite dans Vers */
	public ArrayList <Integer> compterNbPiedsMots(String ecriture){
		ArrayList <Integer> NbPiedsMots =new ArrayList <Integer> ();
		int dierese=0;
		int piedsMinimal=0;


		// le u suivant un q ou un g n est pas une voyelle
		//System.out.println(ecriture);
		String mot= ecriture.replaceAll("qu|gu", "C");
		// un y pr c d  ou suivi d une voyelle n est pas consid r  comme une voyelle

		mot= mot.replaceAll("e$", "E");
		mot= mot.replaceAll("ent$", "E");
		mot= mot.replaceAll("es$", "E");

		Pattern p;
		Matcher m;

		mot= mot.replaceAll("er$", "V");

		Pattern p0 = Pattern.compile(".*y[aeiou    ]|[aeiou    ]y.*");
		Matcher m0 = p0.matcher(mot);
		if (m0.matches()){
			Pattern p1 = Pattern.compile("y");
			Matcher m1 = p1.matcher(mot);
			mot= m1.replaceAll("C");
		}

		p = Pattern.compile("(eue|oue|eau|aie)");
		m = p.matcher(mot);
		mot= m.replaceAll("V");


		p = Pattern.compile("(uie|oie)");//
		m = p.matcher(mot);
		while (m.find()){
			dierese++;
		}

		mot= m.replaceAll("V");


		p = Pattern.compile("ou| e|ei|ai|eu|au|o |ey|ay|ou");
		m = p.matcher(mot);
		mot= m.replaceAll("V");


		p = Pattern.compile(("(ui|u |oi|o |uy|oy|ie|ia|io|iu)"));
		m = p.matcher(mot);
		while (m.find()){
			dierese++;
		}

		mot= m.replaceAll("V");

		mot= mot.replaceAll("[aeiouy                    ]", "V");

		 p = Pattern.compile("(V)");
		 m = p.matcher(mot);
			while (m.find()){
				piedsMinimal++;
			}
		NbPiedsMots.add(piedsMinimal);

		// Il faut faire une r gle pour le e final !
		while (dierese!=0){
			piedsMinimal+=1;
			NbPiedsMots.add(piedsMinimal);
			dierese-=1;
		}

		p = Pattern.compile(("^[qwrtzpsdfghjklxcvbnm]E$"));
		m = p.matcher(mot);
		while (m.find()){
			NbPiedsMots.remove(0);
			NbPiedsMots.add(1);

		}

		return NbPiedsMots;
	}

	// ici on regarde si le mot contient un une cosonne npremi re lettre et un e en derni re lettre
	// cela sera util pour d terminer si le e muets se prononce ou pas
	public void analyseConsonneVoyelle(){
		Pattern p1 = Pattern.compile("^[qwrtzpsdfghjklxcvbnm][a-z]*");
		Matcher m1 = p1.matcher(ecriture);
		if (m1.matches()){premiereConsonne=true;}
		Pattern p = Pattern.compile("[a-z]*e$");
		Matcher m = p.matcher(ecriture);
		if (m.matches() && (ecriture.length()>2)){efinal=true;}
		Pattern p9 = Pattern.compile("[a-z]*es$");
		Matcher m9 = p9.matcher(ecriture);
		if ((m9.matches()) && (ecriture.length()>3)){efinal=true;}

	}

	//getter phonemephinal
	public Phoneme phofinal(){
		return phonemeFinal;
	}

	public void print(){

		System.out.println("le mot est \"" + ecriture+"\"");

		if (!nbPieds.isEmpty()){
			if(dierese){
				String nbP =""+nbPieds.get(0);
				for (int i=1;i<nbPieds.size(); i++){
					if (i==nbPieds.size()-1){
						nbP+=" ou "+ nbPieds.get(i);
					}else{
						nbP+=", "+ nbPieds.get(i);
					}
				}
				System.out.println("Il contient une di r se et "+ nbP +" pieds");
			}else{
				System.out.println("Il contient "+ nbPieds.get(0) +" pieds");
			}
		}
		System.out.println("Son phon me final est "+phonemeFinal.print());

	}

	public static void main(String[] args) {


}

}
