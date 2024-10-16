import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// TODO refactor to utilize JUnit @BeforeEach and @BeforeAll

public class MainTest {

    // create variables for testing
    private ArrayList<Ball> snake1;
    private Ball snakePart1;
    private ArrayList<Ball> snake2;
    private ArrayList<Ball> snake3;
    private ArrayList<Ball> snake4;
    private ArrayList<Ball> snake5;
    private ArrayList<Ball> snake6;
    private ArrayList<Ball> snake7;
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

        snake3 = Main.initSnake();
        snake3.getLast().dir = "down";
        Main.growSnake(snake3);

        snake4 = Main.initSnake();
        snake4.getLast().dir = "left";
        Main.growSnake(snake4);

        snake5 = Main.initSnake();
        snake5.getLast().dir = "right";
        Main.growSnake(snake5);

        snake6 = Main.initSnake();
        snake6.getLast().dir = "up";
        Main.growSnake(snake6);
        snake6.getLast().dir = "right";
        Main.growSnake(snake6);
        snake6.getLast().dir = "down";
        Main.growSnake(snake6);
        snake6.getLast().dir = "left";
        Main.growSnake(snake6);

        snake7 = Main.initSnake();
        snake7.getFirst().x = -100;
        snake7.getFirst().y = -100;

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

        assertEquals("up", snake2.getLast().dir);
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
        initTestData();

        assertEquals(250, snake2.getFirst().x);
        assertEquals(250, snake2.getFirst().y);
        assertEquals(250, snake2.getLast().x);
        assertEquals(260, snake2.getLast().y);

        assertEquals(250, snake3.getFirst().x);
        assertEquals(250, snake3.getFirst().y);
        assertEquals(250, snake3.getLast().x);
        assertEquals(240, snake3.getLast().y);

        assertEquals(250, snake4.getFirst().x);
        assertEquals(250, snake4.getFirst().y);
        assertEquals(260, snake4.getLast().x);
        assertEquals(250, snake4.getLast().y);

        assertEquals(250, snake5.getFirst().x);
        assertEquals(250, snake5.getFirst().y);
        assertEquals(240, snake5.getLast().x);
        assertEquals(250, snake5.getLast().y);

        Main.moveBalls(snake2);
        Main.moveBalls(snake3);
        Main.moveBalls(snake4);
        Main.moveBalls(snake5);

        assertEquals(250, snake2.getFirst().x);
        assertEquals(240, snake2.getFirst().y);
        assertEquals(250, snake2.getLast().x);
        assertEquals(250, snake2.getLast().y);

        assertEquals(250, snake3.getFirst().x);
        assertEquals(260, snake3.getFirst().y);
        assertEquals(250, snake3.getLast().x);
        assertEquals(250, snake3.getLast().y);

        assertEquals(240, snake4.getFirst().x);
        assertEquals(250, snake4.getFirst().y);
        assertEquals(250, snake4.getLast().x);
        assertEquals(250, snake4.getLast().y);

        assertEquals(260, snake5.getFirst().x);
        assertEquals(250, snake5.getFirst().y);
        assertEquals(250, snake5.getLast().x);
        assertEquals(250, snake5.getLast().y);
    }

    @Test
    void selfEatTest() {
        initTestData();

        assertFalse(Main.selfEat(snake1));
        assertTrue(Main.selfEat(snake6));
    }

    @Test
    void outOfBoundsTest() {
        initTestData();

        assertFalse(Main.outOfBounds(snake1));
        assertTrue(Main.outOfBounds(snake7));
    }
}
