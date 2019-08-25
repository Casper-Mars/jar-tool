package org.r.tool.jar.tool;

import org.r.tool.jar.enums.ProtocalEnum;

import java.net.URL;

/**
 * @Author Casper
 * @DATE 2019/8/25 20:22
 **/
public class JarTool {

    private static String[] checkSrc = {"", "/"};

    /**
     * 获取当前的jar包位置
     *
     * @return
     */
    public static String getCurrentJarPath() {
        URL resource = null;
        for (String s : checkSrc) {
            resource = JarTool.class.getClassLoader().getResource(s);
        }
        if (resource == null) {
            return "";
        }
        String path = resource.getPath();
        int i = path.indexOf("!/");
        if (i != -1) {
            path = path.substring(0, i);
        }
        if (path.startsWith(ProtocalEnum.FILE.getValue())) {
            String value = ProtocalEnum.FILE.getValue();
            path = path.substring(path.indexOf(value) + value.length());
        }
        return path;
    }


}
