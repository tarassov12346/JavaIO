import java.io.*;

public class OptionalTask2 {

    public static void main(String[] args) {

        File file = new File("c:/data/JavaIO/Examples/JavaOptionalTask2Example.txt");
        new File("c:/data/JavaIO/OptionalTask2Result/").mkdir();
        File resultFile = new File("c:/data/JavaIO/OptionalTask2Result/JavaOptionalTask2ExampleResult.txt");
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(line.replace("public", "private"));
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

