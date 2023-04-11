package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    public static void main(String[] args) {

        if(args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
            JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
            javaGrepLambdaImp.setRegex(args[0]);
            javaGrepLambdaImp.setRootPath(args[1]);
            javaGrepLambdaImp.setOutFile(args[2]);

            try {
                javaGrepLambdaImp.process();

            } catch (Exception e) {
                javaGrepLambdaImp.logger.error("Error: Unable to process", e.getMessage());
            }
    }

    @Override
    public List<String> readLines(File inputFile) {
    List<String> allLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile)))
        {
        bufferedReader.lines().forEach(line->allLines.add(line));
        } catch (IOException e) {
        logger.info("An error occurred while reading the file", e.getMessage());
        }
    return allLines;
    }


    @Override
    public List<File> listFiles(String rootDir)  {
        try (Stream<Path> fileList = Files.walk(Paths.get(rootDir))) {
            return fileList
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
