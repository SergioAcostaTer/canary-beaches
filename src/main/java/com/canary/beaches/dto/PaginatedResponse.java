package com.canary.beaches.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
public class PaginatedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isSorted;

    public PaginatedResponse(Page<T> pageData) {
        this.content = pageData.getContent();
        this.page = pageData.getNumber();
        this.size = pageData.getSize();
        this.totalPages = pageData.getTotalPages();
        this.totalElements = pageData.getTotalElements();
        this.hasNext = pageData.hasNext();
        this.hasPrevious = pageData.hasPrevious();
        this.isSorted = pageData.getSort().isSorted();
    }

}
