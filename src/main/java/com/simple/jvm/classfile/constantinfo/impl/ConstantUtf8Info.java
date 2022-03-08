package com.simple.jvm.classfile.constantinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.constantinfo.ConstantInfo;

public class ConstantUtf8Info implements ConstantInfo {

    private String str;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readU2();
        byte[] bytes = reader.readBytes(length);
        str = new String(bytes);
    }

    @Override
    public int getTag() {
        return CONSTANT_UTF8;
    }

    public String getStr() {
        return str;
    }

}
