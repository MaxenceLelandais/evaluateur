package code;

public class EvaluateurNiveau {
	
	private static final int MIN_COURS = 0;
	private static final int MAX_COURS = 25;
	private static final int MIN_EXAM = 0;
	private static final int MAX_EXAM = 75;
	private static final int LIMITE_D_C = 30;
	private static final int LIMITE_C_B = 50;
	private static final int LIMITE_B_A = 70;


	public static boolean estEntier( String note ) {
		boolean resultat = true;
		try {
			Integer.parseInt( note );
		}
		catch( NumberFormatException nfe) {
			resultat = false;
		}
		return resultat;
	}
	
	public static boolean estDansIntervalle( int note , int min , int max ) {
		return note >= min && note <= max;
	}
	
	
	

	public static String evaluerNiveau(String cours, String examen ) throws ExceptionNoteNonEntiere, ExceptionNoteHorsBornes {
		// Les notes doivent être entiéres : exception sinon
		if( ! estEntier( cours )) {
			throw new ExceptionNoteNonEntiere();
		}
		int icours = Integer.parseInt( cours );
		
		if( ! estEntier( examen )) {
			throw new ExceptionNoteNonEntiere();
		}
		int iexamen = Integer.parseInt( examen );
		
		//Les notes doivent être dans les bons intervalles : exception sinon
		
		if ( !estDansIntervalle( icours, MIN_COURS, MAX_COURS)) {
			throw new ExceptionNoteHorsBornes();
		}
		
		if ( !estDansIntervalle( iexamen, MIN_EXAM, MAX_EXAM)) {
			throw new ExceptionNoteHorsBornes();
		}
		
		// On somme la note de cours avec celle de l'examen
		int somme = icours + iexamen;
		
		// On calcule selon la spec
		String niveau = "";
		if ( somme < LIMITE_D_C )
		{
			niveau = "D";
		}
		else if ( somme < LIMITE_C_B ) {
			niveau = "C";
		}
		else if ( somme < LIMITE_B_A ) {
			niveau = "B";
		}
		else {
			niveau = "A";
		}
		return niveau;
	}

}
