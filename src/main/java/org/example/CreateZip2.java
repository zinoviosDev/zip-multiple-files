package org.example;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CreateZip2 {

    public static void main(String[] args) {
        printZipFiles();
        createZip();
        printZipFiles();
    }

    public static void createZip() {
        String outputZipFileName = "educative.zip";
        List<String> filesToBeWritten = Arrays.asList("org/example/one.txt", "org/example/two.txt", "org/example/three.txt");
        // voir aussi https://mkyong.com/java/how-to-convert-inputstream-to-file-in-java/
        // Create zip file stream.
//        InputStream fis = CreateZip.class.getClassLoader().getResourceAsStream(filePath);
//        new File(fis);
        try (ZipArchiveOutputStream archive = new ZipArchiveOutputStream(new FileOutputStream(outputZipFileName))) {
            for(String file : filesToBeWritten) {
                // Add file to zip archive
                String[] filePathArray = file.split("/");
                File file_1 = new File(file);
                ZipArchiveEntry entry_1 = new ZipArchiveEntry(file_1, filePathArray[filePathArray.length - 1]);
                archive.putArchiveEntry(entry_1);
                IOUtils.copy(new FileInputStream(file_1), archive);
                archive.closeArchiveEntry();
            }
            // Complete archive entry addition.
            archive.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printZipFiles() {
        System.out.print("\nThe zip files in the current directory is: \n");
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.getName().contains(".zip")){
                System.out.print(f.getName() + ", ");
            }
        }
    }
}
