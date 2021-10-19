package it.extend.exercises1;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    public static String up(String currentPath) {
        File file = new File(currentPath);
        if (file.exists()) {
            String parent = file.getParent();
            if (parent != null) {
                dir(parent);
                return parent;
            }
        }
        return currentPath;
    }

    public static String cd(String currentPath, String newDir) {
        String pathname = currentPath + "/" + newDir;
        File file = new File(pathname);
        if (file.exists()) {
            dir(pathname);
            return pathname;
        }
        return currentPath;
    }

    public static void dir(String currentPath) {
        File file = new File(currentPath);
        if (file.exists() && file.isDirectory()) {
            String[] lists = file.list();
            if (lists.length == 0) {
                System.out.println("Folder is empty.");
            } else {
                for (String s : lists) {
                    System.out.println(s);
                }
            }
        } else {
            System.out.println("Wrong path entered!");
        }
    }

    public static void info(String currentPath, String path) {
        String obj = currentPath + "/" + path;
        File file = new File(obj);
        if (file.exists()) {
            System.out.println("Name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Relative path: " + file.getPath());
            System.out.println("Size: " + file.length());
            Path p = Paths.get(obj);
            try {
                BasicFileAttributes bfa = Files.readAttributes(p, BasicFileAttributes.class);
                System.out.println("Created: " + time(bfa.creationTime().toMillis()));
            } catch (IOException ex) {
                System.out.println("Unable to show creation time);
            }
            System.out.println("Last Modified: " + time(file.lastModified()));
        } else {
            System.out.println("Wrong path entered!");
        }
    }

    public static String time(long l) {
        Instant instant = Instant.ofEpochMilli(l);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
        return dateTime.format(dateTimeFormatter);
    }

    public static void createDir(String currentPath, String path) {
        File folder = new File(currentPath + "/" + path);
        String s = folder.getName();
        boolean bool = s.indexOf(92) * s.indexOf(47) * s.indexOf(58) * s.indexOf(63)
                * s.indexOf(42) * s.indexOf(34) * s.indexOf(60) * s.indexOf(62)
                * s.indexOf(124) < 0;
        try {
            if (!folder.exists()) {
                if (bool) {
                    folder.mkdir();
                    System.out.println("Created a folder called " + folder.getName());
                } else {
                    System.out.println("A file name can't contain any of the following characters: ");
                    char[] chars = {92, 47, 58, 63, 42, 34, 60, 62, 124};
                    System.out.println(chars);
                }
            } else {
                System.out.println("Folder called " + folder.getName() + " already exists.");
            }
        } catch (Exception e) {
            System.out.println("Couldn't create a folder called " + folder.getName());
        }
    }

    public static void show(String currentPath, String fileToShow) {
        Path path = FileSystems.getDefault().getPath(currentPath, fileToShow);
        try {
            String mimeType = Files.probeContentType(path);
            if ("text/plain".equals(mimeType)) {
                Files.lines(path).forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("Unable to show file content");
        }
    }
}