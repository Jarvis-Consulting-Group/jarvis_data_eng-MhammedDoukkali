package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;



public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    /**the regular expression pattern to search for*/
    private String regex;
    /**the root directory to search for matching files*/
    private String rootPath;
    /**the name of the file to write the output of the search to*/
    private String outFile;



    public static void main(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        //Use default logger config
        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception e) {
            javaGrepImp.logger.error("Error: Unable to process", e);
        }
    }
    @Override
    public void process() throws IOException {

        List<String> matchedLines = new ArrayList<>();
        for (File currFile : listFiles(getRootPath())) {
            for (String currLine : readLines(currFile)) {
                if (containsPattern(currLine)) {
                    matchedLines.add(currLine);
                }
            }
        }
        writeToFile(matchedLines);
    }


    @Override
    public List<File> listFiles(String rootDir) {

        List<File> fileList = new ArrayList<>();
        //Creating a File object for directory
        File path = new File(rootDir);
        //list of all files
        File[] files = path.listFiles();
        for( int i = 0 ; i < files.length; i++) {
            if (files[i].isFile()) {
                fileList.add(files[i]);
            }
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> allLines = new ArrayList<>();
        try {
            //Wrap FileReader in BufferedReader to read and store in a buffer
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            //returning lines as string
            String line;
            while((line = bufferedReader.readLine()) != null) {
                //adding each line to the List
                allLines.add(line);
//                logger.info(line);
                }

            bufferedReader.close();
            } catch (IOException e) {
            logger.error("An error occurred while reading the file", e.getMessage());
            }
        return allLines;
    }


    @Override
    public boolean containsPattern(String line) {
        Pattern pattern = Pattern.compile("regex") ;
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }


    @Override
    public void writeToFile(List<String> lines) throws IOException {

        try {
            //Create a FileOutputStream to write bytes to the file
            FileOutputStream fileOutputStream = new FileOutputStream("output");
            //Create an OutputStream to write characters data to the FileOutputStream
            OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream);
            //Create a BufferedWriter to write data to the outputStreamWriter
            BufferedWriter bufferedWriter = new BufferedWriter(outputStream);

            for( String line: lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
//            logger.info("Data has been written to the file");
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file: " + e.getMessage());
        }

    }


    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
    this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
