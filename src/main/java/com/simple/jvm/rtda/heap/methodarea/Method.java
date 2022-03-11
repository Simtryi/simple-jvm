package com.simple.jvm.rtda.heap.methodarea;

import com.simple.jvm.classfile.MemberInfo;
import com.simple.jvm.classfile.attributeinfo.impl.CodeAttribute;
import com.simple.jvm.rtda.heap.constantpool.AccessFlags;

/**
 * 方法信息
 */
public class Method extends ClassMember {

    public int maxStack;    //  操作数栈大小
    public int maxLocals;   //  局部变量表大小
    public byte[] code;     //  方法字节码

    /**
     * 根据class文件中的方法信息创建方法表
     */
    public Method[] create(Class clazz, MemberInfo[] cfMethods) {
        Method[] methods = new Method[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyMemberInfo(cfMethods[i]);
            methods[i].copyAttributes(cfMethods[i]);
        }
        return methods;
    }

    /**
     * 从method_info结构中提取信息
     */
    public void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute codeAttr = cfMethod.getCodeAttribute();
        if (null != codeAttr) {
            maxStack = codeAttr.getMaxStack();
            maxLocals = codeAttr.getMaxLocals();
            code = codeAttr.getData();
        }
    }

    public boolean isSynchronized() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isBridge() {
        return 0 != (accessFlags & AccessFlags.ACC_BRIDGE);
    }

    public boolean isVarargs() {
        return 0 != (accessFlags & AccessFlags.ACC_VARARGS);
    }

    public boolean isNative() {
        return 0 != (accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isStrict() {
        return 0 != (accessFlags & AccessFlags.ACC_STRICT);
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

}
