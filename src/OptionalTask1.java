import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class OptionalTask1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the random numbers quantity!");
        try {
            int numbersQuantity = scanner.nextInt();
            List<Integer> randomNumbersList = new ArrayList<>();
            Random random = new Random();
            IntStream.range(0, numbersQuantity).forEach(o -> randomNumbersList.add(random.nextInt(1000)));
            writeToRandomFile(readToList(writeToRandomFile(randomNumbersList,
                    args[0],"random.txt")).stream().
                    sorted().toList(),args[0],"sortedRandom.txt");
        }
        catch (InputMismatchException e){
            System.out.println("The wrong format!");
        }
    }

    public static File writeToRandomFile(List<Integer> list,String pathToFolderName,String fileName){
        new File(pathToFolderName).mkdir();
        File randomFile=new File(pathToFolderName,fileName);
        try(BufferedWriter  writer = new BufferedWriter(new FileWriter(randomFile))) {
            list.stream().forEach(number -> {writeANumberIntoFile(number,writer);});
        }catch (IOException e) {
            e.printStackTrace();
        }
        return randomFile;
    }

    public static List<Integer> readToList(File file){

        List<Integer> numberList=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(file))){
            String number;
            while ((number = reader.readLine()) != null){
                numberList.add(Integer.valueOf(number));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberList;
    }

    static void writeANumberIntoFile(Integer number,BufferedWriter writer) {

        try {writer.write(Integer.toString(number));
             writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
