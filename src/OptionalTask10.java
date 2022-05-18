import java.io.*;

public class OptionalTask10 {

    public static void main(String[] args) {

        File file = new File("C:/data/JavaIO/Examples/JavaOptionalTask10Example.txt");
        new File("c:/data/JavaIO/OptionalTask10Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask10Result/JavaOptionalTask10ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(removeFirstAndLastWordInLine(lineOfTheFile));
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String removeFirstAndLastWordInLine(String line){

        String [] wordsInLine=line.replaceAll(","," ,").replaceAll("\\."," .").split("\\s+");
        String swapCell=wordsInLine[0];
        if(wordsInLine[wordsInLine.length-1].equals(",")||wordsInLine[wordsInLine.length-1].equals(".")){
             wordsInLine[0]=wordsInLine[wordsInLine.length-2];
             wordsInLine[wordsInLine.length-2]=swapCell;
        }
        else {
            wordsInLine[0]=wordsInLine[wordsInLine.length-1];
            wordsInLine[wordsInLine.length-1]=swapCell;
        }
        return  String.join(" ",wordsInLine).replaceAll("\\s(\\p{Punct})","$1").trim();
    }
}
