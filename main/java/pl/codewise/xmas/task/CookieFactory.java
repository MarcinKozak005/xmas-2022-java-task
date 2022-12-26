package pl.codewise.xmas.task;

import pl.codewise.xmas.task.cookie.Cookie;
import pl.codewise.xmas.task.cookiestorage.CookieQueue;
import pl.codewise.xmas.task.elf.Elf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CookieFactory {
    private final ArrayList<Elf> elfList = new ArrayList<>();
    private final CookieQueue cookieQueue;
    private DoughState doughState = DoughState.FRESH;

    public CookieFactory(CookieQueue cookieQueue) {
        this.cookieQueue = cookieQueue;
    }

    public void addElf(Elf elf) {
        elfList.add(elf);
    }

    public void step() {
        cookieQueue.getAllCookies().forEach(Cookie::tick);
        elfList.forEach(e -> cookieQueue.add(e.makeCookie(doughState)));
    }

    public void setDoughState(DoughState doughState) {
        this.doughState = doughState;
    }

    public DoughState getDoughState() {
        return doughState;
    }

    public Report getReport() {
        return cookieQueue.getReport();
    }

    public Collection<Cookie> getCookies() {
        return Collections.unmodifiableCollection(cookieQueue.getAllCookies());
    }

    public List<Elf> getElves() {
        return Collections.unmodifiableList(elfList);
    }

    @Override
    public String toString() {
        StringBuilder elvesString = new StringBuilder();
        elfList.stream()
                .collect(Collectors.groupingBy(Elf::getMadeCookieShape))
                .forEach(
                        (shape, el) -> elvesString.append("\t").append(shape).append(":").append(el.size()).append("\n")
                );
        return "DoughState: " + doughState + "\n" +
                "Elves:\n" + elvesString +
                "CookieQueue: " + cookieQueue.size() + "/" + cookieQueue.getCapacity();
    }
}
