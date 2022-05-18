import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class OptionalTask11 {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number of words to select:");
        int numberOfWordsToSelect=scanner.nextInt();
        System.out.println("Enter the number of lines to select:");
        int numberOfLinesToSelect=scanner.nextInt();
        File file = new File("C:/data/JavaIO/Examples/JavaOptionalTask11Example.txt");
        new File("c:/data/JavaIO/OptionalTask11Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask11Result/JavaOptionalTask11ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                Stream<String> streamLines= Files.
                        lines(Paths.get("C:/data/JavaIO/Examples/JavaOptionalTask11Example.txt"));
                int totalLinesNumberInFile=(int)streamLines.count();
                if (totalLinesNumberInFile<numberOfLinesToSelect) throw new IndexOutOfBoundsException();
                String lineOfTheFile;
                int counterOfLines=0;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    counterOfLines++;
                    if (counterOfLines>totalLinesNumberInFile-numberOfLinesToSelect) {
                        bufferedWriter.write(selectLastWordsInTheLine(lineOfTheFile,numberOfWordsToSelect));
                        bufferedWriter.newLine();
                    }
                }
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("The wrong initial parameters! Check the file!");
            }
        }
    }

    public static String selectLastWordsInTheLine(String line, int numberOfWords){

        String [] wordsInLine=line.split("\\s+");
        List<String> wordsSelectedInLineList=new ArrayList<>();
        wordsSelectedInLineList.
                addAll(Arrays.asList(wordsInLine).subList(wordsInLine.length - numberOfWords, wordsInLine.length));
        return String.join(" ",wordsSelectedInLineList).replaceAll(
                "[^a-zA-Z0-9\\s-]", "");
    }
}
