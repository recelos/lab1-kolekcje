package exodus.java;

/**
 * Klasa testująca kolekcje zawierające obiekty z przesłoniętymi metodami equals() i hashCode()
 */
public class CollectionValueTypeTest extends CollectionTest{
    @Override
    protected void displayTestAdditionAnnotation(){
        System.out.println("Obiekty z przesłoniętymi metodami equals() i hashCode()");
    }

    /**
     * Tworzy przykładowe dane do testowania wyświetlania oraz dodawanie
     * Dwa obiekty mają sobowtóry z identycznymi polami (Karol Wisniewski i Janusz Kowalski)
     * @return tablica z przykładowymi danymi
     */
   @Override
   protected Person[] createData() {
        PersonValueType[] output;
        try {
            output = new PersonValueType[]{
                    new PersonValueType("Wojciech", "Jozwik", 1968, PersonJob.MANAGER),
                    new PersonValueType("Marian", "Nowak", 1991, PersonJob.TEACHER),
                    new PersonValueType("Maciej", "Kaminski", 2001, PersonJob.STUDENT),
                    new PersonValueType("Janusz", "Kowalski", 1951, PersonJob.DIRECTOR),
                    new PersonValueType("Karol", "Wisniewski", 1998, PersonJob.STUDENT),
                    new PersonValueType("Andrzej", "Lewandowski", 1987, PersonJob.TEACHER),
                    new PersonValueType("Karol", "Wisniewski", 1998, PersonJob.STUDENT),
                    new PersonValueType("Janusz", "Kowalski", 1951, PersonJob.DIRECTOR)
            };
        } catch (PersonException e) {
            e.printStackTrace();
            output = null;
        }
        return output;
    }
}
