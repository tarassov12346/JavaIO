import java.io.*;

public class OptionalTask3 {

    public static void main(String[] args) {

        File file= new File(args[0]);
        new File(args[1]).mkdir();
        File resultFile = new File(args[1]+"/JavaOptionalTask3ExampleResult.txt");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(new StringBuilder(line).reverse().toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
