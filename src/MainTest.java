import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// TODO refactor to utilize JUnit @BeforeEach and @BeforeAll

public class MainTest {

    // create variables for testing
    private ArrayList<Ball> snake1;
    private Ball snakePart1;
    private ArrayList<Ball> snake2;
    private Ball apple1;
    private Ball apple2;

    void initTestData() {
        snake1 = new ArrayList<Ball>();
        snakePart1 = new Ball("snake", Main.SNAKE_COLOR, Main.FRAME_SIZE / 2, Main.FRAME_SIZE / 2, "");
        snake1.add(snakePart1);
        Main.growSnake(snake1);

        snake2 = Main.initSnake();
        snake2.getLast().dir = "up";
        Main.growSnake(snake2);

        apple1 = Main.genApple(snake1);
        apple2 = new Ball("apple", Main.APPLE_COLOR, Main.FRAME_SIZE / 2 - 10, Main.FRAME_SIZE / 2, "");
    }

    @Test
    void initSnakeTest() {
        initTestData();

        assertEquals(2, snake1.size());
        assertTrue(snakePart1.equals(Main.initSnake().getFirst()));
    }

    @Test
    void growSnakeTest() {
        initTestData();

        assertEquals(240, snake1.getLast().x);
        assertEquals(250, snake1.getLast().y);

        assertEquals(250, snake2.getLast().x);
        assertEquals(260, snake2.getLast().y);
    }

    @Test
    void overlapTest() {
        initTestData();

        assertTrue(apple1.x <= snakePart1.x - (Main.BALL_RAD * 2) ||
                apple1.x >= snakePart1.x + (Main.BALL_RAD * 2));
        assertTrue(apple1.y <= snakePart1.y - (Main.BALL_RAD * 2) ||
                apple1.y >= snakePart1.y + (Main.BALL_RAD * 2));
    }

    @Test
    void overlapListTest() {
        initTestData();

        assertFalse(Main.overlapList(apple1, snake1));
        assertTrue(Main.overlapList(apple2, snake1));
    }

    @Test
    void genAppleTest() {
        initTestData();

        assertFalse(Main.overlapList(apple1, snake1));
        assertTrue(Main.BALL_RAD <= apple1.x);
        assertTrue(apple1.x <= Main.FRAME_SIZE - Main.BALL_RAD);
        assertTrue(Main.BALL_RAD <= apple1.y);
        assertTrue(apple1.y <= Main.FRAME_SIZE - Main.BALL_RAD);
    }

    @Test
    void moveBallsTest() {

    }

    @Test
    void gotAppleTest() {

    }

    @Test
    void selfEatTest() {
        // TODO
    }

    @Test
    void outOfBoundsTest() {
        // TODO
    }
}
