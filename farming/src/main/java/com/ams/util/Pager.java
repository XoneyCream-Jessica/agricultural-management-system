package com.ams.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author Administrator
 * 公共分页类 for jqxGrid
 *
 */
public class Pager<T> extends Page<T> {

    private static final long serialVersionUID = 1L;


    private String sortdatafield;


    private String sortorder;

    public Pager() {
        super();
    }

    /**
     * @return the sortdatafield
     */
    public String getSortdatafield() {
        return sortdatafield;
    }

    /**
     * @param sortdatafield the sortdatafield to set
     */
    public void setSortdatafield(String sortdatafield) {
        this.sortdatafield = sortdatafield;
        handleSort();
    }

    /**
     * @return the sortorder
     */
    public String getSortorder() {
        return sortorder;
    }

    /**
     * @param sortorder the sortorder to set
     */
    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
        handleSort();
    }

    // 处理排序字段
    private void handleSort() {
        if (this.sortdatafield == null || "".equals(this.sortdatafield.trim())
                || this.sortorder == null || "".equals(this.sortorder.trim())) {
            return;
        }
        if ("asc".equalsIgnoreCase(this.sortorder)) {
            setAsc(this.sortdatafield);
        } else if ("desc".equalsIgnoreCase(this.sortorder)) {
            setDesc(this.sortdatafield);
        }
    }

    /**
     * 设置当前页码
     */
    public void setPagenum(long pagenum) {
        setCurrent(pagenum + 1);
    }

    public void setCurrentPage(long currentPage) {
        setCurrent(currentPage);
    }

    /**
     * 设置页大小
     */
    public void setPagesize(long pagesize) {
        setSize(pagesize);
    }

    public void setPageSize(long pageSize) {
        setSize(pageSize);
    }


    public long getOffset() {
        return (getCurrent() - 1) * getSize();
    }


    public long getLimit() {
        return getSize();
    }


    public String getOrderByClause() {
        if (sortdatafield != null && !"".equals(sortdatafield.trim())
                && sortorder != null && !"".equals(sortorder.trim())) {
            return sortdatafield + " " + sortorder;
        } else {
            return null;
        }
    }

    public long getCurrent() {
        return super.getCurrent();
    }


    public long getPages() {
        // TODO Auto-generated method stub
        return super.getPages();
    }

    /* (non-Javadoc)
     * @see com.baomidou.mybatisplus.extension.plugins.pagination.Page#getSize()
     */
    @Override
    public long getSize() {
        // TODO Auto-generated method stub
        return super.getSize();
    }

    /* (non-Javadoc)
     * @see com.baomidou.mybatisplus.extension.plugins.pagination.Page#getTotal()
     */
    @Override
    public long getTotal() {
        // TODO Auto-generated method stub
        return super.getTotal();
    }

    /**
     *
     * @Title: defaultSort
     * @Description:TODO(设置默认排序)
     * @author
     * @date
     * @param ascs 升序排序集
     * @param descs 降序排序集
     *
     */
    public void defaultSort(String ascs, String descs) {
        if (getOrderByClause() != null) return;
        // 升序排序集
        if (ascs != null && !"".equals(ascs.trim())) {
            setAsc(ascs.split(","));
        }
        // 降序排序集
        if (descs != null && !"".equals(descs.trim())) {
            setDesc(descs.split(","));
        }
    }

}
