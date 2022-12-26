package pl.codewise.xmas.task;

import pl.codewise.xmas.task.cookiestorage.CookieStorage;
import pl.codewise.xmas.task.elf.Elf;
import pl.codewise.xmas.task.elf.MadeCookieShape;

import java.util.Optional;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static int time = 0;

    public static void main(String[] args) {
        System.out.println(
                "=== Cookie Factory System ===\n" +
                        "Welcome to Elves Cookie Factory Management System !\n" +
                        "--- System configuration ---\n");

        int numOfElves = simpleParsePositiveInteger("Enter number of elves working in this factory: ",
                "Number of elves cannot be <= 0");
        int storageSize = simpleParsePositiveInteger("Enter the capacity of the cookie storage: ",
                "Storage size cannot be <= 0");

        System.out.println("--- Elves creation ---");
        CookieFactory cookieFactory = new CookieFactory(new CookieStorage(storageSize));
        System.out.println("Available cookie shapes:\n" +
                "t - christmas tree\n" +
                "h - santa claus hat\n" +
                "s - sledge\n" +
                "r - random shape (tree,hat or sledge) each time");
        while (cookieFactory.getElves().size() < numOfElves) {
            createElf(cookieFactory.getElves().size()).ifPresent(cookieFactory::addElf);
        }
        System.out.println();

        System.out.println("--- Simulation start ---");
        showAvailableCommands();
        boolean loop = true;
        String input;
        while (loop) {
            System.out.print("\n" + cookieFactory + "\nTime " + time + " >");
            input = scanner.nextLine();
            switch (input) {
                case "s" -> {
                    cookieFactory.step();
                    time++;
                }
                case "c" -> {
                    System.out.println("Cookies list (full)");
                    cookieFactory.getCookies().forEach(System.out::println);
                }
                case "r" -> {
                    System.out.println("Cookies report:");
                    cookieFactory.getReport().getCookies().forEach(System.out::println);
                }
                case "e" -> {
                    System.out.println("Elves list");
                    cookieFactory.getElves().forEach(System.out::println);
                }
                case "h" -> showAvailableCommands();
                case "q" -> loop = false;
                default -> {
                    System.out.println("Incorrect command");
                    showAvailableCommands();
                }
            }
        }
        System.out.println("Goodbye");
        scanner.close();
    }

    private static int simpleParsePositiveInteger(String promptString, String negativeNumberErrorString) {
        int result;
        while (true) {
            try {
                System.out.print(promptString);
                result = Integer.parseInt(scanner.nextLine());
                if (result <= 0) throw new IllegalArgumentException(negativeNumberErrorString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Input an integer greater than 0");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        return result;
    }

    private static Optional<Elf> createElf(int promptElfNumber) {
        String input;
        MadeCookieShape cookieShape;
        System.out.print("Creating Elf(" + promptElfNumber + "). Shape made by this elf: ");
        try {
            input = scanner.nextLine();
            cookieShape = switch (input) {
                case "t" -> MadeCookieShape.ONLY_CHRISTMAS_TREE;
                case "h" -> MadeCookieShape.ONLY_SANTA_CLAUS_HAT;
                case "s" -> MadeCookieShape.ONLY_SLEDGE;
                case "r" -> MadeCookieShape.RANDOM;
                default -> throw new IllegalArgumentException("Elf(" + promptElfNumber + ") - Incorrect cookie shape!");
            };
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(new Elf(cookieShape));
    }


    private static void showAvailableCommands() {
        System.out.println("Available commands:\n" +
                "s - (step) perform a single step of the simulation\n" +
                "c - (cookies) show full cookies list\n" +
                "r - (report) santa cookies list\n" +
                "e - (elves) show full elves list\n" +
                "h - (help) show available commands\n" +
                "q - (quit) quit the program");
    }

}
