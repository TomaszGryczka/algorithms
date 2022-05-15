# Data Structures and Algorithms Course
This is a set of projects written during my classes at Faculty of Electrical Engineering (Warsaw University of Technology). 
The aim of these projects was to develop basic structures and algorithms.

## Description
All algorithms (except for binary search) were written using Java 1.8, tested with JUnit 5.8.1 and managed by Maven.

The following list contains all implemented algorithms:
- binary search (written in C),
- sorting algorithms (Insertion Sort, Selection Sort, Heap Sort, Quick Sort),
- hash table (separate chaining),
- hash table (open addressing),
- left leaning red black tree,
- huffman coding,
- longest common subsequence,
- minimum spanning tree,
- finite automata algorithm for pattern searching.

## Getting started

### Dependencies
- openjdk version 1.8
- either maven or IDE with built in maven plugin

### Running tests
If you want to test these programs, simply clone repo using:
```
$ git clone https://github.com/TomaszGryczka/algorithms.git
```
Then you can navigate to a particular algorithm, for example hash table:
```
$ cd algorithms/hash_table
```
And now you can run its test with maven:
```
$ mvn test
```
You can also run these tests with IntelliJ IDEA (it has built in maven plugin).

## Author
- [Tomasz Gryczka](https://github.com/TomaszGryczka)
