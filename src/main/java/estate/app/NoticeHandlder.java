package estate.app;

import estate.common.HtmlSplit;
import estate.dao.BaseDao;
import estate.entity.database.NoticeEntity;
import estate.entity.json.BasicJson;
import estate.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by kangbiao on 15-9-5.
 * 提供公告推送的API
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeHandlder
{
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private BaseDao baseDao;

    /**
     * 获取指定数量的公告,按时间排序
     * @param number 数量
     * @return
     */
    @RequestMapping(value = "/getSome/{number}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public BasicJson getSome(@PathVariable Integer number)
    {
        BasicJson basicJson=new BasicJson(false);
        ArrayList<NoticeEntity> noticeEntities=noticeService.getNewestNotice(number);
        if (noticeEntities==null)
        {
            basicJson.getErrorMsg().setCode("100123");
            basicJson.getErrorMsg().setDescription("未获取到相应公告");
            return basicJson;
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(noticeEntities);
        return basicJson;
    }

    /**
     * 根据id获取某个公告的详细信息
     * @param noticeID
     * @return
     */
    @RequestMapping(value = "/get/{noticeID}")
    public BasicJson get(@PathVariable Integer noticeID)
    {
        BasicJson basicJson=new BasicJson(false);
        NoticeEntity noticeEntity= (NoticeEntity) baseDao.get(noticeID,NoticeEntity.class);
        if (noticeEntity==null)
        {
            basicJson.getErrorMsg().setCode("1000010");
            basicJson.getErrorMsg().setDescription("该条公告不存在");
            return basicJson;
        }
        basicJson.setStatus(true);
        basicJson.setJsonString(noticeEntity);
        return basicJson;
    }

    @RequestMapping(value = "/getContent/{noticeID}",produces = "text/html;charset=UTF-8")
    public String getCOntent(@PathVariable Integer noticeID)
    {
        NoticeEntity noticeEntity= (NoticeEntity) baseDao.get(noticeID,NoticeEntity.class);
        if (noticeEntity==null)
        {
            return null;
        }
        return HtmlSplit.header+noticeEntity.getContent()+HtmlSplit.footer;
    }
}
