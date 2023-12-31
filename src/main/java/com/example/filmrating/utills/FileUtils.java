package com.example.filmrating.utills;

import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Slf4j
public class FileUtils {

    /**
     * Saving images to the filesystem.
     *
     * @param part  - data from servlet
     * @param title - title of film/image
     * @param type  - root where to save, there will be option to save users photo
     * @return url to save in database
     * @throws IOException
     */
    public static String saveImage(Part part, String title, String type, String absoluteSavePath) throws IOException {
        log.info("Saving images to folder {} ", type);
        String fileName = replaceExtras(title) + ".jpg";
        File fileSaveDir = new File(absoluteSavePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String filePath = absoluteSavePath + File.separator + fileName;
        String imageUrl = "images" + File.separator + type + File.separator + fileName;
        part.write(filePath);
        log.info("url to save is: {} ", imageUrl);
        return imageUrl;
    }


    private static String replaceExtras(String title) {
        return title.replaceAll("\\s+", "_").replaceAll(":", "");
    }

    /**
     * Delete file by relative path
     *
     * @param path relative path
     */
    public static void deleteFile(String path) {
        String baseDirectory = System.getProperty("user.dir");
        try {
            Path fileToDelete = Paths.get(baseDirectory, path);
            Files.delete(fileToDelete);
            log.info("File deleted successfully.");
        } catch (NoSuchFileException e) {
            log.warn("File not found", e);
        } catch (DirectoryNotEmptyException e) {
            log.warn("Cannot delete: Directory is not empty", e);
        } catch (IOException e) {
            log.warn("Error deleting file", e);
        }
    }
}
