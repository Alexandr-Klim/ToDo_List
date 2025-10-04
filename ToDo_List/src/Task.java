import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final int number;
    private String name;
    private int priority;
    private final LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private Status status;

    public Task(int number, String name, int priority) {
        this.number = number;
        this.name = name;
        this.priority = priority;
        this.dateStart = LocalDateTime.now();
        this.dateEnd = null;
        this.status = Status.NOT_DONE;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
        if (status == Status.DONE) {
            this.dateEnd = LocalDateTime.now();
        } else if (status == Status.NOT_DONE || status == Status.IN_PROGRESS) {
            this.dateEnd = null;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = dateStart.format(dtf);
        String end;
        if (dateEnd == null) {
            end ="Отсутствует";

        } else {
            end = dateEnd.format(dtF);
        }
        return "[" + number + "]" + ". " + name + " " +
                "| Приоритет: " + priority +
                " | Статус: " + status + " " +
                "| Дата создания: " + start +
                " | Время выполнения: " + end;
    }
}

