package it.extend;

import it.extend.exercises1.FileCommander;

public class ExerciseLauncher {
    private ExerciseLauncher() {
    }

    public static void main(String[] args) {
        FileCommander fileCommander = new FileCommander();
        fileCommander.run(args);
    }
}
