package com.simple.jvm.classpath;

import com.simple.jvm.classpath.impl.CompositeEntry;
import com.simple.jvm.classpath.impl.DirEntry;
import com.simple.jvm.classpath.impl.WildcardEntry;
import com.simple.jvm.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

/**
 * 类路径接口
 */
public interface Entry {

    /**
     * 寻找并加载class文件
     * @param className class文件的相对路径，路径之间用斜线分割，文件名有.class后缀
     */
    byte[] readClass(String className) throws IOException;

    /**
     * 根据参数创建不同类型的Entry实例
     */
    static Entry create(String path) {
        //  File.pathSeparator: 路径分隔符
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
