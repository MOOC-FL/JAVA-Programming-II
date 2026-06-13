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
Here's a comprehensive guide to regex in Java:

#### Core Classes

Java regex lives in `java.util.regex`:
- **`Pattern`** — compiled regex
- **`Matcher`** — applies pattern to a string
- **`String`** — has built-in regex methods too

#### Basic Usage

```java
import java.util.regex.*;

// 1. Compile + match
Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher("room 42, floor 7");

while (m.find()) {
    System.out.println(m.group()); // "42", "7"
}

// 2. Quick check (no Matcher needed)
boolean valid = Pattern.matches("\\d{3}-\\d{4}", "555-1234"); // true
```

> **Note:** In Java strings, backslashes must be escaped — `\d` becomes `"\\d"`, `\w` becomes `"\\w"`, etc.

#### Matcher Methods

```java
Pattern p = Pattern.compile("(\\w+)@(\\w+\\.\\w+)");
Matcher m = p.matcher("Send to alice@example.com please");

if (m.find()) {
    m.group();    // "alice@example.com"  — full match
    m.group(1);   // "alice"              — group 1
    m.group(2);   // "example.com"        — group 2
    m.start();    // index where match starts
    m.end();      // index where match ends
}
```

| Method | Description |
|--------|-------------|
| `m.find()` | Find next match (use in loop) |
| `m.matches()` | Entire string must match |
| `m.lookingAt()` | Match from the start only |
| `m.group()` | Full matched text |
| `m.group(n)` | Nth capturing group |
| `m.start()` / `m.end()` | Match position |
| `m.reset()` | Reset matcher to start |


#### String Convenience Methods

```java
String s = "hello world foo";

// Test
s.matches("hello.*");              // true (matches entire string)

// Split
s.split("\\s+");                   // ["hello", "world", "foo"]

// Replace
s.replaceAll("\\b\\w{3}\\b", "X"); // "hello X X"   (replaces 3-letter words)
s.replaceFirst("\\w+", "hi");      // "hi world foo"
```

#### Flags

```java
// Case-insensitive
Pattern.compile("hello", Pattern.CASE_INSENSITIVE);

// Multiple flags combined with |
Pattern.compile("^start", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

// Inline flag inside the pattern
Pattern.compile("(?i)hello(?-i) WORLD"); // only "hello" is case-insensitive
```

| Flag | Constant | Inline |
|------|----------|--------|
| Case-insensitive | `Pattern.CASE_INSENSITIVE` | `(?i)` |
| Multiline | `Pattern.MULTILINE` | `(?m)` |
| Dotall (`.` matches `\n`) | `Pattern.DOTALL` | `(?s)` |
| Literal (no metacharacters) | `Pattern.LITERAL` | — |
| Comments & whitespace | `Pattern.COMMENTS` | `(?x)` |

#### Named Groups (Java 7+)

```java
Pattern p = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
Matcher m = p.matcher("Date: 2024-03-15");

if (m.find()) {
    m.group("year");   // "2024"
    m.group("month");  // "03"
    m.group("day");    // "15"
}
```
#### Find All Matches (Java 9+)

```java
// Clean stream-based approach
Pattern p = Pattern.compile("\\d+");
List<String> numbers = p.matcher("1 fish, 2 fish, 3 fish")
    .results()
    .map(MatchResult::group)
    .collect(Collectors.toList()); // ["1", "2", "3"]
```

#### Replace with Logic (Java 9+)

```java
// Transform each match dynamically
Pattern p = Pattern.compile("\\d+");
String result = p.matcher("a1 b22 c333")
    .replaceAll(mr -> "[" + mr.group() + "]");
// "a[1] b[22] c[333]"
```

#### Performance Tip

```java
// BAD — recompiles the pattern every call
public boolean isValid(String s) {
    return s.matches("\\d{4}-\\d{2}-\\d{2}"); // compiles each time!
}

// GOOD — compile once, reuse
private static final Pattern DATE = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

public boolean isValid(String s) {
    return DATE.matcher(s).matches();
}
```
#### Common Patterns

```java
// Email
"[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}"

// US phone
"\\(?\\d{3}\\)?[-.\\s]\\d{3}[-.\\s]\\d{4}"

// URL
"https?://[\\w.\\-]+\\.[a-z]{2,}(/\\S*)?"

// UUID
"[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"

// Integer or decimal
"-?\\d+(\\.\\d+)?"
```
