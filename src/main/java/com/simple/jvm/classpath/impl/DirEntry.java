package com.simple.jvm.classpath.impl;

import com.simple.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 目录形式的类路径
 */
public class DirEntry implements Entry {

    private Path absolutePath;

    public DirEntry(String path) {
        //  将path转换成绝对路径
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        //  将目录和class文件名拼成一个完整的路径
        Path fileName = absolutePath.resolve(className);
        //  读取class文件内容
        return Files.readAllBytes(fileName);
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }

}
