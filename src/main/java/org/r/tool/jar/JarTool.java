package org.r.tool.jar;

import org.r.tool.jar.util.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author Casper
 * @DATE 2019/8/24 9:47
 **/
public class JarTool {

    private static String PROTOCOL_FILE = "file:";


    /**
     * 列出jar包中的文件夹下的所有文件
     *
     * @param folder       文件夹相对于jar包中的位置
     * @param jarEntityURL jar包中的某个条目的url
     * @throws IOException
     */
    public static List<JarEntry> listFolder(String folder, URL jarEntityURL) throws IOException {
        String jarPath = getJarPathFromJarEntityURL(jarEntityURL);
        return listFolder(folder, jarPath);
    }

    /**
     * 列出jar包中的文件夹下的所有文件
     *
     * @param folder  文件夹名称
     * @param jarPath jar包位置
     * @return jar包里指定的文件夹下的所有文件元素
     * @throws IOException 有可能找不到jar包
     */
    public static List<JarEntry> listFolder(String folder, String jarPath) throws IOException {
        JarFile jarFile = getJarFile(jarPath);
        List<JarEntry> result = new ArrayList<>();

        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (jarEntry.isDirectory()) {
                continue;
            }
            String filename = jarEntry.getName();
            if (filename.contains(folder)) {
                result.add(jarEntry);
            }
        }
        jarFile.close();
        return result;
    }

    /**
     * 列出指定jar包中指定的文件夹下的所有文件名称
     *
     * @param folder  文件夹名称
     * @param jarPath jar包位置
     * @return
     * @throws IOException
     */
    public static List<String> listFolderFilename(String folder, String jarPath) throws IOException {
        List<JarEntry> entries = listFolder(folder, jarPath);
        return listFolderFilename(entries, folder);
    }

    /**
     * 在指定的jar包元素中找出指定文件夹下的所有文件名
     *
     * @param entries jar包元素集合
     * @param folder  文件夹名称
     * @return
     */
    public static List<String> listFolderFilename(List<JarEntry> entries, String folder) {
        List<String> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entries)) {
            entries.forEach(t -> {
                String name = t.getName();
                int i = name.indexOf(folder);
                if (i != -1) {
                    result.add(name.substring(name.indexOf(folder) + folder.length()));
                }
            });
        }
        return result;
    }


    public static JarFile getJarFile(String jarPath) throws IOException {
        if (jarPath.startsWith(PROTOCOL_FILE)) {
            jarPath = jarPath.substring(jarPath.indexOf(PROTOCOL_FILE) + PROTOCOL_FILE.length());
        }
        return new JarFile(jarPath);
    }

    public static String getJarPathFromJarEntityURL(URL jarEntity) {
        String path = jarEntity.getPath();
//        System.out.println("the jar entity path is " + path);
//        System.out.println("the jar entity path(toString) is " + jarEntity.toString());
        int i = path.indexOf("!/");
        String result = path;
        if (i != -1) {
            result = path.substring(0, path.indexOf("!/"));
        }
//        System.out.println("the result is " + result);
        return result;
    }



}
