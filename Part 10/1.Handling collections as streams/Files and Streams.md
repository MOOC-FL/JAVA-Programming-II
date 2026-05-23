#### Files and Streams
- Streams are also very handy in handling files.
- The file is read in stream form using Java's ready-made Files class.
- The lines method in the files class allows you to create an input stream from a file, allowing you to process the rows one by one.
- The lines method gets a path as its parameter, which is created using the get method in the Paths class. The get method is provided a string describing the file path.
- The example below reads all the lines in "file.txt" and adds them to the list.
```java
List<String> rows = new ArrayList<>();

try {
    Files.lines(Paths.get("file.txt")).forEach(row -> rows.add(row));
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}

// do something with the read lines
```
- If the file is both found and read successfully, the lines of the `"file.txt"` file will be in the `rows` list variable at the end of the program. However, if a file cannot be found or read, an error message will be displayed. Below is one possibility:
```text
Error: file.txt (No such file or directory)
```
