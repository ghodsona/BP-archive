import java.io.*;
import java.nio.file.*;
import java.util.*;

public class judge1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process compileProcess = Runtime.getRuntime().exec("javac tmp3.java");
        compileProcess.waitFor();

        // getting the list of input's name
        File inputDir = new File("in");
        File[] inputFiles = inputDir.listFiles((dir, name) -> name.startsWith("input") && name.endsWith(".txt"));
        if (inputFiles == null || inputFiles.length == 0) {
            System.out.println("input was not found");
            return;
        }

        // sorting inputs
        Arrays.sort(inputFiles, Comparator.comparingInt(file -> extractNumber(file.getName())));

        for (File inputFile : inputFiles) {
            int testCaseNumber = extractNumber(inputFile.getName());
            File expectedOutputFile = new File("out/output" + testCaseNumber + ".txt");

            if (!expectedOutputFile.exists()) {
                System.out.println("ouput for testcase" + testCaseNumber + " was not found");
                continue;
            }

            String input = Files.readString(inputFile.toPath()).trim();
            String expectedOutput = Files.readString(expectedOutputFile.toPath()).trim();

            // run java
            Process process = Runtime.getRuntime().exec("java tmp3");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input + "\n"); //input
            writer.flush();
            writer.close();

            // resdingoutput
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            reader.close();

            String output = outputBuilder.toString().trim();

            // comparing
            System.out.print("test " + testCaseNumber);
            if (output.equals(expectedOutput)) {
            }
            else {
                System.out.print(" failed ");
            }
            System.out.println();
            System.out.println("   input: " + input);
            System.out.println("   output: " + output);
            System.out.println("   expected output: " + expectedOutput);
            System.out.println();
        }
    }

    private static int extractNumber(String filename) {
        return Integer.parseInt(filename.replaceAll("[^0-9]", ""));
    }
}

