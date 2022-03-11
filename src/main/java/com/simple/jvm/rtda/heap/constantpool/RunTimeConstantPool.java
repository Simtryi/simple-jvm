package com.simple.jvm.rtda.heap.constantpool;

import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;
import com.simple.jvm.classfile.constantinfo.impl.*;
import com.simple.jvm.rtda.heap.methodarea.Class;

/**
 * 运行时常量池
 */
public class RunTimeConstantPool {

    private Class clazz;
    private Object[] constants;

    /**
     * 将class文件中的常量池转换成运行时常量池
     */
    public RunTimeConstantPool(Class clazz, ConstantPool constantPool) {
        ConstantInfo[] constantInfos = constantPool.getConstantInfos();
        int cpCount = constantInfos.length;
        
        this.clazz = clazz;
        constants = new Object[cpCount];
        
        for (int i = 1; i < cpCount; i++) {
            ConstantInfo constantInfo = constantInfos[i];

            switch (constantInfo.getTag()) {
                case ConstantInfo.CONSTANT_INTEGER:
                    ConstantIntegerInfo integerInfo = (ConstantIntegerInfo) constantInfo;
                    constants[i] = integerInfo.getVal();
                    break;

                case ConstantInfo.CONSTANT_FLOAT:
                    ConstantFloatInfo floatInfo = (ConstantFloatInfo) constantInfo;
                    constants[i] = floatInfo.getVal();
                    break;

                case ConstantInfo.CONSTANT_LONG:
                    ConstantLongInfo longInfo = (ConstantLongInfo) constantInfo;
                    constants[i] = longInfo.getVal();
                    i++;
                    break;

                case ConstantInfo.CONSTANT_DOUBLE:
                    ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) constantInfo;
                    constants[i] = doubleInfo.getVal();
                    i++;
                    break;

                case ConstantInfo.CONSTANT_STRING:
                    ConstantStringInfo stringInfo = (ConstantStringInfo) constantInfo;
                    constants[i] = stringInfo.getString();
                    break;

                case ConstantInfo.CONSTANT_CLASS:
                    ConstantClassInfo classInfo = (ConstantClassInfo) constantInfo;
                    constants[i] = ClassRef.create(this, classInfo);
                    break;

                case ConstantInfo.CONSTANT_FIELDREF:
                    constants[i] = FieldRef.create(this, (ConstantFieldrefInfo) constantInfo);
                    break;

                case ConstantInfo.CONSTANT_METHODREF:
                    constants[i] = MethodRef.create(this, (ConstantMethodrefInfo) constantInfo);
                    break;

                case ConstantInfo.CONSTANT_INTERFACEMETHODREF:
                    constants[i] = InterfaceMethodRef.create(this, (ConstantInterfaceMethodrefInfo) constantInfo);
                    break;

                default:
            }
        }
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 根据索引返回常量
     */
    public Object getConstants(int idx) {
        return constants[idx];
    }

}
