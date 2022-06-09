package ru.geekbrains.march.market.core.converters;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.api.Pageable;
import ru.geekbrains.march.market.api.Sort;

@Component
public class PageConverter {

    public PageDto entityToDto(Page page){
        return new PageDto(
                page.getTotalPages(),
                page.getTotalElements(),
                new Pageable(
                        page.getPageable().getOffset(),
                        page.getPageable().getPageNumber(),
                        page.getPageable().getPageSize(),
                        page.getPageable().isPaged(),
                        new Sort(page.getPageable().getSort().isEmpty(), page.getPageable().getSort().isSorted(), page.getPageable().getSort().isUnsorted()),
                        page.getPageable().isUnpaged()),
                page.getSize(),
                new Sort(page.getSort().isEmpty(), page.getSort().isSorted(), page.getSort().isUnsorted()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.isLast(),
                page.isFirst(),
                page.isEmpty(),
                page.getContent()
        );
    }
}
