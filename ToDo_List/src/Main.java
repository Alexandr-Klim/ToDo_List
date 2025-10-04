import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в приложение \"Управление списком задач\"!");
        while (true) {
            printMenu();
            int action = readInt("Выберите действие: ");

            switch (action) {
                case 1:
                    addTask();
                    break;
                case 2:
                    deleteTask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    showTask();
                    break;
                case 5:
                    filterTasks();
                    break;
                case 6:
                    searchTasks();
                    break;
                case 7:
                    changeStatus();
                    break;
                case 8:
                    manager.statistics();
                    break;
                case 9:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова. ");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n" +
                "\n1. Добавить задачу" +
                "\n2. Удалить задачу" +
                "\n3. Отредактировать задачу" +
                "\n4. Показать все задачи" +
                "\n5. Фильтровать все задачи по статусу" +
                "\n6. Найти задачу по ключевому слову" +
                "\n7. Изменить статус задачи" +
                "\n8. Показать статистику" +
                "\n9. Выход" +
                "\n");
    }

    private static int readInt(String value) {
        while (true) {
            try {
                System.out.println(value);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода! введите целое число.");
            }
        }
    }

    private static void addTask() {
        System.out.println("Введите название задачи: ");
        String name = scanner.nextLine();
        int priority = readInt("Введите приоритет (число): ");
        manager.addTask(name, priority);
    }

    private static void deleteTask() {
        int number = readInt("Введите номер задачи для удаления: ");
        manager.deleteTask(number);
    }

    private static void editTask() {
        int number = readInt("Введите номер задачи для редактирования: ");
        System.out.println("введите новое название: ");
        String name = scanner.nextLine();
        int priority = readInt("Введите новый приоритет: ");
        manager.editTask(number, name, priority);
    }

    private static void showTask() {
        System.out.println("1. Сортировка по приоритету: ");
        System.out.println("2. Сортировка по дате: ");
        int sort = readInt("Ваш выбор: ");
        if (sort == 1) {
            manager.showPriority();
        } else {
            manager.showDate();
        }
    }

    private static void filterTasks() {
        System.out.println("Выберите статус 1-" + Status.DONE + ", 2-" + Status.IN_PROGRESS + ", 3-" + Status.NOT_DONE);
        int count = readInt("Ваш выбор: ");
        Status status = Status.NOT_DONE;
        if (count == 1) {
            status = Status.DONE;
        } else if (count == 2) {
            status = Status.IN_PROGRESS;
        }
        manager.filterStatus(status);
    }

    private static void searchTasks() {
        System.out.println("Введите слово для поиска: ");
        String name = scanner.nextLine();
        manager.searchTask(name);
    }

    private static void changeStatus() {
        int number = readInt("Введите номер задачи: ");
        System.out.println("Выберите новый статус: 1- выполнена, 2- В процессе, 3- Не выполнена");
        int count = readInt("Ваш выбор: ");
        Status status = Status.NOT_DONE;
        if (count == 1) {
            status = Status.DONE;
        } else if (count == 2) {
            status = Status.IN_PROGRESS;
        }
        manager.changeStatus(number, status);
    }
}

