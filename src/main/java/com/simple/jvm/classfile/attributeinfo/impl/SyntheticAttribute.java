package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class SyntheticAttribute implements AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        //  read nothing
    }

}
