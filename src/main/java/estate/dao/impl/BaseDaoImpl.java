package estate.dao.impl;

import estate.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kangbiao on 15-9-20.
 *
 */
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Integer save(Object object)
    {
        getSession().save(object);
        Class c=object.getClass();
        Method[] methods=c.getMethods();
        for (Method method:methods)
        {
            if (method.getName().matches("^getId$"))
            {
                try
                {
                    return (Integer)method.invoke(object);
                }
                catch (IllegalAccessException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }



    @Override
    public Object get(Serializable id,Object object)
    {
        return getSession().get(object.getClass(),id);
    }

    @Override
    public Object get(Serializable id, Class cls)
    {
        return getSession().get(cls,id);
    }

    @Override
    public void delete(Object object)
    {

    }

    @Override
    public Integer count(String table)
    {
        Session session=getSession();
        String hql="select count(*) from "+table;
        return ((Long)session.createQuery(hql).uniqueResult()).intValue();
    }

}
