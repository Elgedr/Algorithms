package ee.ttu.algoritmid.guessinggame;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GuessingGame {

    Oracle oracle;

    public GuessingGame(Oracle oracle) {
        this.oracle = oracle;
    }

    /**
     * @param fruitArray - All the possible fruits.
     * @return the name of the fruit.
     */
    public String play(Fruit[] fruitArray) {
        List<Fruit> fruits = List.of(fruitArray).stream().sorted(Comparator.comparing(Fruit::getWeight)).collect(Collectors.toList());
        int middle = (fruits.size() - 1) / 2;
        System.out.println(fruits);
        String answer = oracle.isIt((Fruit) fruits.get(middle));
        while (!answer.equals("correct!")) {
            if (answer.equals("lighter")) {
                fruits = fruits.subList(0, middle);
            } else {
                fruits = fruits.subList(middle + 1, fruits.size());
            }
            middle = (fruits.size() - 1) / 2;
            answer = oracle.isIt(fruits.get(middle));
        }
        String res = String.valueOf(fruits.get(middle));
        System.out.println(res);
        System.out.println(answer);
        return res;
    }

    public static void main(String[] args) {
        Fruit[] fruits = new Fruit[]{
                new Fruit("Apelsin", 150),
                new Fruit("Banaan", 185),
                new Fruit("Greip", 250),
                new Fruit("Mango", 210),
                new Fruit("Pirn", 170),
                new Fruit("Ploom", 50),
                new Fruit("Virsik", 130),
                new Fruit("Õun", 110)

        };
        Oracle oracle = new Oracle(fruits[0]); // mango
        GuessingGame guessingGame = new GuessingGame(oracle);
        guessingGame.play(fruits);
    }
}