package org.example;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class CreateZip {
    public static void main(String[] args) {
        String outputZipFileName = "educative.zip";
        String[] filesToBeWritten = {"org/example/one.txt", "org/example/two.txt", "org/example/three.txt"};

        try {
            printZipFiles();
            // create a ZipOutputStream object
            FileOutputStream fos = new FileOutputStream(outputZipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i=0; i < filesToBeWritten.length; i++) {
                String filePath = filesToBeWritten[i];
                int subDirsToFileNb = filePath.split("/").length;
                InputStream fis = CreateZip.class.getClassLoader().getResourceAsStream(filePath);
                // Start writing a new file entry
                zos.putNextEntry(new ZipEntry(filePath.split("/")[subDirsToFileNb - 1]));

                int length;
                // create byte buffer
                byte[] buffer = new byte[1024];

                // read and write the content of the file
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                // current file entry is written and current zip entry is closed
                zos.closeEntry();

                // close the InputStream of the file
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();
            System.out.println("\nThe file is written successfully");
            printZipFiles();
        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
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
