#### Randomness
- **Encryption algorithms**, machine learning and making computer games less predictable all require `randomness`. We can model randomness using random numbers. Java offers ready-made Random class for creating random numbers. An instance of the Random class can be used as follows:
```java
import java.util.Random;

public class Raffle {
    public static void main(String[] args) {
        Random ladyLuck = new Random(); // create Random object ladyLuck

        for (int i = 0; i < 10; i++) {
            // Draw and print a random number
            int randomNumber = ladyLuck.nextInt(10);
            System.out.println(randomNumber);
        }
    }
}
```
> Above we create an instance of the `Random` class. It has `nextInt` method, which gets an `integer` as a parameter. The method returns a random number between [0, integer[ or 0..(integer -1).

> The program output is not always the same. One possible output is the following:
```text
Sample output
2
2
4
3
4
5
6
0
7
8
```
- We can use the `nextInt` method to create **diverse randomness**. For example, we might need a program to give us a temperature between [-30,50]. We can do this by first creating **random** a number between 0 and 80 and then subtracting 30 from it.
```java
Random weatherMan = new Random();
int temperature = weatherMan.nextInt(81) - 30;
System.out.println(temperature);
```




