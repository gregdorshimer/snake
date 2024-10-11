import java.util.ArrayList;

import static java.lang.Math.max;

// TODO display
// TODO create game-over screen
// TODO allow choosing a level when calling Main.main i.e. pass level in String[] args

public class Main {
    // WORLD CONSTANTS
    private static final int LEVEL = 1;
    private static final int BALL_RAD = 5;
    private static final int MOVE_DIST = BALL_RAD * 2;
    private static final int FRAME_SIZE = 500;
    private static final int SNAKE_COLOR = 0xa52a2a; // RGB -> 165,42,42
    private static final int APPLE_COLOR = 0xff0000; // RGB -> 255,0,0
    private static final int TICK_TIME = 800 / LEVEL;
    private static final int STARTING_LIVES = max(0, 4 - LEVEL);

    // game data
    static ArrayList<Ball> snake;
    static Ball apple;
    static int lives;
    static int apples;


    public static void main(String[] args) {

        // initialize game data
        apple = genApple();
        snake = initSnake();
        apples = 0;
        lives = STARTING_LIVES;

        long time = System.currentTimeMillis();
        while (true) {
            if ((System.currentTimeMillis() - time) > TICK_TIME) {
                if (lives == 0) {
                    gameOver();
                }
                else if (outOfBounds() || selfEat()) {
                    loseLife();
                }
                else if (gotApple()) {
                    apple = genApple();
                    snake.add(genSnake());
                    apples++;
                    // TODO
                    // move snake
                    // update directions
                }
                else {
                    // TODO
                    // move snake
                    // update directions
                    // read key event
                }
                time = System.currentTimeMillis();
            }
        }
    }

    // generates a new apple with random coordinates in the window
    static Ball genApple() {
        // TODO fix by using modulo, make apples and snake line up
        // TODO make sure apple can't appear on snake
        return new Ball("apple", APPLE_COLOR, (int)(Math.random() * FRAME_SIZE),
                (int)(Math.random() * FRAME_SIZE), "up");
    }

    static ArrayList<Ball> initSnake() {
        ArrayList<Ball> list = new ArrayList<Ball>();
        list.add(new Ball("snake", SNAKE_COLOR, FRAME_SIZE / 2, FRAME_SIZE / 2, "right"));
        return list;
    }


    static void moveBalls(ArrayList<Ball> balls) {
        // TODO don't use a foreach loop because you have to update dirs
        // or do multiple loops
        for (Ball b : balls) {
            // TODO call Ball.moveBall() on each ball in snake, and update the direction of each
            // after this method completes, Ball.dir for each Ball in snake should point in the direction that
            // the Ball should move next: i.e. each ball's dir will point to the ball ahead of it in the snake
        }
    }

    // generates a segment to be added to the end of the snake
    // TODO rename to addSegment() or growSnake()
    static Ball genSnake() {
        return new Ball("snake", SNAKE_COLOR, snake.get(snake.size() - 1).x,
                snake.get(snake.size() - 1).y, snake.get(snake.size() - 1).dir);
    }

    // returns if the snake caught the apple
    // TODO review math...
    static boolean gotApple() {
        int headX = snake.getFirst().x;
        int headY = snake.getFirst().y;
        int diffX = headX - apple.x;
        int diffY = headY - apple.y;
        return ((diffX * diffX + diffY * diffY) < MOVE_DIST * MOVE_DIST);
    }

    // checks if the snake is eating itself
    // TODO review math...
    static boolean selfEat() {
        int headX = snake.getFirst().x;
        int headY = snake.getFirst().y;
        for (int i = 1; i < snake.size(); i++) {
            int diffX = headX - snake.get(i).x;
            int diffY = headY - snake.get(i).y;
            if ((diffX * diffX + diffY * diffY) > MOVE_DIST * MOVE_DIST) {
                return false;
            }
        }
        return true;
    }

    // returns if the snake is running off the screen
    static boolean outOfBounds() {
        return snake.getFirst().x < 0 ||
                snake.getFirst().x > FRAME_SIZE ||
                snake.getFirst().y < 0 ||
                snake.getFirst().y > FRAME_SIZE;
    }

    // events for when a player loses a life
    static void loseLife() {
        lives--;
        snake = initSnake();
        apple = genApple();
    }

    // events for when player runs out of lives
    static void gameOver() {
        // TODO show screen with number of apples collected
        // break out of time loop
    }


}