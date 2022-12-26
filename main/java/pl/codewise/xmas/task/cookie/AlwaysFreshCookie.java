package pl.codewise.xmas.task.cookie;

public class AlwaysFreshCookie extends Cookie {

    public AlwaysFreshCookie(int id, CookieType cookieType) {
        super(id, cookieType);
    }

    @Override
    public CookieState getCookieState() {
        return CookieState.FRESH;
    }

    @Override
    public void tick() {
    }
}
