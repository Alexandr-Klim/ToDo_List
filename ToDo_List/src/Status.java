public enum Status {
    NOT_DONE("Не выполнена"),
    IN_PROGRESS("В процессе"),
    DONE("Выполнена");


    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
