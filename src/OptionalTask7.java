import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OptionalTask7 {

    public static void main(String[] args) {

        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask7Example.txt");
        new File("c:/data/JavaIO/OptionalTask7Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask7Result/JavaOptionalTask7ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(deleteFromLineSomeWordsSomeTimes(lineOfTheFile));
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String deleteFromLineSomeWordsSomeTimes(String line){

        String [] wordsInLine=line.split("\\s+");
        List<String> wordsInLineList= new ArrayList<>();
        int numberOfCases=0;
        for (String wordInLine: wordsInLine){
            if((wordInLine.length()>2)&&(wordInLine.length()<6)) numberOfCases++;
        }
        int countCases=1;
        for (String wordInLine: wordsInLine){
            if ((wordInLine.length()<3)||(wordInLine.length()>5)||(countCases>((numberOfCases/2)*2)))
                wordsInLineList.add(wordInLine);
            else countCases++;
        }
        return String.join(" ",wordsInLineList);
    }
}
