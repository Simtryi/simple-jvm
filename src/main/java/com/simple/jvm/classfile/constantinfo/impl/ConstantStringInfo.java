package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantStringInfo implements ConstantInfo {

    private ConstantPool constantPool;  //  常量池
    private int strIdx;                 //  常量池索引，指向CONSTANT_Utf8_info常量

    public ConstantStringInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        strIdx = reader.readU2();
    }

    @Override
    public int getTag() {
        return this.CONSTANT_STRING;
    }

    /**
     * 按索引从常量池中查找字符串
     */
    public String getString() {
        return constantPool.getUTF8(strIdx);
    }

}
