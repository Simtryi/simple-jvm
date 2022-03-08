package com.simple.jvm.classpath;

import com.simple.jvm.classpath.impl.WildcardEntry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 搜索class文件
 */
public class Classpath {

    private Entry bootstrapClasspath;   //  启动类路径
    private Entry extensionClasspath;   //  扩展类路径
    private Entry userClasspath;        //  用户类路径

    /**
     * -Xjre选项解析启动类路径和扩展类路径，-classpath/-cp选项解析用户类路径
     */
    public Classpath(String jreOption, String cpOption) {
        parseBootstrapAndExtensionClasspath(jreOption);
        parseUserClasspath(cpOption);
    }

    /**
     * 解析启动类路径和扩展类路径
     */
    private void parseBootstrapAndExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);

        //  ../jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);

        //  ../jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);
    }

    /**
     * 获取jre目录
     */
    private static String getJreDir(String jreOption) {
        //  优先使用用户输入的-Xjre选项作为jre目录
        if (jreOption != null && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }
        //  如果没有输入该选项，则在当前目录下寻找jre目录
        if (Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }
        //  如果找不到，尝试使用JAVA_HOME环境变量
        String javaHome = System.getenv("JAVA_HOME");
        if (javaHome != null) {
            return Paths.get(javaHome, "jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder!");
    }

    /**
     * 解析用户类路径
     */
    private void parseUserClasspath(String cpOption) {
        //  如果用户没有提供-classpath/-cp选项，则使用当前目录作为用户类路径
        if (cpOption == null) {
            cpOption = ".";
        }
        userClasspath = Entry.create(cpOption);
    }

    /**
     * 依次从启动类路径、扩展类路径和用户类路径中搜索class文件
     */
    public byte[] readClass(String className) throws Exception {
        className = className + ".class";

        //  启动类路径
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception ignored) {
            //  ignored
        }

        //  扩展类路径
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {
            //  ignored
        }

        //  用户类路径
        return userClasspath.readClass(className);
    }

}
