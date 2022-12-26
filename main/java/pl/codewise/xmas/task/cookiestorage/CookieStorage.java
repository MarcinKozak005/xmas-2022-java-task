package pl.codewise.xmas.task.cookiestorage;

import pl.codewise.xmas.task.Report;
import pl.codewise.xmas.task.cookie.Cookie;

import java.util.LinkedList;

public class CookieStorage implements CookieQueue {

    private final int storageCapacity;
    private LinkedList<Cookie> cookieList;

    public CookieStorage(int storageCapacity) {
        this.storageCapacity = storageCapacity;
        this.cookieList = new LinkedList<>();
    }

    @Override
    public void add(Cookie cookie) {
        if (cookieList.size() == storageCapacity) cookieList.pop();
        cookieList.add(cookie);
    }

    @Override
    public Report getReport() {
        return (() -> cookieList);
    }
}
