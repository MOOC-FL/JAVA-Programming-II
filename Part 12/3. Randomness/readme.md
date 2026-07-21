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
- [ ] A Random object can also be used to create random doubles.
- [ ] These can for example be used for calculating probabilities.
- [ ] Computers often simulate probabilities using doubles between `[0..1]`.
- The `nextDouble` method of the Random class creates random doubles. Let's assume the weather follows these probabilities:
     - There is 0.1 probability it rains (10%)
     - There is 0.3 probability it snows (30%)
     - There is 0.6 probability the sun shines (60%)
> Let's create a weather forecast using these probabilities.
```java
import java.util.ArrayList;
import java.util.Random;

public class WeatherMan {
    private Random random;

    public WeatherMan() {
        this.random = new Random();
    }

    public String forecast() {
        double probability = this.random.nextDouble();

        if (probability <= 0.1) {
            return "It rains";
        } else if (probability <= 0.4) { // 0.1 + 0.3
            return "It snows";
        } else { // rest, 1.0 - 0.4 = 0.6
            return "The sun shines";
        }
    }

    public int makeAForecast() {
        return (int) (4 * this.random.nextGaussian() - 3);
    }
}
```
- [ ] The `makeAForecast` method is interesting in many ways.
- [ ] The `this.random.nextGaussian()` call is a regular method call.
- [ ] However what is interesting is that this method of the Random class returns a normally distributed number (normally distributed numbers can be used to for example model the heights and weights of people — if you are not interested in different kinds of randomness that is OK!).
```java
public int makeAForecast() {
    return (int) (4 * this.random.nextGaussian() - 3);
}
```
> In the previous example we use explicit type casting to convert doubles to `integers (int)`. We can equally convert integers to doubles with `(double) integer`.

> Let's now add a main which uses the `WeatherMan` class.
```java
// imports

public class Program {

    public static void main(String[] args) {
        WeatherMan forecaster = new WeatherMan();

        // save days of the week to a list
        ArrayList<String> days = new ArrayList<>();
        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");
        days.add("Fri");
        days.add("Sat");
        days.add("Sun");

        System.out.println("Next week's weather forecast:");

        for (String day : days) {
            String weatherForecast = forecaster.forecast();
            int temperatureForecast = forecaster.makeAForecast();

            System.out.println(day + ": " + weatherForecast + " " + temperatureForecast + " degrees.");
        }
    }
}
```
The program output could be:
```text
Sample output
Next week's weather forecast:
Mon: It snows 1 degrees.
Tue: It snows 1 degrees.
Wed: The sun shines -2 degrees.
Thu: The sun shines 0 degrees.
Fri: It snows -3 degrees.
Sat: It snows -3 degrees.
Sun: The sun shines -5 degrees
```







