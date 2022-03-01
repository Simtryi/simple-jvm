package com.simple.jvm.classpath.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * 通配符形式的类路径
 */
public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String path) {
        super(toPathList(path));
    }

    private static String toPathList(String wildcardPath) {
        //  移除路径末尾的"*"
        String baseDir = wildcardPath.replace("*", "");
        try {
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))  //  通配符类路径不能递归匹配子目录下的JAR文件
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }

}
