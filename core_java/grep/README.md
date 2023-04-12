# Introduction
The goal of this project is to replicate the functionality of Linux's grep feature in Java 8. The implementation of the program includes two approaches: one using loops, and the other using streams with lambdas. The program accepts a regular expression pattern as input, searches through all files in a root directory and its subdirectories, and generates an output file containing the matched lines. The project was developed in IntelliJ, built with Maven, and bundled into a Docker image.

# Quick Start

1. To run using the source code :
```
# Compile with Maven:
mvn clean package

#Run locally:   
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp <regex> <rootPath> <outFile>`
```
2. Run using Docker image:  
```
# pull docker image from DockerHub
docker pull themhammed/grep   
docker run --rm themhammed/grep <regex> <rootPath> <outFile>

```
# Implementation
The App utilizes two different methods for its implementation. The first method involves iterating through all sub-directories, starting from the root directory, to collect all files. The files are then scanned to retrieve all lines that match the given regex pattern. The matched lines are subsequently written to an output file.

The second method adopts the same code logic but is optimized for efficiency by employing Java 8 Lambda Expressions and the Streams API.
## Pseudocode
`process` method pseudocode.
```
process() {
matchedLines = []
for each file in listFilesRecursively(rootDir)
  for each line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)`
}
```
## Performance Issue

The initial implementation approach presents a problem with memory usage. When the file sizes are notably large and exceed the allocated memory of the JVM, it can result in a crash of the application.

To resolve this issue, an alternative solution would be to employ streams. Unlike traditional approaches, streams only retain the items that are currently being processed in memory, rather than storing the entire collection. By utilizing streams, it's possible to create a sequence of operations that can be parallelized.

# Test
In order to perform an application test, I created sample text files and saved them both in the root directory and its subdirectories. Input parameters for the root directory path and the desired regular expression were then specified. After running the test, I verified the output file contents to confirm that the output lines included the specified regular expression. The test was executed multiple times, utilizing different regular expressions.
# Deployment
To simplify distribution, the application was dockerized and subsequently uploaded to Docker Hub.
# Improvement
- Generate a line count of the matching lines.
- Implement the lambda and stream API across the entire application.
- Incorporate the various flags utilized in the grep command.