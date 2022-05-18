import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class OptionalTask12 {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the target pattern!");
        String targetPattern=scanner.nextLine();
        File file = new File("C:/data/JavaIO/Examples/JavaOptionalTask12Example.txt");
        new File("c:/data/JavaIO/OptionalTask12Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask12Result/JavaOptionalTask12ExampleResult.txt");
        if (file.exists()) {
            try {
                Pattern pattern =Pattern.compile(targetPattern);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                List<String> wordsInTextList=new ArrayList<>();
                List<String> wordsMatchTheTemplateList=new ArrayList<>();
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    wordsInTextList.addAll(Arrays.asList(lineOfTheFile.split("[,\\.\\s+]")));
                }
                wordsInTextList.stream().forEach(word->{
                    Matcher matcher= pattern.matcher(word);
                    if (matcher.matches()) wordsMatchTheTemplateList.add(word);
                });
                for(String word:wordsMatchTheTemplateList){
                    bufferedWriter.write(word);
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (PatternSyntaxException e){
                System.out.println("Check the template!");
            }

        }
    }
}
