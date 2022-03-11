package com.simple.jvm;

import com.simple.jvm.classfile.ClassFile;
import com.simple.jvm.classpath.Classpath;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Field;
import com.simple.jvm.rtda.heap.methodarea.Slots;

import java.util.HashMap;
import java.util.Map;

/**
 * 类加载器
 */
public class ClassLoader {

    private Classpath classpath;
    private Map<String, Class> classMap;    //  已经加载的类数据，key是类的完全限定名，方法区的具体实现

    public ClassLoader(Classpath classpath) {
        this.classpath = classpath;
        classMap = new HashMap<>();
    }

    /**
     * 把类数据加载到方法区
     */
    public Class loadClass(String className) {
        Class clazz = classMap.get(className);

        //  查找classMap，看类是否已经被加载
        if (null != clazz) {
            //  如果是，直接返回类数据
            return clazz;
        }

        try {
            return loadNonArrayClass(className);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 加载普通类
     */
    private Class loadNonArrayClass(String className) throws Exception {
        //  找到class文件并把数据读取到内存
        byte[] data = classpath.readClass(className);
        if (null == data) {
            throw new ClassNotFoundException(className);
        }

        //  解析class文件，生成虚拟机可以使用的类数据，并放入方法区
        Class clazz = defineClass(data);

        //  链接
        link(clazz);
        return clazz;
    }

    /**
     * 解析class文件
     */
    private Class defineClass(byte[] data) throws Exception {
        Class clazz = parseClass(data);
        clazz.loader = this;
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        classMap.put(clazz.name, clazz);
        return clazz;
    }

    /**
     * 将class文件数据转换成class结构体
     */
    private Class parseClass(byte[] data) {
        ClassFile classFile = new ClassFile(data);
        return new Class(classFile);
    }

    /**
     * 解析超类
     */
    private void resolveSuperClass(Class clazz) throws Exception {
        if (!clazz.name.equals("java/lang/Object")) {
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName);
        }
    }

    /**
     * 解析接口
     */
    private void resolveInterfaces(Class clazz) throws Exception {
        int interfaceCount = clazz.interfaceNames.length;
        if (interfaceCount > 0) {
            clazz.interfaces = new Class[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames[i]);
            }
        }
    }

    /**
     * 链接类
     */
    private void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    /**
     * 验证阶段
     */
    private void verify(Class clazz) {
        //  todo
    }

    /**
     * 准备阶段
     */
    private void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    /**
     * 计算实例字段的个数，同时编号
     */
    private void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (Field field : clazz.fields) {
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    /**
     * 计算静态字段的个数，同时编号
     */
    private void calcStaticFieldSlotIds(Class clazz) {
        int slotId = 0;
        for (Field field : clazz.fields) {
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    /**
     * 给类变量分配空间
     */
    private void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (Field field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    /**
     * 给类变量赋予初始值
     */
    private void initStaticFinalVar(Class clazz, Field field) {
        Slots staticVars = clazz.staticVars;
        RunTimeConstantPool constantPool = clazz.runTimeConstantPool;
        int cpIdx = field.getConstValueIndex();
        int slotId = field.getSlotId();

        if (cpIdx > 0) {
            switch (field.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    java.lang.Object val = constantPool.getConstants(cpIdx);
                    staticVars.setInt(slotId, (Integer) val);
                case "J":
                    staticVars.setLong(slotId, (Long) constantPool.getConstants(cpIdx));
                case "F":
                    staticVars.setFloat(slotId, (Float) constantPool.getConstants(cpIdx));
                case "D":
                    staticVars.setDouble(slotId, (Double) constantPool.getConstants(cpIdx));
                case "Ljava/lang/String;":
                    System.out.println("todo");
            }
        }
    }

}
