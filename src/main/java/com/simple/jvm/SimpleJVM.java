package com.simple.jvm;

import com.simple.jvm.classfile.ClassFile;
import com.simple.jvm.classfile.MemberInfo;
import com.simple.jvm.classpath.Classpath;

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
        System.out.printf("classpath: %s class: %s args: %s\n", classpath, cmd.getMainClass(), cmd.getArgs());

        //  获取className
        String className = cmd.getMainClass().replace(".", "/");
        ClassFile classFile = loadClass(className, classpath);
        MemberInfo mainMethod = getMainMethod(classFile);
        if (null == mainMethod) {
            System.out.println("Main method not found in class " + cmd.classpath);
            return;
        }
        new Interpreter(mainMethod);
    }

    /**
     * 搜索并解析class文件
     */
    private static ClassFile loadClass(String className, Classpath classpath) {
        try {
            byte[] classData = classpath.readClass(className);
            return new ClassFile(classData);
        } catch (Exception e) {
            System.out.println("Could not find or load main class " + className);
            return null;
        }
    }

    private static MemberInfo getMainMethod(ClassFile cf) {
        if (null == cf) return null;
        MemberInfo[] methods = cf.getMethods();
        for (MemberInfo m : methods) {
            if ("main".equals(m.getName()) && "([Ljava/lang/String;)V".equals(m.getDescriptor())) {
                return m;
            }
        }
        return null;
    }

}
