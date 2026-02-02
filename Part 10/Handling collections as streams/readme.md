##### Handling collections as streams
- Let's get to know collections, such as lists, as streams of values. Stream is a way of going through a collection of data such that the programmer determines the operation to be performed on each value. No record is kept of the index or the variable being processed at any given time.
- With streams, the programmer defines a sequence of events that is executed for each value in a collection. An event chain may include dumping some of the values, converting values ​​from one form to another, or calculations. A stream does not change the values ​​in the original data collection, but merely processes them. If you want to retain the transformations, they need to be compiled into another data collection.
- Let's begin to understand the usage of streams through a concrete example. Consider the following problem:
> *Write a program that reads input from a user and prints statistics about those inputs. When the user enters the string "end", the reading is stopped. Other inputs are numbers in string format. When you stop reading inputs, the program prints the number of positive integers divisible by three, and the average of all values.*



