package com.simple.jvm;

import com.simple.jvm.classfile.ClassFile;
import com.simple.jvm.classfile.MemberInfo;
import com.simple.jvm.classpath.Classpath;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Method;

/**
 * JVM的简单实现
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
        Classpath classpath = new Classpath(cmd.jre, cmd.classpath);
        ClassLoader classLoader = new ClassLoader(classpath);

        //  获取className
        String className = cmd.getMainClass().replace(".", "/");
        Class mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        if (null == mainMethod) {
            throw new RuntimeException("Main method not found in class " + cmd.getMainClass());
        }
        new Interpreter(mainMethod, cmd.verboseClassFlag);
    }

}
