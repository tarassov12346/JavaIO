import java.io.*;

public class OptionalTask9 {

    public static void main(String[] args) {

        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask9Example.txt");
        new File("c:/data/JavaIO/OptionalTask9Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask9Result/JavaOptionalTask9ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String lineOfTheFile;
                while ((lineOfTheFile = bufferedReader.readLine()) != null) {
                    //.* means all that follows it
                    String lineOfTheFileWithCommentsDeleted=lineOfTheFile.replaceAll("//.*","").
                            replaceAll("(^/\\*\\*)|(.\\**?\\*+/$)|^\\s\\*.*","").
                            replaceAll( "(^/\\*)|(.\\**?\\*+/$)|^\\s\\*.*","");
                    if(!(lineOfTheFileWithCommentsDeleted=="")) {
                        bufferedWriter.write(lineOfTheFileWithCommentsDeleted);
                        bufferedWriter.newLine();}
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
