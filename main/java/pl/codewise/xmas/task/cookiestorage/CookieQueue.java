package pl.codewise.xmas.task.cookiestorage;

import pl.codewise.xmas.task.Report;
import pl.codewise.xmas.task.cookie.Cookie;

public interface CookieQueue {

    void add(Cookie message);

    Report getReport();
}
