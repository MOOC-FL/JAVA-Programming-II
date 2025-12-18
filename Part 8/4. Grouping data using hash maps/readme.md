#### Grouping data using hash maps
- A hash map has at most one value per each key. In the following example, we store the phone numbers of people into the hash map.
```java
HashMap<String, String> phoneNumbers = new HashMap<>();
phoneNumbers.put("Pekka", "040-12348765");
System.out.println("Pekka's Number " + phoneNumbers.get("Pekka"));
phoneNumbers.put("Pekka", "09-111333");
System.out.println("Pekka's Number " + phoneNumbers.get("Pekka"));
```
> What if we wanted to assign multiple values ​​to a single key, such as multiple phone numbers for a given person?

- Since keys and values ​​in a hash map can be any variable, it is also possible to use lists as values in a hash map. You can add more values ​​to a single key by attaching a list to the key. Let's change the way the numbers are stored in the following wa
```java
HashMap<String, ArrayList<String>> phoneNumbers = new HashMap<>();
```
- Each key of the hash map now has a list attached to it. Although the new command creates a hash map, the hash map initially does not contain a single list. They need to be created separately as needed.
```java
HashMap<String, ArrayList<String>> phoneNumbers = new HashMap<>();

// let's initially attatch an empty list to the name Pekka
phoneNumbers.put("Pekka", new ArrayList<>());
// and add a phone number to the list at Pekka
phoneNumbers.get("Pekka").add("040-12348765");
// and let's add another phone number
phoneNumbers.get("Pekka").add("09-111333");
System.out.println("Pekka's numbers: " + phoneNumbers.get("Pekka"));
```
- We define the type of the phone number as `HashMap<String, ArrayList<String>>`. This refers to a hash map that uses a string as a key and a list containing strings as its value. As such, the values added to the hash map are concrete lists.
```java
// let's first add an empty ArrayList as the value of Pekka
phoneNumbers.put("Pekka", new  ArrayList<>());
// ...
```
- We can implement, for instance, an exercise point tracking program in a similar way. The example below outlines the `TaskTracker` class, which involves user-specific tracking of points from tasks. The user is represented as a string and the points as integers.
```java
public class TaskTracker {
    private HashMap<String, ArrayList<Integer>> completedExercises;

    public TaskTracker() {
        this.completedExercises = new HashMap<>();
    }

    public void add(String user, int exercise) {
        // an empty list has to be added for a new user if one has not already been added
        this.completedExercises.putIfAbsent(user, new ArrayList<>());

        // let's first retrieve the list containing the exercises completed by the user and add to it
        ArrayList<Integer> completed = this.completedExercises.get(user);
        completed.add(exercise);

        // the previous would also work without the helper variable as follows
        // this.completedExercises.get(user).add(exercise);
    }

    public void print() {
        for (String name: completedExercises.keySet()) {
            System.out.println(name + ": " + completedExercises.get(name));
        }
    }
}
```
```java
TaskTracker tracker = new TaskTracker();
tracker.add("Ada", 3);
tracker.add("Ada", 4);
tracker.add("Ada", 3);
tracker.add("Ada", 3);

tracker.add("Pekka", 4);
tracker.add("Pekka", 4);

tracker.add("Matti", 1);
tracker.add("Matti", 2);

tracker.print();
```

















