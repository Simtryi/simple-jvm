package com.simple.jvm.classpath.impl;

import com.simple.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.*;

/**
 * zip/jar形式的类路径
 */
public class ZipEntry implements Entry {

    private Path absolutePath;

    public ZipEntry(String path) {
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        //  构造一个新的FileSystem以访问文件的内容作为文件系统
        try (FileSystem zipFs = FileSystems.newFileSystem(absolutePath, null)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }

}
