import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionalTask8 {

    public static void main(String[] args) {

        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask8Example.txt");
        new File("c:/data/JavaIO/OptionalTask8Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask8Result/JavaOptionalTask8ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(deleteFromLineUnwantedSpaces(lineOfTheFile));
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String deleteFromLineUnwantedSpaces(String line){

        List<String> wordsInLineList= new ArrayList<>();
        wordsInLineList.addAll(Arrays.asList(line.trim().split("\\s+")));
        return String.join(" ",wordsInLineList);
    }
}
