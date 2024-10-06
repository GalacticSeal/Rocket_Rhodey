package Engine;

public class Keybinds {
    private static Key moveLeft = Key.A;
    private static Key moveRight = Key.D;
    private static Key jump = Key.SPACE;
    private static Key crouch = Key.DOWN;

    public static Key getCrouchKey() {
        return Keybinds.crouch;
    }
    public static Key getJumpKey() {
        return Keybinds.jump;
    }
    public static Key getMoveLeftKey() {
        return Keybinds.moveLeft;
    }
    public static Key getMoveRightKey() {
        return Keybinds.moveRight;
    }
}
