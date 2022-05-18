import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionalTask4 {

    public static void main(String[] args) {

        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask2Example.txt");
        new File("c:/data/JavaIO/OptionalTask4Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask4Result/JavaOptionalTask4ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheProgram;
                while ((lineOfTheProgram = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(makeSomeWordsInLineToUpperCase(lineOfTheProgram));
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String makeSomeWordsInLineToUpperCase(String line){

        List<String> someWordsInLineToUpperCaseList=new ArrayList<>();
        Arrays.stream(line.replaceAll(";"," ;").replaceAll(","," ,").replaceAll("\\)"," )").
                replaceAll("\\(", "( ").split(" ")).forEach(wordInLine->
        {
                    if(wordInLine.length()>2) someWordsInLineToUpperCaseList.add(wordInLine.toUpperCase());
                    else someWordsInLineToUpperCaseList.add(wordInLine);
        });
        return String.join(" ",someWordsInLineToUpperCaseList).replaceAll(" ;",";").
                replaceAll(" ,",",").replaceAll("\\( ","(").replaceAll(" \\)",")");
    }
}
