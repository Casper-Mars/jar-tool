package org.r.tool.jar.util;

import java.util.Collection;

/**
 * @Author Casper
 * @DATE 2019/8/24 22:17
 **/
public class CollectionUtils {


    public static boolean isEmpty(Collection target) {
        return target == null || target.size() == 0;
    }


}
