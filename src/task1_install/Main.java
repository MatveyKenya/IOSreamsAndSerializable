package task1_install;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static final String PATH = "D://Games"; //главный путь
    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {

        //создаем папки и файлы по главному пути
        newDir(""); //создаем папку Games
        newDir("src");
        newDir("src//main");

        newFile("src//main", "Main.java");
        newFile("src//main", "Utils.java");

        newDir("src//test");

        newDir("res");
        newDir("res//drawables");
        newDir("res//vectors");
        newDir("res//icons");

        newDir("savegames");

        newDir("temp");
        newFile("temp","temp.txt");

        System.out.println(log);

        try {
            FileWriter writer = new FileWriter(PATH + "//temp//temp.txt");
            writer.write(log.toString());
            writer.flush();

        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    static void newDir(String path){
        File mainDir = new File(PATH + "//" + path);
        String logInfo = "Папка " + mainDir.getPath() + " создана\n";
        if (mainDir.mkdir()) log.append(logInfo);
        else log.append("папка не создана\n");
    }

    static void newFile(String path, String fileName){
        File file = new File(PATH + "//" + path, fileName);
        String logInfo = "Файл " + file.getAbsolutePath() + " создан\n";
        try {
            if (file.createNewFile()) log.append(logInfo);
        } catch (IOException ex){
            logInfo = "файл не создан - " + ex.getMessage() + '\n';
            log.append(logInfo);
        }

    }
}
