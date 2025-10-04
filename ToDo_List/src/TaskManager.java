import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int numberTasks = 1;

    public void addTask(String name, int priority) {
        Task task = new Task(numberTasks++, name, priority);
        tasks.add(task);
        System.out.println("Задача добавлена!");
    }

    private Task findNumber(int number) {
        for (Task task : tasks) {
            if (task.getNumber() == number) {
                return task;
            }
        }
        return null;
    }

    public void deleteTask(int number) {
        Task delete = findNumber(number);
        if (delete != null) {
            tasks.remove(delete);
            System.out.println("Задача удалена!");
        } else {
            System.out.println("Задача с таким номером не найдена");
        }
    }

    public void editTask(int number, String name, int priority) {
        Task task = findNumber(number);
        if (task != null) {
            task.setName(name);
            task.setPriority(priority);
            System.out.println("Задача обновлена!");
        } else {
            System.out.println("Задача  не найдена");
        }
    }

    public void changeStatus(int number, Status status) {
        Task task = findNumber(number);
        if (task != null) {
            task.setStatus(status);
            System.out.println("Статус задачи обновлен!");
        } else {
            System.out.println("Задача  не найдена");
        }

    }

    public void showPriority() {
        tasks.stream()
                .sorted(Comparator.comparingInt(Task::getPriority).reversed())
                .forEach(System.out::println);
    }

    public void showDate() {
        tasks.stream()
                .sorted(Comparator.comparing(Task::getDateStart))
                .forEach(System.out::println);
    }

    public void filterStatus(Status status) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Задач со статусом " + status + " нет.");
        }
    }

    public void searchTask(String name) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Задачи с таким словом не найдены.");
        }
    }

    public void statistics() {
        long done = tasks.stream()
                .filter(s -> s.getStatus() == Status.DONE)
                .count();
        long inProgress = tasks.stream()
                .filter(s -> s.getStatus() == Status.IN_PROGRESS)
                .count();
        long notDone = tasks.stream()
                .filter(s -> s.getStatus() == Status.NOT_DONE)
                .count();

        double averageValue = 0.0;

        if (!tasks.isEmpty()) {
            int sumCount = 0;
            int count = 0;
            for (Task task : tasks) {
                sumCount += task.getPriority();
                count++;
            }
            averageValue = (double) sumCount / count;
        }
        System.out.println("\n Статистика задач: ");
        System.out.println(" Выполнено: " + done);
        System.out.println(" В процессе: " + inProgress);
        System.out.println("Не выполнено: " + notDone);
        System.out.println("Средний приоритет: " + averageValue);

        System.out.println("\n Группировка задач по статусу: ");

        System.out.println("Выполнена: ");
        for (Task task : tasks) {
            if (task.getStatus() == Status.DONE) {
                System.out.println(task);
            }
        }
        System.out.println("В процессе: ");
        for (Task task : tasks) {
            if (task.getStatus() == Status.IN_PROGRESS) {
                System.out.println(task);
            }
        }
        System.out.println("Не выполнена: ");
        for (Task task : tasks) {
            if (task.getStatus() == Status.NOT_DONE) {
                System.out.println(task);
            }
        }

    }
}
