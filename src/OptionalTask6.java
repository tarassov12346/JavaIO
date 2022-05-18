import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OptionalTask6 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type (int,double,char,String)!");
        String inputType= scanner.next();
        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask6Example.txt");
        new File("c:/data/JavaIO/OptionalTask6Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask6Result/JavaOptionalTask6ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(makeLineOfTheRequiredType(lineOfTheFile,inputType));
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String checkValueType (String stringValue) {

      if(stringValue.matches("-?\\d+")) return "int";
      if(stringValue.matches("-?\\d+(\\.\\d+)?")) return "double";
      if (stringValue.matches("[\\D]+")&&(stringValue.length()==1)) return "char";
      else return "String";
    }

    public static String makeLineOfTheRequiredType(String line, String type){

        List<String> lineToTheRequiredTypeList=new ArrayList<>();
        Arrays.stream(line.split("\\s+")).forEach(wordInArray->{
            if(checkValueType(wordInArray).equals(type)) lineToTheRequiredTypeList.add(wordInArray);
        });
        return String.join(" ",lineToTheRequiredTypeList);
    }
}
