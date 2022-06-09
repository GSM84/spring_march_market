package ru.geekbrains.march.market.api;

public class Sort {
    private boolean empty;
    private boolean sorted;
    private boolean unsorted;

    public Sort() {
    }

    public Sort(boolean empty, boolean sorted, boolean unsorted) {
        this.empty = empty;
        this.sorted = sorted;
        this.unsorted = unsorted;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public boolean isUnsorted() {
        return unsorted;
    }

    public void setUnsorted(boolean unsorted) {
        this.unsorted = unsorted;
    }
}
