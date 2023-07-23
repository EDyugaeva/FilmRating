package com.example.kinorate.utills;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class SavingFiles {
    private static String savePath = "C:/Users/ekaterina/IdeaProjects/KinoRate/src/main/webapp/images";


    // Save the file to the server
    public static String saveImage(Part part, String title, String type) throws IOException {

        String fileName = replaceSpaces(title) + ".jpg";

        System.out.println("file name" + fileName);

        // Specify the directory where you want to save the uploaded files
        String fullPath = savePath + type;

        System.out.println("full path" + fullPath);

        // Create the save directory if it does not exist
        File fileSaveDir = new File(fullPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String filePath = fullPath + File.separator + fileName;

        System.out.println("file Path" + filePath);

        String imageUrl = type + File.separator + fileName;

        part.write(filePath);

        return imageUrl;
    }


    private static String replaceSpaces(String title) {
        return title.replaceAll("\\s+", "_");
    }


}
