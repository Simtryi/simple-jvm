package com.simple.jvm;

/**
 * JVM 的简单实现
 */
public class SimpleJVM {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if (!cmd.ok || cmd.helpFlag) {
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if (cmd.versionFlag) {
            System.out.println("java version \"1.8.0\"");
            return;
        }
        start(cmd);
    }

    /**
     * 启动 JVM
     */
    private static void start(Cmd cmd) {
        System.out.printf("classpath:%s class:%s args:%s\n", cmd.classpath, cmd.getMainClass(), cmd.getAppArgs());
    }
}
