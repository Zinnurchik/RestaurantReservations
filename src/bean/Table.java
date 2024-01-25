package bean;

public class Table extends Base {
    private Boolean isReserve = false;

    public Table() {
    }

    public Boolean getReserve() {
        return isReserve;
    }

    public void setReserve(Boolean reserve) {
        isReserve = reserve;
    }
}
