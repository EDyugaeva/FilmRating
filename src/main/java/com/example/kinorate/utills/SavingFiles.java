package com.example.kinorate.utills;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class SavingFiles {
    private static String savePath = "D:/java/project/";


    // Save the file to the server
    public static String saveImage(Part part, String title, String type) throws IOException {

        String fileName = replaceSpaces(title) + ".jpg";

        // Specify the directory where you want to save the uploaded files
        String fullPath = savePath + type;
        // Create the save directory if it does not exist
        File fileSaveDir = new File(fullPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String filePath = fullPath + File.separator + fileName;

        part.write(filePath);

        return filePath;
    }


    private static String replaceSpaces(String title) {
        return title.replaceAll("\\s+", "_");
    }


}
