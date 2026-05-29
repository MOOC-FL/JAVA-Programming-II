#### Regular Expressions
- A regular expression defines a set of strings in a compact form.
- Regular expressions are used, among other things, to verify the correctness of strings.
- We can assess whether or not a string is in the desired form by using a regular expression that defines the strings considered correct.
  > Let's look at a problem where we need to check if a student number entered by the user is in the correct format. A student number should begin with "01" followed by 7 digits between 0–9.
- You could verify the format of the student number, for instance, by going through the character string representing the student number using the `charAt` method.
- Another way would be to check that the first character is **"0"** and call the `Integer.valueOf` method to convert the string to a number.
- You could then check that the number returned by the `Integer.valueOf` method is less than 20000000.
- Checking correctness with the help of regular expressions is done by first defining a suitable regular expression.
- We can then use the `matches` method of the `String` class, which checks whether the **string matches the regular expression** given as a parameter.
- For the student number, the appropriate regular expression is `"01[0-9]{7}"`, and checking the student number entered by a user is done as follows:
```java
System.out.print("Provide a student number: ");
String number = scanner.nextLine();

if (number.matches("01[0-9]{7}")) {
    System.out.println("Correct format.");
} else {
    System.out.println("Incorrect format.");
}
```
Let's go through the most common characters used in regular expressions.

