package pl.codewise.xmas.task;

import pl.codewise.xmas.task.cookie.Cookie;

public interface CookieQueue {

    void add(Cookie message);

    Report getReport();
}
