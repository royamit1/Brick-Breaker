// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * This class represents level four in the game, where the ghosts can change colors.
 */
public class ChangeColorSprite implements Sprite {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private Color[] colors = {Color.BLUE, Color.PINK, Color.BLUE, Color.GREEN, Color.YELLOW,
            Color.ORANGE, Color.RED, Color.MAGENTA};
    private final Counter counter = new Counter();

    /**
     * This function draws the sprite to the screen.
     *
     * @param d - DrawSurface object
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        d.setColor(Color.yellow);
        d.fillCircle(200, 500, 50);
        for (int i = 0; i < 70; i++) {
            d.setColor(Color.black);
            d.drawLine(200, 500, 250, 465 + i);
        }
        d.setColor(Color.black);
        d.fillCircle(215, 470, 6);
        d.setColor(colors[0]);
        d.fillCircle(670, 550, 30);
        d.fillRectangle(640, 550, 60, 40);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(650 + j * 20, 580, 640 + i + j * 20, 590);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(658, 550, 8);
        d.fillCircle(682, 550, 8);
        d.setColor(Color.black);
        d.fillCircle(653, 550, 4);
        d.fillCircle(677, 550, 4);
        d.setColor(colors[1]);
        d.fillCircle(600, 550, 30);
        d.fillRectangle(570, 550, 60, 40);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(580 + j * 20, 580, 570 + i + j * 20, 590);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(588, 550, 8);
        d.fillCircle(612, 550, 8);
        d.setColor(Color.black);
        d.fillCircle(583, 550, 4);
        d.fillCircle(607, 550, 4);
        d.setColor(colors[2]);
        d.fillCircle(740, 550, 30);
        d.fillRectangle(710, 550, 60, 40);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(720 + j * 20, 580, 710 + i + j * 20, 590);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(728, 550, 8);
        d.fillCircle(752, 550, 8);
        d.setColor(Color.black);
        d.fillCircle(723, 550, 4);
        d.fillCircle(747, 550, 4);
        d.setColor(colors[3]);
        d.fillCircle(60, 80, 30);
        d.fillRectangle(30, 40, 60, 40);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(40 + j * 20, 50, 30 + i + j * 20, 40);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(48, 80, 8);
        d.fillCircle(72, 80, 8);
        d.setColor(Color.black);
        d.fillCircle(48, 84, 4);
        d.fillCircle(72, 84, 4);
        d.setColor(colors[4]);
        d.fillCircle(130, 80, 30);
        d.fillRectangle(100, 40, 60, 40);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(110 + j * 20, 50, 100 + i + j * 20, 40);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(118, 80, 8);
        d.fillCircle(142, 80, 8);
        d.setColor(Color.black);
        d.fillCircle(118, 80, 4);
        d.fillCircle(142, 80, 4);
        d.setColor(colors[5]);
        d.fillCircle(70, 550, 30);
        d.fillRectangle(30, 520, 40, 60);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(40, 530 + j * 20, 30, 520 + i + j * 20);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(70, 538, 8);
        d.fillCircle(70, 562, 8);
        d.setColor(Color.black);
        d.fillCircle(70, 538, 4);
        d.fillCircle(70, 562, 4);
        d.setColor(colors[6]);
        d.fillCircle(70, 480, 30);
        d.fillRectangle(30, 450, 40, 60);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 20; i++) {
                d.setColor(Color.black);
                d.drawLine(40, 460 + j * 20, 30, 450 + i + j * 20);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(70, 468, 8);
        d.fillCircle(70, 492, 8);
        d.setColor(Color.black);
        d.fillCircle(70, 464, 4);
        d.fillCircle(70, 488, 4);
    }

    /**
     * This function notifies the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        this.counter.increase(1);
        if (this.counter.getValue() % 30 == 0) {
            this.colors = updateColors(this.colors);
        }
    }

    /**
     * This function adds a sprite to the game.
     *
     * @param g - GameLevel object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This function changes the colors of the array.
     *
     * @param colors - colors
     * @return returns a new colors array in a different order.
     */
    public Color[] updateColors(Color[] colors) {
        Random rand = new Random();
        Color[] newColors = new Color[8];
        int random = rand.nextInt(8);
        for (int i = 0; i < 8; i++) {
            newColors[i] = colors[(random + i) % 8];
        }
        return newColors;
    }

}