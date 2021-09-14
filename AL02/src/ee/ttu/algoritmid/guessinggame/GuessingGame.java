package ee.ttu.algoritmid.guessinggame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

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
//        Fruit[] fruits = fruitArray.stream.sorted(Comparator.comparing(Fruit::getWeight));
        System.out.println(Arrays.toString(new List[]{fruits}));
        int middle = fruits.size() / 2;
        String answer = oracle.isIt((Fruit) fruits.get(middle));
        System.out.println(answer);
        while (!answer.equals("correct!")) {
            if (answer.equals("lighter")) {
                fruits = fruits.subList(0, middle);
            } else {
                fruits = fruits.subList(middle, fruits.size());
            }
            middle = fruits.size() / 2;
            answer = oracle.isIt(fruits.get(middle));
        }
        String res = String.valueOf(fruits.get(middle));
        System.out.println(res);
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
        Oracle oracle = new Oracle(fruits[3]); // mango
        GuessingGame guessingGame = new GuessingGame(oracle);
        guessingGame.play(fruits);
    }
}