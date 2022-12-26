package pl.codewise.xmas.task.cookiestorage;

import pl.codewise.xmas.task.Report;
import pl.codewise.xmas.task.cookie.Cookie;
import pl.codewise.xmas.task.cookie.CookieState;

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
    public Report getReport() {
        return (cookieList.size() < 100) ?
                (() -> cookieList.stream()
                        .filter(c -> c.getCookieState() == CookieState.FRESH)
                        .collect(Collectors.toList())) :
                (() -> cookieList.stream()
                        .skip(cookieList.size() - 100)
                        .filter(c -> c.getCookieState() == CookieState.FRESH)
                        .collect(Collectors.toList()));
    }

    @Override
    public Collection<Cookie> getAllCookies() {
        return this.cookieList;
    }

    @Override
    public int size() {
        return cookieList.size();
    }

    @Override
    public int getCapacity() {
        return storageCapacity;
    }

    @Override
    public String toString() {
        return cookieList.toString();
    }
}
