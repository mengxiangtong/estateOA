package estate.entity.json;

/**
 * Created by kangbiao on 15-9-7.
 * datatable查询数据容器
 */
public class TableFilter
{
    private Integer length;
    private Integer start;
    private String searchValue;

    public Integer getLength()
    {
        return length;
    }

    public void setLength(Integer length)
    {
        this.length = length;
    }


    public Integer getStart()
    {
        return start;
    }

    public void setStart(Integer start)
    {
        this.start = start;
    }

    public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }
}