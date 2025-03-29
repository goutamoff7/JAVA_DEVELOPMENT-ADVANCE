package com.goutam.journalApp.Pagination;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PagingResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    public static <T> PagingResponse<T> getPagingResponse(Page<T> pagePost){
        PagingResponse<T> pagingResponse = new PagingResponse<>();
        pagingResponse.setContent(pagePost.getContent());
        pagingResponse.setPageNumber(pagePost.getNumber());
        pagingResponse.setPageSize(pagePost.getSize());
        pagingResponse.setTotalElements(pagePost.getTotalElements());
        pagingResponse.setTotalPages(pagePost.getTotalPages());
        pagingResponse.setLastPage(pagePost.isLast());
        return pagingResponse;
    }
}
