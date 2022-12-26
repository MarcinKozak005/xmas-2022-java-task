package pl.codewise.xmas.task.cookiestorage;

import pl.codewise.xmas.task.Report;
import pl.codewise.xmas.task.cookie.Cookie;

import java.util.Collection;

public interface CookieQueue {

    void add(Cookie message);

    Report getReport();

    Collection<Cookie> getAllCookies();

    int size();

    int getCapacity();
}
