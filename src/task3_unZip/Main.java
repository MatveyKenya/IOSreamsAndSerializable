package task3_unZip;

import task2_save.GameProgress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        //unZIP gameProgress!!!!

        String pathToSave = "D://Games//savegames//";
        String gp1Path = pathToSave + "gp1.dat";
        String gp2Path = pathToSave + "gp2.dat";
        String gp3Path = pathToSave + "gp3.dat";

        if (openZip(pathToSave + "zipFiles.zip")) System.out.println("unZip ok");

        GameProgress gp1 = getGameProgress(gp1Path);
        GameProgress gp2 = getGameProgress(gp2Path);
        GameProgress gp3 = getGameProgress(gp3Path);

        System.out.println(gp1);
        System.out.println(gp2);
        System.out.println(gp3);

    }

    static boolean openZip(String zipPath){
        boolean result = false;
        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream(zipPath))){
            ZipEntry entry;
            String name;

            while ((entry = zin.getNextEntry()) != null) {
                // получим название файла
                name = entry.getName();
                // распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            result = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    static GameProgress getGameProgress(String path){
        GameProgress gameProgress = null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path))){
            gameProgress = (GameProgress) ois.readObject();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }

}
