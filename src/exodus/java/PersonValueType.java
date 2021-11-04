package exodus.java;


/**
        Implementacja klasy Person z przesłoniętymi metodami equals() i hashCode()
 */

public class PersonValueType extends Person{

    public PersonValueType(String first_name, String last_name, int birth_year, PersonJob job) throws PersonException{
        super(first_name, last_name, birth_year, job);
    }
    public PersonValueType(String first_name, String last_name) throws PersonException{
        super(first_name,last_name);
    }

    public PersonValueType(String first_name, String last_name, int birth_year) throws PersonException{
        super(first_name,last_name, birth_year);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (getBirthYear() != person.getBirthYear()) return false;
        if (!getFirstName().equals(person.getFirstName())) return false;
        return getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBirthYear();
        return result;
    }
}
