import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        long howManyNumbers =numbers.stream().filter(i -> i< 4).map(i -> i*2).filter(i -> i>10).count();
        System.out.println("Numbers: " + howManyNumbers);
    }
}
//Step-by-step evaluation

//source:
//numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

//filter(i -> i < 4)
//Keeps: 0, 1, 2, 3

//map(i -> i * 2)
//Transforms to: 0, 2, 4, 6

//filter(i -> i > 10)
//Elements reaching here: 0, 2, 4, 6
//From these, i > 10 is true for none → empty stream

//count() → 0

//Question: How many numbers does the stream take to the lambda-expression "i -> i > 10"?

//That means:
//How many elements from the previous step (map) are passed as input to the last filter?

//The previous step (map(i -> i * 2)) produces 4 numbers: 0, 2, 4, 6.
//Each of these is passed into i -> i > 10.

//✅ Correct answer: 4

//So the number is 4, not the final count (0).