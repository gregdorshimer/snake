
public class Ball {
    String type;
    int color;
    int x;
    int y;
    String dir;

    // constructor
    Ball(String type, int color, int x, int y, String dir) {
        this.type = type; // "apple" or "snake"
        this.color = color; // Hex RGB color
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public boolean equals(Ball other) {
        return this.type.equals(other.type) &&
                this.color == other.color &&
                this.x == other.x &&
                this.y == other.y &&
                this.dir.equals(other.dir);
    }

    static Ball makeApple(int x, int y) {
        return new Ball("apple", Main.APPLE_COLOR, x, y, "up");
    }

    // moves the ball one unit of movement in its direction
    void moveBall(int dist) {
        switch (this.dir) {
            case "up" -> this.y -= dist;
            case "down" -> this.y += dist;
            case "left" -> this.x -= dist;
            case "right" -> this.x += dist;
        }
    }
}
