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
Here's a summary of all the quantifiers and grouping constructs covered:

| Construct | Syntax | Meaning | Example Pattern | Matches |
|---|---|---|---|---|
| Alternation | `a\|b\|c` | Exactly one of the alternatives | `00\|111\|0000` | `"00"`, `"111"`, `"0000"` |
| Grouping | `(...)` | Groups part of the expression | `0000(0\|1)` | `"00000"`, `"00001"` |
| Zero or more | `*` | 0, 1, 2, ... repetitions | `trolo(lo)*` | `"trolo"`, `"trololo"`, `"trolololo"` |
| One or more | `+` | 1, 2, 3, ... repetitions | `tro(lo)+` | `"trolo"`, `"trololo"`, ... |
| Zero or one | `?` | Optional — 0 or 1 time | `(delete )?` | `""` or `"delete "` |
| Exactly n | `{a}` | Exactly `a` repetitions | `(10){2}` | `"1010"` |
| Between n and m | `{a,b}` | Between `a` and `b` repetitions | `1{2,4}` | `"11"`, `"111"`, `"1111"` |
| At least n | `{a,}` | `a` or more repetitions | `1{2,}` | `"11"`, `"111"`, `"11111"`, ... |

**Key rule:** `String.matches()` in Java requires the pattern to match the **entire string**, not just a substring — unlike `Matcher.find()` which searches for a match anywhere inside the string.

**Combining constructs** — example `5{3}(1|0)*5{3}`:

| Part | Meaning |
|---|---|
| `5{3}` | Exactly three `5`s at the start |
| `(1\|0)*` | Any number of `1`s and `0`s in the middle |
| `5{3}` | Exactly three `5`s at the end |

Matches: `"555555"`, `"5551 05 55"` → ✗, `"555101555"` → ✓
