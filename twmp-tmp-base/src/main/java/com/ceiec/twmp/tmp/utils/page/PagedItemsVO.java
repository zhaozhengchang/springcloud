package com.ceiec.twmp.tmp.utils.page;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: wenliang <br/>
 * Description: store items with page information
 **/
public class PagedItemsVO<T> implements Serializable{

    /** serialize number */
    private static final long serialVersionUID = -4235716919149137121L;

    /** total page count */
    private int pageCount;

    /** current page number */
    private int pageIndex;

    /** items count per page */
    private int pageSize;

    /** items count */
    private long total;

    /** if has previous page */
    private int hasPre;

    /** 是否有下一页 */
    private int hasNext;

    /** 当前页面记录集合 */
    private List<T> items;

    public PagedItemsVO() {
    }

    public PagedItemsVO(List<T> list){
        PageInfo page = new PageInfo(list);
        pageIndex = page.getPageNum();
        pageSize = page.getPageSize();
        total = page.getTotal();
        pageCount = page.getPages();
        items = list;
    }

    public PagedItemsVO(long total ,  List<T> items) {
        this.total = total;
        this.items = items;
    }
    public PagedItemsVO(int pageCount, int pageIndex, int pageSize, long itemCount, int hasPre, int hasNext, List<T> items) {
        this.pageCount = pageCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = itemCount;
        this.hasPre = hasPre;
        this.hasNext = hasNext;
        this.items = items;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getHasPre() {
        return hasPre;
    }

    public void setHasPre(int hasPre) {
        this.hasPre = hasPre;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
