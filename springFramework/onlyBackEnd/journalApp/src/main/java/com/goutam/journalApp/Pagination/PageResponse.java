package com.goutam.journalApp.Pagination;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    public static <T> PageResponse<T> getPageResponse(Page<T> pagePost){
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setContent(pagePost.getContent());
        pageResponse.setPageNumber(pagePost.getNumber());
        pageResponse.setPageSize(pagePost.getSize());
        pageResponse.setTotalElements(pagePost.getTotalElements());
        pageResponse.setTotalPages(pagePost.getTotalPages());
        pageResponse.setLastPage(pagePost.isLast());
        return pageResponse;
    }
}
