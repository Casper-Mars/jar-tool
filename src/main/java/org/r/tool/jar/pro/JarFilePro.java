package org.r.tool.jar.pro;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author Casper
 * @DATE 2019/8/25 12:58
 **/
public class JarFilePro extends JarFile {
    /**
     * Creates a new <code>JarFile</code> to read from the specified
     * file <code>name</code>. The <code>JarFile</code> will be verified if
     * it is signed.
     *
     * @param name the name of the jar file to be opened for reading
     * @throws IOException       if an I/O error has occurred
     * @throws SecurityException if access to the file is denied
     *                           by the SecurityManager
     */
    public JarFilePro(String name) throws IOException {
        super(name);
    }

    /**
     * Returns an enumeration of the zip file entries.
     */
    @Override
    public Enumeration<JarEntry> entries() {
        Vector<JarEntry> result = new Vector<>();
        Enumeration<JarEntry> entries = super.entries();
        while (entries.hasMoreElements()) {
            JarEntryPro tmp = new JarEntryPro(entries.nextElement());
            tmp.setParent(this);
            result.add(tmp);
        }
        return result.elements();
    }


    /**
     * Returns the <code>JarEntry</code> for the given entry name or
     * <code>null</code> if not found.
     *
     * @param name the jar file entry name
     * @return the <code>JarEntry</code> for the given entry name or
     * <code>null</code> if not found.
     * @throws IllegalStateException may be thrown if the jar file has been closed
     * @see JarEntry
     */
    @Override
    public JarEntry getJarEntry(String name) {
        JarEntryPro jarEntry = new JarEntryPro(super.getJarEntry(name));
        jarEntry.setParent(this);
        return jarEntry;
    }
}
