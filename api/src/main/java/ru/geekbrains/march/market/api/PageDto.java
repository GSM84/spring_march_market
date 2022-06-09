package ru.geekbrains.march.market.api;

import java.util.List;

public class PageDto<T> {
    private Integer totalPages;
    private Long totalElements;
    private Pageable pageable;
    private Integer size;
    private Sort sort;
    private Integer numberOfElements;
    private Integer number;
    private boolean last;
    private boolean first;
    private boolean empty;
    private List<T> content;

    public PageDto() {
    }

    public PageDto(Integer totalPages,
                   Long totalElements,
                   Pageable pageable,
                   Integer size,
                   Sort sort,
                   Integer numberOfElements,
                   Integer number,
                   boolean last,
                   boolean first,
                   boolean empty,
                   List<T> content
    ) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageable = pageable;
        this.size = size;
        this.sort = sort;
        this.numberOfElements = numberOfElements;
        this.number = number;
        this.last = last;
        this.first = first;
        this.empty = empty;
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
