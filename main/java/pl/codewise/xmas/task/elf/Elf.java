package pl.codewise.xmas.task.elf;

import pl.codewise.xmas.task.DoughState;
import pl.codewise.xmas.task.cookie.AlwaysFreshCookie;
import pl.codewise.xmas.task.cookie.ContaminatedCookie;
import pl.codewise.xmas.task.cookie.Cookie;
import pl.codewise.xmas.task.cookie.CookieType;

import java.util.Random;

public class Elf {

    private static final Random random = new Random();
    private static final CookieType[] cookieTypes = CookieType.values();
    private final int id;
    private final MadeCookieShape madeCookieShape;

    public Elf(int id, MadeCookieShape madeCookieShape) {
        this.id = id;
        this.madeCookieShape = madeCookieShape;
    }

    private static CookieType getRandomCookieType() {
        return cookieTypes[random.nextInt(cookieTypes.length)];
    }

    public Cookie makeCookie(DoughState doughState) {
        CookieType ct = switch (madeCookieShape) {
            case ONLY_CHRISTMAS_TREE -> CookieType.CHRISTMAS_TREE;
            case ONLY_SANTA_CLAUS_HAT -> CookieType.SANTA_CLAUS_HAT;
            case ONLY_SLEDGE -> CookieType.SLEDGE;
            case RANDOM -> getRandomCookieType();
        };
        return (doughState == DoughState.FRESH) ?
                new AlwaysFreshCookie(Cookie.getNextId(), ct) :
                new ContaminatedCookie(Cookie.getNextId(), ct);
    }
}
