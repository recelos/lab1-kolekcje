package exodus.java;

import java.util.*;
import java.util.stream.IntStream;


/**
 * Klasa testująca kolekcje zawierające obiekty bez przesłoniętych metod equals() i hashCode()
 */

public class CollectionTest {

    private List<Person> arrayList;
    private List<Person> linkedList;
    private Set<Person> hashSet;
    private Set<Person> treeSet;
    private Map<Integer, Person> hashMap;
    private Map<Integer, Person> treeMap;

    private void display(Collection<Person> collection){
        var i = 1;
        for (Person person : collection) System.out.println(i++ + " " + person);
        System.out.print("\n\n");
    }
    private void display(Map<Integer, Person> map){
        map.forEach((k, v) -> System.out.println(k + ". " + v));
        System.out.print("\n\n");
    }
    public void runAdditionTest(int collectionType, Person person) {
        initializeCollections();
        displayTestAdditionAnnotation();
        switch (collectionType) {
            default:
            case 1:
                arrayList.add(person);
                display(arrayList);
                break;
            case 2:
                linkedList.add(person);
                display(linkedList);
                break;
            case 3:
                hashSet.add(person);
                display(hashSet);
                break;
            case 4:
                treeSet.add(person);
                display(treeSet);
                break;
            case 5:
                hashMap.put(hashMap.size()+1, person);
                display(hashMap);
                break;
            case 6:
                treeMap.put(treeMap.size()+1, person);
                display(treeMap);
                break;
        }
    }

    public void runIterationTest(){
        initializeCollections();
        System.out.println("ArrayList:");
        display(arrayList);
        System.out.println("LinkedList:");
        display(linkedList);
        System.out.println("HashSet:");
        display(hashSet);
        System.out.println("TreeSet:");
        display(treeSet);
        System.out.println("HashMap:");
        display(hashMap);
        System.out.println("TreeMap:");
        display(treeMap);
    }

    protected void displayTestAdditionAnnotation(){
        System.out.println("Obiekty bez przesłoniętych metod equals() i hashCode()");
    }

    protected void initializeCollections(){
        // inicjalizacja kolekcji
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>();
        treeMap = new TreeMap<>();

        //dodawanie obiektów do kolekcji
        Collections.addAll(arrayList, PERSON_DATA);
        Collections.addAll(linkedList, PERSON_DATA);
        Collections.addAll(hashSet, PERSON_DATA);
        Collections.addAll(treeSet, PERSON_DATA);

        IntStream.range(0, PERSON_DATA.length).forEach(i -> {
            hashMap.put(i+1, PERSON_DATA[i]);
            treeMap.put(i+1, PERSON_DATA[i]);
        });
    }

    protected Person[] PERSON_DATA = createData();

    // generowanie przykładowych danych
    // dwa obiekty w tablicy mają identyczne pola (Karol Wisniewski i Janusz Kowalski)
    protected Person[] createData(){
        Person[] output;
        try {
            output = new Person[]{
                    new Person("Wojciech", "Jozwik", 1968, PersonJob.MANAGER),
                    new Person("Marian", "Nowak", 1991, PersonJob.TEACHER),
                    new Person("Maciej", "Kaminski", 2001, PersonJob.STUDENT),
                    new Person("Janusz", "Kowalski", 1951, PersonJob.DIRECTOR),
                    new Person("Karol", "Wisniewski", 1998, PersonJob.STUDENT),
                    new Person("Andrzej", "Lewandowski", 1987, PersonJob.TEACHER),
                    new Person("Karol", "Wisniewski", 1998, PersonJob.STUDENT),
                    new Person("Janusz", "Kowalski", 1951, PersonJob.DIRECTOR)
            };
        } catch (PersonException e) {
            e.printStackTrace();
            output = null;
        }
        return output;
    }
}


