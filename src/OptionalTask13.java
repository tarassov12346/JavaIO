import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OptionalTask13 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter k:");
        int k=scanner.nextInt();
        System.out.println("Enter j:");
        int j=scanner.nextInt();
        File file = new File("C:/data/JavaIO/Examples/JavaOptionalTask13Example.txt");
        new File("c:/data/JavaIO/OptionalTask13Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask13Result/JavaOptionalTask13ExampleResult.txt");
        if (file.exists()) {
            try {
                Pattern pattern =Pattern.compile("\\(\\d{3}\\) " + k + j + "\\d*");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    Matcher matcher= pattern.matcher(lineOfTheFile);
                    if(matcher.find()){
                    bufferedWriter.write(lineOfTheFile);
                    bufferedWriter.newLine();
                    }
                }
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
