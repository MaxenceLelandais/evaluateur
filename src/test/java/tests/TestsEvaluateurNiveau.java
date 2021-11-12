package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import code.EvaluateurNiveau;
import code.ExceptionNoteHorsBornes;
import code.ExceptionNoteNonEntiere;

public class TestsEvaluateurNiveau {
	
	@Test
	@DisplayName("Test exception si la note de cours n'est pas entière")
	public void testExceptionCoursNonEntier() {
		assertThrows( ExceptionNoteNonEntiere.class ,
				() -> { EvaluateurNiveau.evaluerNiveau("xx","50");  } );
	}
	
	@Test
	@DisplayName("Test exception si la note d'examen n'est pas entière")
	public void testExceptionExmamenNonEntier() {
		assertThrows( ExceptionNoteNonEntiere.class ,
				() -> { EvaluateurNiveau.evaluerNiveau("20","50.6");  } );
	}
	
	@Test
	@DisplayName("Test exception si la note de cours n'est pas dans [1..25]")
	public void testExceptionCoursHorsBornes() {
		assertThrows( ExceptionNoteHorsBornes.class ,
				() -> { EvaluateurNiveau.evaluerNiveau("26","50");  } );
	}
	
	@Test
	@DisplayName("Test exception si la note d'examen n'est pas dans [1..75]")
	public void testExceptionExamenHorsBornes() {
		assertThrows( ExceptionNoteHorsBornes.class ,
				() -> { EvaluateurNiveau.evaluerNiveau("20","76");  } );
	}
	
	@ParameterizedTest()
	@CsvSource( {"0,0,D" , "25,4,D" , "0,30,C" , "20,29,C" , "20,30,B"
		, "20,49,B" , "20,50,A" , "25,75,A"})
	public void testLimites( String cours , String examen , 
			                 String niveauAttendu ) throws ExceptionNoteNonEntiere, ExceptionNoteHorsBornes {
		String niveauObtenu = EvaluateurNiveau.evaluerNiveau(cours, examen);
		assertEquals( niveauAttendu , niveauObtenu );
	}

}
