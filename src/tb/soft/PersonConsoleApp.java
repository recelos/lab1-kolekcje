package tb.soft;

import java.util.Arrays;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Jakub Grelowski
 *    Data: 4.11.2021 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Jakub Grelowski\n" +
			"Data: 4 listopada 2021 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Test iteracji (wyświetlanie wszystkich kolekcji)\n" +
			"2 - Test dodawania do konkretnej kolekcji           \n" +
			"0 - Zakończ program        \n";
	private static final String COLLECTION_TYPES =
					"   Która kolekcja?     \n" +
					"1 - ArrayList           \n" +
					"2 - LinkedList      \n" +
					"3 - HashSet  \n" +
					"4 - TreeSet     \n" +
					"5 - HashMap\n"+
					"6 - TreeMap\n";






	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	}
	
	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		CollectionTest test;
		CollectionTest valueTypeTest;

		while (true) {
			UI.clearConsole();
			int index;
			switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// test iteracji
					test = new CollectionTest();
					valueTypeTest = new CollectionValueTypeTest();
					System.out.println("Test bez przesłonięcia metod equals() i hashCode():");
					test.runIterationTest();
					System.out.println("=====================================================");
					System.out.println("Test z przesłoniętymi metodami equals() i hashCode():");
					valueTypeTest.runIterationTest();
					break;
				case 2:
					// test dodawania do określonej kolekcji.
					test = new CollectionTest();
					valueTypeTest = new CollectionValueTypeTest();
					index = UI.enterInt(COLLECTION_TYPES);
					var newPerson = createNewPerson();
					test.runAdditionTest(index, newPerson);
					valueTypeTest.runAdditionTest(index, newPerson);
					break;
			case 0:
				// zakończenie działania programu
				UI.printInfoMessage("\nProgram zakończył działanie!");
				System.exit(0);
			} // koniec instrukcji switch
		} // koniec pętli while
	}
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	public static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}

	
}  // koniec klasy PersonConsoleApp
