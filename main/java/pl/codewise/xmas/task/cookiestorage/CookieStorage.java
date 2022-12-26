package pl.codewise.xmas.task.cookiestorage;

import pl.codewise.xmas.task.Report;
import pl.codewise.xmas.task.cookie.Cookie;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class CookieStorage implements CookieQueue {

    private final int storageCapacity;
    private final LinkedList<Cookie> cookieList;

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
    public Collection<Cookie> getAllCookies() {
        return this.cookieList;
    }

    @Override
    public Report getReport() {
        return (cookieList.size() < 100) ?
                (() -> cookieList) :
                (() -> cookieList.stream().skip(cookieList.size() - 100).collect(Collectors.toList()));
    }
}
