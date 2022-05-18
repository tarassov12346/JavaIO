import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to folder or the path to file!");
        String pathInConsole = scanner.next();
        System.out.println("-------------------");
        File fileOrFolder = new File(pathInConsole);
        if (!fileOrFolder.exists()) {
            System.out.println(pathInConsole + " does not exist");
            return;
        }
        if (fileOrFolder.isFile()) {
            if (getFileExtension(fileOrFolder).equals("txt")) {
                System.out.println("Reading the  file ");
                System.out.println("-------------------");
                getDataFromTheFile(fileOrFolder);
            } else System.out.println(fileOrFolder.getName() + " does not fit for the task!");
        } else {
            System.out.println("Reading the folder ");
            System.out.println("-------------------");
            printFoldersAndFilesTreeInTheFolder(fileOrFolder, "", args[0]);
        }
    }

    public static void getDataFromTheFile(File file) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> fileLinesList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                fileLinesList.add(line);
            }
            if (fileLinesList.isEmpty()) {
                System.out.println(file.getName() + " is empty!");
                return;
            }
            int amountOfFolders = fileLinesList.stream().
                    filter(fileLine -> isFolder(fileLine)).mapToInt(o -> 1).sum();
            int amountOfFiles = fileLinesList.stream().
                    filter(fileLine -> isFile(fileLine)).
                    mapToInt(o -> 1).sum();
            double averageAmountOfCharsInFile = fileLinesList.stream().
                    filter(fileLine -> isFile(fileLine)).
                    map(fileLine -> fileLine.substring(0, fileLine.indexOf('.')).replaceAll("\\s", "").
                            replaceAll("[|]", "")).mapToInt(fileLine -> fileLine.length()).average().
                    getAsDouble();
            System.out.println("--------------------------");
            System.out.println("Amount of folders = " + amountOfFolders);
            System.out.println("Amount of files = " + amountOfFiles);
            System.out.println("Average amount of files in folder = " +
                    ((double) amountOfFiles) / ((double) amountOfFolders));
            System.out.println("Average amount of chars in file = " + averageAmountOfCharsInFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFoldersAndFilesTreeInTheFolder(File pathToFolder, String indentPoint,
                                                           String folderPathToOutputFile) {

        AtomicInteger fileOrderNumberInFolderForConsole = new AtomicInteger(1);
        AtomicInteger fileOrderNumberInFolderForOutputFile = new AtomicInteger(1);
        if (pathToFolder.listFiles() == null) {
            System.out.println(pathToFolder.getName() + " does not fit for the task!");
            return;
        }
        List<File> filesOrFoldersInFolder = Arrays.asList(pathToFolder.listFiles());
        if (filesOrFoldersInFolder.isEmpty()) System.out.println("There are no files in the folder!");
        filesOrFoldersInFolder.stream().forEach(objectInFolder -> {
            if (objectInFolder.isDirectory()) {
                System.out.println(indentPoint + "|--" + objectInFolder.getName());
                writeToOutputFile(indentPoint + "|--" + objectInFolder.getName(), folderPathToOutputFile);
                printFoldersAndFilesTreeInTheFolder(objectInFolder, indentPoint + "   ", folderPathToOutputFile);
            } else {
                System.out.println(indentPoint +
                        String.format("%02d", fileOrderNumberInFolderForConsole.getAndIncrement()) + " " +
                        objectInFolder.getName());
                writeToOutputFile(
                        indentPoint + String.format("%02d", fileOrderNumberInFolderForOutputFile.getAndIncrement()) + " " +
                                objectInFolder.getName(), folderPathToOutputFile);
            }
        });
    }

    public static void writeToOutputFile(String s, String folderPathToOutputFile) {

        new File(folderPathToOutputFile).mkdir();
        File file = new File(folderPathToOutputFile + "/output.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(s + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getFileExtension(File file) {

        if (file == null) {
            return "";
        }
        String name = file.getName();
        int i = name.lastIndexOf('.');
        return i > 0 ? name.substring(i + 1) : "";
    }

    static boolean isFolder(String fileLine) {

        return fileLine.contains("|--");
    }

    static boolean isFile(String fileLine) {

        return fileLine.contains(".") && !fileLine.contains("|--");
    }
}
