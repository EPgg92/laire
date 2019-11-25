    public static void main( String [ ] args )
    {
        HashMap <String, Integer> m = new HashMap <String, Integer> ();
        try
                {
                    FileOutputStream fichier = new FileOutputStream("valeurs.sav");
                    ObjectOutputStream fluxObjet = new ObjectOutputStream(fichier);
                    fluxObjet.writeObject(m);
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
        
    public static void main2( String [ ] args )
    {
         try
         {
	    	System.out.println( "Affichage du HashMap   partir du fichier 'valeurs.sav'");
            FileInputStream fichier = new FileInputStream("valeurs.sav");
	    	ObjectInputStream fluxObjet = new ObjectInputStream(fichier);
	    	HashMap <String, Integer> m = (HashMap <String, Integer>) fluxObjet.readObject();
	    	fluxObjet.close();
	    	fichier.close();
	    	// affichage
            Set <Map.Entry <String, Integer>> mots = m.entrySet();
            Iterator <Map.Entry <String, Integer>> iter = mots.iterator();
            while (iter.hasNext()) {
                Map.Entry <String, Integer> paire = iter.next();
                System.out.println(paire);
            }
	     }
	     catch (Exception e) {
	    	e.printStackTrace();
	    	System.exit(1);
	     }
    }