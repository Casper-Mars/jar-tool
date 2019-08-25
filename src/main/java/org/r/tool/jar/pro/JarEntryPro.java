package org.r.tool.jar.pro;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * @Author Casper
 * @DATE 2019/8/25 13:02
 **/
public class JarEntryPro extends JarEntry {


    private JarFilePro parent;


    /**
     * Creates a new <code>JarEntry</code> for the specified JAR file
     * entry name.
     *
     * @param name the JAR file entry name
     * @throws NullPointerException     if the entry name is <code>null</code>
     * @throws IllegalArgumentException if the entry name is longer than
     *                                  0xFFFF bytes.
     */
    public JarEntryPro(String name) {
        super(name);
    }


    /**
     * Creates a new <code>JarEntry</code> with fields taken from the
     * specified <code>JarEntry</code> object.
     *
     * @param je the <code>JarEntry</code> to copy
     */
    public JarEntryPro(JarEntry je) {
        super(je);
    }


    public JarFilePro getParent() {
        return parent;
    }

    public void setParent(JarFilePro parent) {
        this.parent = parent;
    }


    /**
     * 列出文件夹下所有的文件，如果当前不是文件夹则返回空
     *
     * @param fileOnly 指示是否只列出文件
     * @return
     */
    public List<JarEntryPro> list(boolean fileOnly) {
        List<JarEntryPro> result = new ArrayList<>();
        if (!isDirectory()) {
            return result;
        }
        String prefix = getName();
        Enumeration<JarEntry> entries = parent.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String filename = jarEntry.getName();
            if (fileOnly && jarEntry.isDirectory()) {
                continue;
            }
            /*根据前缀判断实体是否在此文件夹下*/
            if (filename.startsWith(prefix)) {
                result.add((JarEntryPro) jarEntry);
            }
        }
        return result;
    }


}
