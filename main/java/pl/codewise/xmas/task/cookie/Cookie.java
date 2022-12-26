package pl.codewise.xmas.task.cookie;

/**
 * You can modify this class
 */
public abstract class Cookie {
    private static int currentId = 0;

    public static int getNextId() {
        return currentId++;
    }

    private final int id;
    private final CookieType cookieType;

    public Cookie(int id, CookieType cookieType) {
        this.id = id;
        this.cookieType = cookieType;
    }

    public int getId() {
        return id;
    }

    public CookieType getCookieType() {
        return cookieType;
    }

    public abstract CookieState getCookieState();

    public abstract void tick();

}
