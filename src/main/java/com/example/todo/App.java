package com.example.todo;

import java.util.List;
import java.util.Scanner;

/**
 * Proste CLI dla TodoManagera.
 * Komendy:
 *   add <opis>    - dodaje nowe zadanie
 *   list          - wypisuje wszystkie zadania
 *   done <id>     - oznacza zadanie jako wykonane
 *   remove <id>   - usuwa zadanie
 *   help          - pokazuje pomoc
 *   exit / quit   - kończy program
 */
public class App {

    public static void main(String[] args) {
        TodoManager manager = new TodoManager();

        try (Scanner sc = new Scanner(System.in)) {
            printHelp();

            while (true) {
                System.out.print("> ");
                if (!sc.hasNextLine()) {
                    // koniec wejścia
                    break;
                }
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+", 2);
                String cmd = parts[0].toLowerCase();

                switch (cmd) {
                    case "add" -> {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            System.out.println("Użycie: add <opis>");
                        } else {
                            TodoItem it = manager.add(parts[1].trim());
                            System.out.println("Dodano: " + it);
                        }
                    }

                    case "list" -> {
                        List<TodoItem> list = manager.listAll();
                        if (list.isEmpty()) {
                            System.out.println("(brak zadań)");
                        } else {
                            list.forEach(System.out::println);
                        }
                    }

                    case "done" -> {
                        if (parts.length < 2) {
                            System.out.println("Użycie: done <id>");
                        } else {
                            try {
                                int id = Integer.parseInt(parts[1].trim());
                                if (manager.markDone(id)) {
                                    System.out.println("Oznaczono jako wykonane: " + id);
                                } else {
                                    System.out.println("Brak zadania o id " + id);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Id musi być liczbą całkowitą.");
                            }
                        }
                    }

                    case "remove" -> {
                        if (parts.length < 2) {
                            System.out.println("Użycie: remove <id>");
                        } else {
                            try {
                                int id = Integer.parseInt(parts[1].trim());
                                if (manager.remove(id)) {
                                    System.out.println("Usunięto: " + id);
                                } else {
                                    System.out.println("Brak zadania o id " + id);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Id musi być liczbą całkowitą.");
                            }
                        }
                    }

                    case "help" -> printHelp();

                    case "exit", "quit" -> {
                        System.out.println("Do widzenia!");
                        return;
                    }

                    default -> System.out.println("Nieznana komenda. Wpisz 'help'.");
                }
            }
        } catch (Exception ex) {
            System.err.println("Błąd: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void printHelp() {
        System.out.println("Simple TODO CLI - komendy:");
        System.out.println("  add <opis>    - dodaj nowe zadanie");
        System.out.println("  list          - pokaż wszystkie zadania");
        System.out.println("  done <id>     - oznacz zadanie jako wykonane");
        System.out.println("  remove <id>   - usuń zadanie");
        System.out.println("  help          - pokaż tę pomoc");
        System.out.println("  exit / quit   - zakończ program");
    }
}