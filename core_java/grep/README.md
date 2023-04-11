# Introduction
The goal of this project is to replicate the functionality of Linux's grep feature in Java 8. The implementation of the program includes two approaches: one using loops, and the other using streams with lambdas. The program accepts a regular expression pattern as input, searches through all files in a root directory and its subdirectories, and generates an output file containing the matched lines. The project was developed in IntelliJ, built with Maven, and bundled into a Docker image.

# Quick Start
How to use your apps? 

#Implemenation
## Pseudocode
write `process` method pseudocode.

`process() {
matchedLines = []
for each file in listFilesRecursively(rootDir)
  for each line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)`
}

## Performance Issue
(30-60 words)
Discuss the memory issue and how would you fix it

# Test
How did you test your application manually? (e.g. prepare sample data, run some test cases manually, compare result)

# Deployment
How you dockerize your app for easier distribution?

# Improvement
List three things you can improve in this project.