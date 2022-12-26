package pl.codewise.xmas.task.cookie;

/**
 * You can modify this class
 */
public abstract class Cookie {

    public enum CookieType {
        CHRISTMAS_TREE,
        SANTA_CLAUS_HAT,
        SLEDGE
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
