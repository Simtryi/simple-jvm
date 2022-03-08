package com.simple.jvm.classfile.attributeinfo;

import com.simple.jvm.classfile.ClassReader;
import com.simple.jvm.classfile.ConstantPool;
import com.simple.jvm.classfile.attributeinfo.impl.*;

/**
 * 属性表
 * <p>
 *  按照用途，23种预定义属性可以分为三组：
 *  1. Java虚拟机所必需的，共有5种。
 *  2. Java类库所必需的，共有12种。
 *  3. 提供给工具使用，共有6种。
 *  第三组属性是可选的，也就是说可以不出现在class文件中。
 *  如果class文件中存在第三组属性，Java虚拟机实现或者Java类库也是可以利用它们的，比如使用LineNumberTable属性在异常堆栈中显示行号。
 * </p>
 */
public interface AttributeInfo {

    /**
     * 读取属性表
     */
    static AttributeInfo[] read(ClassReader reader, ConstantPool constantPool) {
        int attributesCount = reader.readU2();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    /**
     * 读取单个属性
     */
    static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        //  读取属性名索引
        int attrNameIdx = reader.readU2();
        //  从常量池中找到属性名
        String attrName = constantPool.getUTF8(attrNameIdx);

        //  读取属性长度
        int attrLen = reader.readU4ToInteger();
        //  创建具体属性实例
        AttributeInfo attrInfo = create(attrName, attrLen, constantPool);

        attrInfo.readInfo(reader);
        return attrInfo;
    }

    /**
     * 根据属性名创建具体属性
     */
    static AttributeInfo create(String attrName, int attrLen, ConstantPool constantPool) {
        switch (attrName) {
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "EnclosingMethod":
                return new EnclosingMethodAttribute(constantPool);
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            //case "MethodParameters":
            //case "RuntimeInvisibleAnnotations":
            //case "RuntimeInvisibleParameterAnnotations":
            //case "RuntimeInvisibleTypeAnnotations":
            //case "RuntimeVisibleAnnotations":
            //case "RuntimeVisibleParameterAnnotations":
            //case "RuntimeVisibleTypeAnnotations":
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            //case "SourceDebugExtension":
            //case "StackMapTable":
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
        }
    }

    /**
     * 读取属性信息
     */
    void readInfo(ClassReader reader);

}
