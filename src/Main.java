import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean  mainMenuLoop = false;
        mainMenuLoop:
        while (!mainMenuLoop) {
        System.out.println("\u001B[31m" +"████████╗ ██████╗ ██████╗ ███████╗██████╗  ██████╗ ██╗   ██╗    ███████╗██╗██╗     ███████╗    ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗██████╗ \n" + "╚══██╔══╝██╔═══██╗██╔══██╗██╔════╝██╔══██╗██╔═══██╗██║   ██║    ██╔════╝██║██║     ██╔════╝    ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝██╔══██╗\n" + "   ██║   ██║   ██║██████╔╝█████╗  ██████╔╝██║   ██║██║   ██║    █████╗  ██║██║     █████╗      ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██████╔╝\n" + "   ██║   ██║   ██║██╔═══╝ ██╔══╝  ██╔══██╗██║   ██║╚██╗ ██╔╝    ██╔══╝  ██║██║     ██╔══╝      ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██╔══██╗\n" + "   ██║   ╚██████╔╝██║     ███████╗██║  ██║╚██████╔╝ ╚████╔╝     ██║     ██║███████╗███████╗    ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║  ██║\n" + "   ╚═╝    ╚═════╝ ╚═╝     ╚══════╝╚═╝  ╚═╝ ╚═════╝   ╚═══╝      ╚═╝     ╚═╝╚══════╝╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝\n"+ "\u001B[0m");
        System.out.println("1. Просмотр содержимого папки\n" + "2. Создание файла/папки\n" + "3. Удаление файла/папки\n" + "4. Выход\n" + "Выберите действие (1-4):");
        Scanner firstChoice = new Scanner(System.in);

        boolean exitProgram = false;
        mainLoop:
        while (!exitProgram) {
            String firstChoiceSwich = firstChoice.nextLine();
        switch (firstChoiceSwich){
            case ("1"):
                System.out.println("Введи точный путь к папке,или напиши '.' чтобы исследовать папку проэкта");
                Scanner choiceToReadFolder = new Scanner(System.in);
                String choiceToReadFolderString = choiceToReadFolder.nextLine();
                if (choiceToReadFolderString.contains(".")){
                    checkTheFolder(".");
                }else{
                    checkTheFolder(choiceToReadFolderString);
                }
                System.out.println("1\n\n");
                continue mainMenuLoop;
            case ("2"):
                System.out.println("Введите директорию для создание файла(локальная директория '.'");
                Scanner directorytoCreateScanner= new Scanner(System.in);
                String  directorytoCreateString = directorytoCreateScanner.nextLine();
                System.out.println("Введите имя файла ( с учетом типа файла пример : 'install.exe')");
                Scanner filetoCreateScanner= new Scanner(System.in);
                String  filetoCreateString = filetoCreateScanner.nextLine();
                createTheFile(directorytoCreateString,filetoCreateString);
                continue mainMenuLoop;
            case ("3"):
                System.out.println("Введите Директорию,где вы желаете удалить файл(локальная директория - '.'");
                Scanner directorytoDeleteScanner= new Scanner(System.in);
                String  directorytoDeleteString = directorytoDeleteScanner.nextLine();
                System.out.println("Введите имя файла ( с учетом типа файла пример : 'install.exe')");
                Scanner filetoDeleteScanner = new Scanner(System.in);
                String  filetoDeleteString = filetoDeleteScanner.nextLine();
                deleteTheFile(directorytoDeleteString,filetoDeleteString);
                continue mainMenuLoop;

            case ("4"):
                boolean exitProgramInside = false;
                loopInsideSwich:
                while (!exitProgramInside) {
                System.out.println("Перед тем,как выйти,вы уверенны в своем выборе Y/N?");
                Scanner choiceToExit = new Scanner(System.in);
                String choiceToExitString = choiceToExit.nextLine();

                if (choiceToExitString.contains("Y")||choiceToExitString.contains("y")){
                    exitProgram = true;
                    exitProgramInside = true;
                    System.exit(0);
                } else if (choiceToExitString.contains("N")||choiceToExitString.contains("n")) {
                    exitProgram = true;
                    exitProgramInside = true;
                    continue mainLoop;
                }else {
                    System.out.println("Такого варианта нету,только Y or N,попробуй снова!");
                    continue loopInsideSwich;
                    }
                }
                break;
            default:
                System.out.println("Такого выбора нету бро(\n\n");

                continue mainMenuLoop;
                }
            }
        }

    }
    public static void checkTheFolder(String path){
        System.out.println("Что находиться в директории по пути: "+"' "+path+" '");
        Path folderPath = Paths.get(path);
        try (Stream<Path> paths = Files.list(folderPath)) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Произошла ошибка,такой директории несуществует. \n" + e.getMessage());
        }
    }
    public static void createTheFile(String filefullname,String directory) throws IOException {
        String filePathString = directory + "\\" + filefullname;
        if (Files.exists(Path.of(filePathString))) {
            System.out.println("Файл '" + filefullname + "' уже существует в директории '" + directory + "'.");
            System.out.println("Желаете его удалить? Y/N");
            Scanner choiceToDelete = new Scanner(System.in);
            String choiceToDeleteString = choiceToDelete.nextLine();
            if (choiceToDeleteString.contains("Y")||choiceToDeleteString.contains("y")){
                deleteTheFile(directory,filefullname);
            } else if (choiceToDeleteString.contains("N")||choiceToDeleteString.contains("n")) {
                System.out.println("ok");
            } else {
                System.out.println("Вы не выбрали Y/N");
            }
        } else {
            Files.createFile(Path.of(filePathString));
            System.out.println("Файл '" + filefullname + "' создан успешно в директории '" + directory + "'.");
        }
    }
    public static void deleteTheFile(String directory,String filefullname) throws IOException {
        String filePathString = directory + "\\" + filefullname;
        if (Files.exists(Path.of(filePathString))) {
            Files.delete(Path.of(filePathString));
            System.out.println("Файл '" + filefullname + "' удален успешно в директории '" + directory + "'.");
        }else {
            System.out.println("Данного файла не существует.");
        }
    }
}