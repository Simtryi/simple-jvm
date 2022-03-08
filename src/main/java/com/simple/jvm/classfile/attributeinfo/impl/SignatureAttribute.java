package com.simple.jvm.classfile.attributeinfo.impl;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.attributeinfo.AttributeInfo;

public class SignatureAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int signatureIdx;

    public SignatureAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        signatureIdx = reader.readU2();
    }

    public String getSignature(){
        return constantPool.getUTF8(signatureIdx);
    }

}
