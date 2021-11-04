package exodus.java;

import java.io.*;

/**
 *  Typ wyliczeniowy PersonJob reprezentuje przykładowe stanowiska, 
 *  które może zajmować osoba. Klasa została zaimplementowana
 *  tak, by mogła być rozszerzana o dodatkowe stanowiska.
 *  W tym celu wystarczy do zdefiniowanej listy dodać kolejne
 *  wywołanie konstruktora. 
 */
enum PersonJob {
	UNKNOWN("-------"), 
	GUEST("Gość"), 
	STUDENT("Student"), 
	TEACHER("Nauczyciel"), 
	MANAGER("Kierownik"), 
	DIRECTOR("Dyrektor");

	String jobName;

	PersonJob(String job_name) {
		jobName = job_name;
	}

	
	@Override
	public String toString() {
		return jobName;
	}
	
}  // koniec klasy enum Job


/**
 * Klasa PersonException jest klasą wyjątków służącą do zgłaszania błędów
 * występujących przy operacjach na obiektach klasy Person.
 */
class PersonException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersonException(String message) {
		super(message);
	}
	
} // koniec klasy PersonException


/**
 * Klasa Person reprezentuje osoby, które są opisane za pomocą
 * czterech atrybutów: imię, nazwisko, rok urodzenia, stanowisko.
 * W klasie przyjęto ograniczenia:
 *   - pola firstName oraz lastName muszą zawierać niepusty ciąg znaków
 *   - pole birthYear musi zawierać liczbę z przedziału [1900-2030]
 *     lub 0 (0 oznacza niezdefiniowany rok urodzenia).
 *   - pole job musi zawierać wyłącznie jedną z pozycji zdefiniowanych
 *     w typie wyliczeniowym enum PersonJob.
 *
 * Powyższe ograniczenia są kontrolowane i w przypadku próby nadania
 * niedozwolonej wartości, któremuś z atrybutów jest zgłaszany wyjątek
 * zawierający stosowny komunikat.
 */
public class Person implements Comparable<Person>, Serializable {
	
	private String firstName;
	private String lastName;
	private int birthYear;
	private PersonJob job;


	public Person(String first_name, String last_name, int birth_year, PersonJob job) throws PersonException{
		setFirstName(first_name);
		setLastName(last_name);
		setBirthYear(birth_year);
		setJob(job);
	}
	public Person(String first_name, String last_name, int birth_year) throws PersonException{
		setFirstName(first_name);
		setLastName(last_name);
		setBirthYear(birth_year);
		job = PersonJob.UNKNOWN;
	}
	public Person(String first_name, String last_name) throws PersonException{
		setFirstName(first_name);
		setLastName(last_name);
	}

	
	public String getFirstName() {
		return firstName;
	}

	
	public void setFirstName(String first_name) throws PersonException {
		if ((first_name == null) || first_name.equals(""))
			throw new PersonException("Pole <Imię> musi być wypełnione.");
		this.firstName = first_name;
	}

	
	public String getLastName() {
		return lastName;
	}

	
	public void setLastName(String last_name) throws PersonException {
		if ((last_name == null) || last_name.equals(""))
			throw new PersonException("Pole <Nazwisko> musi być wypełnione.");
		this.lastName = last_name;
	}

	
	public int getBirthYear() {
		return birthYear;
	}

	
	public void setBirthYear(int birth_year) throws PersonException {
		if ((birth_year!=0) && (birth_year < 1900 || birth_year > 2030))
			throw new PersonException("Rok urodzenia musi być w przedziale [1900 - 2030].");
		this.birthYear = birth_year;
	}
	
	
	public void setBirthYear(String birth_year) throws PersonException {
		if (birth_year == null || birth_year.equals("")){  // pusty łańcuch znaków oznacza rok niezdefiniowany
			setBirthYear(0);
			return;
		}
		try { 
			setBirthYear(Integer.parseInt(birth_year));
		} catch (NumberFormatException e) {
			throw new PersonException("Rok urodzenia musi być liczbą całkowitą.");
		}
	}


	public PersonJob getJob() {
		return job;
	}

	
	public void setJob(PersonJob job){
		this.job = job;
	}
	
	
	public void setJob(String job_name) throws PersonException {
		if (job_name == null || job_name.equals("")) {  // pusty łańcuch znaków oznacza stanowisko niezdefiniowane
			this.job = PersonJob.UNKNOWN;
			return;
		}
		for(PersonJob job : PersonJob.values()){
			if (job.jobName.equals(job_name)) {
				this.job = job;
				return;
			}
		}
		throw new PersonException("Nie ma takiego stanowiska.");
	}

	/**
	 * Metoda porównuje dwa obiekty Person na bazie ich pola lastName
	 * @param p - osoba porównywana
	 * @return 0 jeśli referencja wskazuje na ten sam obiekt
	 */
	@Override
	public int compareTo(Person p) {
		if (equals(p)) return 0;
		return getLastName().compareTo(p.getLastName());
	}

	@Override
	public String toString() {  
		return firstName + " " + lastName + ", " + birthYear +"r, " + job.toString();
	}
	
}  // koniec klasy Person
