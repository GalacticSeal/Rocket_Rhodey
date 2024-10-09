package Engine;

public class Keybinds {
    private static Key moveLeft = Key.A;
    private static Key moveRight = Key.D;
    private static Key jump = Key.SPACE;
    private static Key crouch = Key.DOWN;

    // get methods
    public static Key getCrouchKey() {
        return crouch;
    }
    public static Key getJumpKey() {
        return jump;
    }
    public static Key getMoveLeftKey() {
        return moveLeft;
    }
    public static Key getMoveRightKey() {
        return moveRight;
    }

    // set methods
    public static void setCrouchKey(Key code) {
        crouch = code;
    }
    public static void setJumpKey(Key code) {
        jump = code;
    }
    public static void setMoveLeftKey(Key code) {
        moveLeft = code;
    }
    public static void setMoveRightKey(Key code) {
        moveRight = code;
    }
}
