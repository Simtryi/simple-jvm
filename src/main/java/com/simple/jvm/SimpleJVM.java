package com.simple.jvm;

import com.simple.jvm.classfile.ClassFile;
import com.simple.jvm.classfile.MemberInfo;
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
        Classpath classpath = new Classpath(cmd.jre, cmd.classpath);
        System.out.printf("classpath: %s class: %s args: %s\n", classpath, cmd.getMainClass(), cmd.getArgs());

        //  获取className
        String className = cmd.getMainClass().replace(".", "/");
        ClassFile classFile = loadClass(className, classpath);
        assert classFile != null;
        printClassInfo(classFile);
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

    private static void printClassInfo(ClassFile classFile) {
        System.out.printf("version: %d.%d\n", classFile.getMajorVersion(), classFile.getMinorVersion());
        System.out.printf("constant pool count: %d\n", classFile.getConstantPool().getSize());
        System.out.printf("access flags: 0x%x\n", classFile.getAccessFlags());
        System.out.printf("this class: %s\n", classFile.getClassName());
        System.out.printf("super class: %s\n", classFile.getSupperClassName());
        System.out.printf("interfaces: %s\n", Arrays.toString(classFile.getInterfaceNames()));
        System.out.printf("fields count: %d\n", classFile.getFields().length);
        for (MemberInfo memberInfo : classFile.getFields()) {
            System.out.printf("%s \t\t %s\n", memberInfo.getName(), memberInfo.getDescriptor());
        }

        System.out.printf("methods count: %d\n", classFile.getMethods().length);
        for (MemberInfo memberInfo : classFile.getMethods()) {
            System.out.printf("%s \t\t %s\n", memberInfo.getName(), memberInfo.getDescriptor());
        }
    }

}
