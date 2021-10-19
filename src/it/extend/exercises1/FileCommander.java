package it.extend.exercises1;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static it.extend.exercises1.FileUtils.*;

public class FileCommander {

    public final void run(String[] args) {
        List<String> availableLanguages = Arrays.asList("en", "de", "fr");
        String askedLang = (args.length > 0) ? args[0].toLowerCase().trim() : "";
        if (!availableLanguages.contains(askedLang)) {
            System.out.println("Usage : program_name language {en,de,fr}");
            System.out.println("Example :\njava it.extend.ExerciseLauncher en");
            System.exit(0);
        }
        Locale locale = new Locale(askedLang);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("filemanager", locale);
        String availableCommands = resourceBundle.getString("availableCommands");
        System.out.println(availableCommands);
        Scanner s = new Scanner(System.in);
        String prompt = resourceBundle.getString("prompt");
        String currentDir = "/";
        boolean work = true;
        while (work) {
            System.out.println("currentDir : " + currentDir);
            System.out.print(prompt);
            String str = s.nextLine().toLowerCase().trim();
            switch (str) {
                case "..":
                    currentDir = up(currentDir);
                    break;
                case "cd":
                    System.out.println("Enter folder name: ");
                    currentDir = cd(currentDir, s.nextLine().trim());
                    break;
                case "help":
                    System.out.println(availableCommands);
                    break;
                case "dir":
                    dir(currentDir);
                    break;
                case "info":
                    System.out.println("Enter desired file/folder's path: ");
                    info(currentDir, s.nextLine().trim());
                    break;
                case "mkdir":
                    System.out.println("Enter new folder's path: ");
                    createDir(currentDir, s.nextLine().trim());
                    break;
                case "show":
                    System.out.println("Enter texts file name to show content: ");
                    show(currentDir, s.nextLine().trim());
                    break;
                case "quit":
                    work = false;
                    break;
                default:
                    System.out.println("Command you entered, doesn't exist!");
            }
        }
        s.close();
        System.out.println("You closed File Manager.");
    }
}