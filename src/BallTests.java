
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        assertEquals("up", ball1.type);
        assertEquals("down", ball2.type);
        assertEquals("left", ball3.type);
        assertEquals("right", ball4.type);
        assertEquals("smash", ball5.type);
    }

    @Test
    void makeAppleTest() {
        // TODO
    }

    @Test
    void moveBallTest() {
        // TODO
    }

}
