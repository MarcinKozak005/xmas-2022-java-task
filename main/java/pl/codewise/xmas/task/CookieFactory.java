package pl.codewise.xmas.task;

import pl.codewise.xmas.task.cookie.Cookie;
import pl.codewise.xmas.task.cookiestorage.CookieQueue;
import pl.codewise.xmas.task.cookiestorage.CookieStorage;
import pl.codewise.xmas.task.elf.Elf;
import pl.codewise.xmas.task.elf.MadeCookieShape;

import java.util.ArrayList;

public class CookieFactory {
    private final ArrayList<Elf> elfList = new ArrayList<>();
    private final CookieQueue cookieQueue;
    private DoughState doughState = DoughState.FRESH;

    public CookieFactory(CookieQueue cookieQueue) {
        this.cookieQueue = cookieQueue;
    }

    public static void main(String[] args) {
        CookieFactory cf = new CookieFactory(new CookieStorage(10));
        cf.addElf(new Elf(1, MadeCookieShape.RANDOM));
        cf.addElf(new Elf(2, MadeCookieShape.ONLY_CHRISTMAS_TREE));
        cf.addElf(new Elf(3, MadeCookieShape.ONLY_SLEDGE));
        cf.addElf(new Elf(4, MadeCookieShape.ONLY_SANTA_CLAUS_HAT));
        cf.addElf(new Elf(5, MadeCookieShape.ONLY_SLEDGE));
        cf.step();
        cf.step();
        cf.step();
        cf.step();
        cf.setDoughState(DoughState.CONTAMINATED);
        cf.step();
        cf.step();
        cf.step();
        cf.step();
        System.out.println(cf.getReport().getCookies());
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

    public Report getReport() {
        return cookieQueue.getReport();
    }

}
