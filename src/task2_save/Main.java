package task2_save;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        String pathToSave = "D://Games//savegames//";
        String gp1Path = pathToSave + "gp1.dat";
        String gp2Path = pathToSave + "gp2.dat";
        String gp3Path = pathToSave + "gp3.dat";

        GameProgress gp1 = new GameProgress(90, 6, 12, 1000);
        GameProgress gp2 = new GameProgress(100, 9, 1, 1345);
        GameProgress gp3 = new GameProgress(75, 2, 82, 5050);

        if (saveGame(gp1Path, gp1)) System.out.println("save gp1.dat ok");
        if (saveGame(gp2Path, gp2)) System.out.println("save gp2.dat ok");
        if (saveGame(gp3Path, gp3)) System.out.println("save gp3.dat ok");

        if (zipFiles(pathToSave + "zipFiles.zip", new String[] {gp1Path, gp2Path, gp3Path})){
            System.out.println("files ziped ok");
        }

        //удаляем заархивированные файлы
        new File(gp1Path).delete();
        new File(gp2Path).delete();
        new File(gp3Path).delete();

    }

    static boolean saveGame(String path, GameProgress gameProgress){
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(path)
        )) {
            oos.writeObject(gameProgress);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean zipFiles(String zipPath, String[] filesToZIP){
        boolean result = false;
        try (ZipOutputStream zout = new ZipOutputStream(
                new FileOutputStream(zipPath)))
        {
            for (String file : filesToZIP){
                try (FileInputStream fis = new FileInputStream(file)){
                    ZipEntry entry = new ZipEntry(file);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                    result = true;
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
