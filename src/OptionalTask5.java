import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class OptionalTask5 {

    public static void main(String[] args) {

        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask5Example.txt");
        new File("c:/data/JavaIO/OptionalTask5Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask5Result/JavaOptionalTask5ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(makeSomeWordsInLineToUpperCase(lineOfTheFile));
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e){
                System.out.println("Fill all the file references with scores in right format!");
            }
        }
    }

    public static String makeSomeWordsInLineToUpperCase(String line) throws NumberFormatException{

        String someWordsInLineToUpperCase;
            String[] wordsInLineArray = line.split(" ", 2);
            if (IntStream.of(Arrays.stream(wordsInLineArray[1].split(" ")).
                    mapToInt(Integer::parseInt).toArray()).average().getAsDouble() > 7)
                someWordsInLineToUpperCase = wordsInLineArray[0].toUpperCase() + " " + wordsInLineArray[1];
            else someWordsInLineToUpperCase = wordsInLineArray[0] + " " + wordsInLineArray[1];
        return someWordsInLineToUpperCase;
    }
}
