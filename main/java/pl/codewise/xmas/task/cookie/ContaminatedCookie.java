package pl.codewise.xmas.task.cookie;

public class ContaminatedCookie extends Cookie {

    private int timeSinceCreation;
    private CookieState cookieState;

    public ContaminatedCookie(int id, CookieType cookieType) {
        super(id, cookieType);
        this.cookieState = CookieState.FRESH;
        this.timeSinceCreation = 0;
    }

    @Override
    public CookieState getCookieState() {
        return cookieState;
    }

    @Override
    public void tick() {
        timeSinceCreation++;
        // 5min x 60s = 300s
        if (timeSinceCreation == 300) cookieState = CookieState.ROTTEN;
    }

    @Override
    public String toString() {
        return getId() + " (ContaminatedCookie): " + getCookieType()
                + " [" + timeSinceCreation + "," + cookieState + "]";
    }
}
