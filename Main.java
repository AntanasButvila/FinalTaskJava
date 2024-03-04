import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Laptop> notebooks = new HashSet<>();

        notebooks.add(new Laptop("Dell", 8, 128, "Windows10", "black"));
        notebooks.add(new Laptop("Acer", 32, 512, "Windows11", "blue"));
        notebooks.add(new Laptop("HP", 16, 256, "Windows10", "black"));
        notebooks.add(new Laptop("Huawei", 8, 128, "Windows8", "grey"));
        notebooks.add(new Laptop("Xiaomi", 16, 512, "Windows10", "gold"));
        System.out.println();
        System.out.println(notebooks);

        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println(
                "1 - Объем ОЗУ (не менее)\n" + "2 - Объем ЖД (не менее)\n" + "3 - Операционная система\n" +
                        "4 - Цвет\n" + "Введите цифру, соответствующую необходимому критерию:");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ:");
                    int minRam = scanner.nextInt();
                    filters.put("ram", minRam);
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД:");
                    int minHdd = scanner.nextInt();
                    filters.put("storage", minHdd);
                    break;
                case 3:
                    System.out.println("Введите требуемую операционную систему:");
                    String requiredOs = scanner.nextLine();
                    filters.put("os", requiredOs);
                    break;
                case 4:
                    System.out.println("Введите требуемый цвет:");
                    String requiredColor = scanner.nextLine();
                    filters.put("color", requiredColor);
                    break;
                default:
                    throw new IllegalArgumentException("Неверный номер критерия. Введите число от 1 до 4.");
            }

            Set<Laptop> filteredLaptops = filterLaptops(notebooks, filters);

            if (filteredLaptops.isEmpty()) {
                System.out.println("Подходящие ноутбуки не найдены.");
            } else {
                System.out.println("Подходящий ноутбук(и):");
                for (Laptop notebook : filteredLaptops) {
                    System.out.println(notebook.getModel());
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Неверный ввод. Введите целое число.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Set<Laptop> filterLaptops(Set<Laptop> notebooks, Map<String, Object> filters) {
        Set<Laptop> filteredNotebooks = new HashSet<>();

        for (Laptop notebook : notebooks) {
            boolean matchesFilter = true;

            if (filters.containsKey("ram")) {
                int minRam = (int) filters.get("ram");
                if (notebook.getRam() < minRam) {
                    matchesFilter = false;
                }
            }

            if (filters.containsKey("storage")) {
                int minHdd = (int) filters.get("storage");
                if (notebook.getHdd() < minHdd) {
                    matchesFilter = false;
                }
            }

            if (filters.containsKey("os")) {
                String requiredOs = (String) filters.get("os");
                if (!notebook.getOs().equalsIgnoreCase(requiredOs)) {
                    matchesFilter = false;
                }
            }

            if (filters.containsKey("color")) {
                String requiredColor = (String) filters.get("color");
                if (!notebook.getColor().equalsIgnoreCase(requiredColor)) {
                    matchesFilter = false;
                }
            }

            if (matchesFilter) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }
}