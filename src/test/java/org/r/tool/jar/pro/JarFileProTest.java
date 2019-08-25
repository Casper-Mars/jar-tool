package org.r.tool.jar.pro;

import org.junit.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;

import static org.junit.Assert.*;

/**
 * @Author Casper
 * @DATE 2019/8/25 19:54
 **/
public class JarFileProTest {


    @Test
    public void test() {
        String jarPath = "D:\\project\\api-tool\\idea-plugin-api-generator\\src\\main\\resources\\container.jar";
        String folder = "BOOT-INF/classes/interface/xml/";
        try {
            JarFilePro jarFile = new JarFilePro(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                System.out.println(entries.nextElement().getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}