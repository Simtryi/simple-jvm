package com.simple.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * Java 命令行
 */
public class Cmd {

    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "print version and exit", order = 2)
    boolean versionFlag = false;

    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    String classpath;

    @Parameter(names = "-Xjre", description = "path to jre", order = 4)
    String jre;

    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    /**
     * 解析命令行参数
     */
    static Cmd parse(String[] args) {
        Cmd cmd = new Cmd();
        JCommander jcommander = JCommander.newBuilder().addObject(cmd).build();

        jcommander.parse(args);
        cmd.ok = true;
        return cmd;
    }

    /**
     * 获取主类
     */
    String getMainClass() {
        if (mainClassAndArgs != null && !mainClassAndArgs.isEmpty()) {
            return mainClassAndArgs.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取参数
     */
    List<String> getArgs() {
        if (mainClassAndArgs != null && mainClassAndArgs.size() > 1) {
            return mainClassAndArgs.subList(1, mainClassAndArgs.size());
        } else {
            return null;
        }
    }

}
