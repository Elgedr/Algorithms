package ee.ttu.algoritmid.bond;

import java.util.HashMap;
import java.util.Map;

public class DisjointSubsets {
    public Map<String, String> persons = new HashMap<>();
    public Map<String, Integer> hash2 = new HashMap<>();


    public String find(String element) throws IllegalArgumentException {
        if (element == null || element.isEmpty() || persons.get(element) == null) {
            throw new IllegalArgumentException();
        }
        if (persons.get(element).equals(element)) {
            return element;
        }
        return find(persons.get(element));
    }


    public void union(String element1, String element2) throws IllegalArgumentException {
        if (element1 == null || element1.isEmpty() || element2 == null || element2.isEmpty() || persons.get(element1) == null || persons.get(element2) == null) {
            throw new IllegalArgumentException();
        }
        String firstPerson = find(element1);
        String secondPerson = find(element2);
        if (hash2.get(firstPerson) < hash2.get(secondPerson)) {
            persons.put(firstPerson, secondPerson);
        } else if (hash2.get(secondPerson) < hash2.get(firstPerson)) {
            persons.put(secondPerson, firstPerson);
        } else {
            persons.put(secondPerson, firstPerson);
            hash2.put(firstPerson, hash2.get(firstPerson) + 1);
        }


    }

    public void addSubset(String element) throws IllegalArgumentException {
        if (element == null || element.isEmpty() || persons.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        if (!persons.containsKey(element)) {
            hash2.put(element, 0);
            persons.put(element, element);
        }

    }


}