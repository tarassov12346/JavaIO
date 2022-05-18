import java.io.*;

public class OptionalTask3 {

    public static void main(String[] args) {

        File file= new File("C:/data/JavaIO/Examples/JavaOptionalTask2Example.txt");
        new File("c:/data/JavaIO/OptionalTask3Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask3Result/JavaOptionalTask3ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(new StringBuilder(line).reverse().toString());
                    bufferedWriter.newLine();
                }
                bufferedReader.close();
                bufferedWriter.close();
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
