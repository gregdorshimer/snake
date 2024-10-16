import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallTests {

    @Test
    void ballTest() {
        Ball ball1 = new Ball("apple", Main.SNAKE_COLOR, 1, 1, "up");
        Ball ball2 = new Ball("snake", Main.APPLE_COLOR, 0, 0, "down");
        Ball ball3 = new Ball("test", 0x04232b, -1000, -1000, "left");
        Ball ball4 = new Ball("fake", Main.SNAKE_COLOR, 1000, 1000, "right");
        Ball ball5 = new Ball("fake", Main.SNAKE_COLOR, 1000, 1000, "smash");

        assertEquals("apple", ball1.type);
        assertEquals("snake", ball2.type);
        assertEquals("test", ball3.type);
        assertEquals("fake", ball4.type);
        assertEquals(Main.SNAKE_COLOR, ball1.color);
        assertEquals(Main.APPLE_COLOR, ball2.color);
        assertEquals(0x04232b, ball3.color);
        assertEquals(1, ball1.x);
        assertEquals(0, ball2.x);
        assertEquals(-1000, ball3.x);
        assertEquals(1000, ball4.x);
        assertEquals(1, ball1.y);
        assertEquals(0, ball2.y);
        assertEquals(-1000, ball3.y);
        assertEquals(1000, ball4.y);
        assertEquals("up", ball1.dir);
        assertEquals("down", ball2.dir);
        assertEquals("left", ball3.dir);
        assertEquals("right", ball4.dir);
        assertEquals("smash", ball5.dir);
    }

    @Test
    void equalsTest() {
        Ball ball1 = new Ball("apple", Main.APPLE_COLOR, 1, 1, "up");
        Ball ball2 = new Ball("apple", Main.APPLE_COLOR, 1, 1, "up");
        Ball ball3 = new Ball("snake", Main.SNAKE_COLOR, 2, 2, "left");

        assertTrue(ball1.equals(ball2));
        assertFalse(ball1.equals(ball3));
    }

    @Test
    void makeAppleTest() {
        Ball ball1 = Ball.makeApple(1, 1);
        Ball ball2 = Ball.makeApple(0, 0);
        Ball ball3 = Ball.makeApple(-1000, -1000);
        Ball ball4 = Ball.makeApple(1000, 1000);

        assertEquals("apple", ball1.type);
        assertEquals(Main.APPLE_COLOR, ball1.color);
        assertEquals("up", ball1.dir);
        assertEquals(1, ball1.x);
        assertEquals(0, ball2.x);
        assertEquals(-1000, ball3.x);
        assertEquals(1000, ball4.x);
        assertEquals(1, ball1.y);
        assertEquals(0, ball2.y);
        assertEquals(-1000, ball3.y);
        assertEquals(1000, ball4.y);
    }

    @Test
    void moveBallTest() {
        Ball ball1 = new Ball("snake", Main.SNAKE_COLOR, 100, 100, "up");
        Ball ball2 = new Ball("snake", Main.SNAKE_COLOR, 100, 100, "down");
        Ball ball3 = new Ball("snake", Main.SNAKE_COLOR, 100, 100, "left");
        Ball ball4 = new Ball("snake", Main.SNAKE_COLOR, 100, 100, "right");

        ball1.moveBall(Main.BALL_RAD * 2);
        ball2.moveBall(Main.BALL_RAD * 2);
        ball3.moveBall(Main.BALL_RAD * 2);
        ball4.moveBall(Main.BALL_RAD * 2);

        assertEquals(100, ball1.x);
        assertEquals(90, ball1.y);
        assertEquals(100, ball2.x);
        assertEquals(110, ball2.y);
        assertEquals(90, ball3.x);
        assertEquals(100, ball3.y);
        assertEquals(110, ball4.x);
        assertEquals(100, ball4.y);

        ball1.moveBall(0);
        ball2.moveBall(0);
        ball3.moveBall(0);
        ball4.moveBall(0);

        assertEquals(100, ball1.x);
        assertEquals(90, ball1.y);
        assertEquals(100, ball2.x);
        assertEquals(110, ball2.y);
        assertEquals(90, ball3.x);
        assertEquals(100, ball3.y);
        assertEquals(110, ball4.x);
        assertEquals(100, ball4.y);

        ball1.moveBall(-10);
        ball2.moveBall(-10);
        ball3.moveBall(-10);
        ball4.moveBall(-10);

        assertEquals(100, ball1.x);
        assertEquals(100, ball1.y);
        assertEquals(100, ball2.x);
        assertEquals(100, ball2.y);
        assertEquals(100, ball3.x);
        assertEquals(100, ball3.y);
        assertEquals(100, ball4.x);
        assertEquals(100, ball4.y);
    }
}
