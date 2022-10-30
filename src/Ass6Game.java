// 209205665 Roy Amit

import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a Game object, initializes and runs it.
 */
public class Ass6Game {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int FRAMES_PER_SEC = 60;

    /**
     * This function is the main function. Here we create a new game, initialize it and run it.
     *
     * @param args - gets nothing.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", FRAME_WIDTH, FRAME_HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui, FRAMES_PER_SEC);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor());
        List<LevelInformation> levelInfo = new ArrayList<>();
        if (args.length != 0) {
            for (String arg : args) {
                if (isNumber(arg)) {
                    if (Integer.parseInt(arg) == 1) {
                        levelInfo.add(new LevelOne());
                    }
                    if (Integer.parseInt(arg) == 2) {
                        levelInfo.add(new LevelTwo());
                    }
                    if (Integer.parseInt(arg) == 3) {
                        levelInfo.add(new LevelThree());
                    }
                    if (Integer.parseInt(arg) == 4) {
                        levelInfo.add(new LevelFour());
                    }
                }
            }
        } else {
            levelInfo.add(new LevelOne());
            levelInfo.add(new LevelTwo());
            levelInfo.add(new LevelThree());
            levelInfo.add(new LevelFour());
        }
        gameFlow.runLevels(levelInfo);
        gui.close();
    }

    /**
     * This function checks whether the argument is a number.
     * if so return true, false otherwise.
     *
     * @param str - string.
     * @return true if the argument is a number, false otherwise.
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return Integer.parseInt(str) >= 1 && Integer.parseInt(str) <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
