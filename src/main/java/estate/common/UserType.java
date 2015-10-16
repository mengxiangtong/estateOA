package estate.common;

import estate.exception.TypeErrorException;

/**
 * Created by kangbiao on 15-9-24.
 *
 */
public class UserType
{
    public static final int FAMILY=1;
    public static final int TENANT=2;
    public static final int OWNER=3;
    public static final int APPUSER=4;
    public static final int NOROLE=0;

    public static boolean checkType(int type) throws TypeErrorException
    {
        return true;
    }

}
