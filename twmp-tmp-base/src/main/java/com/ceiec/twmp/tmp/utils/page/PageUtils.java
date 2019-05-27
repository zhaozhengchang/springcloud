package com.ceiec.twmp.tmp.utils.page;


import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: wenliang <br/>
 * Description:
 **/
public class PageUtils {

    /** first page number */
    private static final int FIRST_PAGE_INDEX = 1;

    /**
     * generate page message
     *
     * @param totalCount all items count
     * @param pageIndex current page index
     * @param size per page items count
     * @return page message
     */
    public static <T> PagedItemsVO<T> createPageMsg(int totalCount, int pageIndex, int size, List<T> items) {

        PagedItemsVO<T> pagedItemsVO = new PagedItemsVO<>();
        int pageCount = (int) Math.ceil((double) totalCount / size);
        pagedItemsVO.setPageCount(pageCount);
        pagedItemsVO.setPageIndex(pageIndex);
        pagedItemsVO.setPageSize(size);
        pagedItemsVO.setTotal(totalCount);
        pagedItemsVO.setHasPre(pageIndex == FIRST_PAGE_INDEX ? 0 : 1);
        pagedItemsVO.setHasNext(pageIndex >= pageCount ? 0 : 1);
        pagedItemsVO.setItems(items);
        return pagedItemsVO;
    }
}
