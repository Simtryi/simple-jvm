package com.simple.jvm;

import com.simple.jvm.classpath.Classpath;

import java.util.Arrays;

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
        Classpath cp = new Classpath(cmd.jre, cmd.classpath);
        System.out.printf("classpath: %s class: %s args: %s\n", cp, cmd.getMainClass(), cmd.getArgs());

        //  获取className
        String className = cmd.getMainClass().replace(".", "/");
        try {
            byte[] classData = cp.readClass(className);
            System.out.println("classData: ");
            System.out.println(Arrays.toString(classData));
        } catch (Exception e) {
            System.out.println("Could not find or load main class " + cmd.getMainClass());
            e.printStackTrace();
        }
    }

}
