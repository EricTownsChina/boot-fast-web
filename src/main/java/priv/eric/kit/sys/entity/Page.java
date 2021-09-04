package priv.eric.kit.sys.entity;

import lombok.Data;

import java.util.List;

/**
 * @author alex
 * @date Created in 2020/10/29 11:50
 * Description: 分页查询返回封装数据
 */
@Data
public class Page<T> {

    /**
     * 分页数据
     */
    private List<T> records;

    /**
     * 总条数
     */
    private int total;

    /**
     * 总页数
     */
    private int pageTotal;

    /**
     * 当前页
     */
    private int pageNum = 1;

    /**
     * 每页显示数量
     */
    private int pageSize = 10;

    /**
     * 当前页的起点
     */
    private int offset;

    /**
     * mysql offset
     * @return offset
     */
    public Integer getOffset() {
        return (this.pageNum - 1) * pageSize;
    }

    /**
     * 设置总记录数和页面总数
     *
     * @param total 总记录数
     */
    public void setTotal(Integer total) {
        this.total = total;
        this.setPageTotal(this.total % this.pageSize > 0 ? this.total / this.pageSize + 1 : this.total / this.pageSize);
    }

}
