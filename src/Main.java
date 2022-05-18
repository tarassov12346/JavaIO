import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the path to folder or the path to file!");
        String pathInConsole=scanner.next();
        System.out.println("-------------------");
        if (pathInConsole.contains(".txt")) {
            System.out.println("Reading the  file ");
            System.out.println("-------------------");
            File file = new File(pathInConsole);
            if (!file.exists()) {
                System.out.println(pathInConsole + " does not exist");
                return;
            }
            getDataFromTheFile(file);
        } else {
            System.out.println("Reading the folder ");
            System.out.println("-------------------");
            File pathToFolder = new File(pathInConsole);
            if (!pathToFolder.exists()) {
                System.out.println(pathInConsole + " does not exist");
                return;
            }
            printFoldersAndFilesTreeInTheFolder(pathToFolder, "");
            System.out.println("-------------------------------");
        }
    }

    public static void getDataFromTheFile(File file) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> fileLinesList= new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null){
             fileLinesList.add(line);
            }
            if (fileLinesList.isEmpty()) {
                System.out.println(file.getName()+" is empty!");
                 return;}
            int amountOfFolders=fileLinesList.stream().
                    filter(fileLine->fileLine.contains("|--")).mapToInt(o->1).sum();
            int amountOfFiles=fileLinesList.stream().
                    filter(fileLine->fileLine.contains(".")).filter(fileLine->!fileLine.contains("|--")).
                    mapToInt(o->1).sum();
            double averageAmountOfCharsInFile=fileLinesList.stream().
                    filter(fileLine->fileLine.contains(".")).filter(fileLine->!fileLine.contains("|--")).
                    map(fileLine->fileLine.substring(0,fileLine.indexOf('.')).replaceAll("\\s","").
                    replaceAll("[|]","")).mapToInt(fileLine->fileLine.length()).average().
                    getAsDouble();
            System.out.println("--------------------------");
            System.out.println("Amount of folders = " + amountOfFolders);
            System.out.println("Amount of files = " + amountOfFiles);
            System.out.println("Average amount of files in folder = " +
                    ((double) amountOfFiles) / ((double) amountOfFolders));
            System.out.println("Average amount of chars in file = " + averageAmountOfCharsInFile);
        }
    }

    public static void printFoldersAndFilesTreeInTheFolder(File pathToFolder, String indentPoint)  {

        int[] fileOrderNumberInFolderForConsole = new int[]{1};
        int[] fileOrderNumberInFolderForOutputFile = new int[]{1};
        if(pathToFolder.listFiles()==null){
            System.out.println(pathToFolder.getName()+" does not fit for the task!");
            return;
        }
        List<File> filesOrFoldersInFolder = Arrays.asList(pathToFolder.listFiles());
        if (filesOrFoldersInFolder.isEmpty()) System.out.println("There are no files in the folder!");
        filesOrFoldersInFolder.stream().forEach(objectInFolder->{
                   if(objectInFolder.isDirectory()){
                     System.out.println(indentPoint + "|--" + objectInFolder.getName());
                     writeToOutputFile(indentPoint + "|--" + objectInFolder.getName());
                     printFoldersAndFilesTreeInTheFolder(objectInFolder, indentPoint + "   ");
                   }
                    else{
                      System.out.println(
                      indentPoint + ""+String.format("%02d", fileOrderNumberInFolderForConsole[0]++)+" " +
                              objectInFolder.getName());
                      writeToOutputFile(
                     indentPoint + ""+String.format("%02d", fileOrderNumberInFolderForOutputFile[0]++)+" " +
                             objectInFolder.getName());
                    }
        });
    }

    public static void writeToOutputFile(String s) {

        new File("c:/data/JavaIO/MainTaskResult/").mkdir();
        File file = new File("c:/data/JavaIO/MainTaskResult/output.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(s + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
