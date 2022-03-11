package com.simple.jvm.classfile;

import com.simple.jvm.classfile.attributeinfo.AttributeInfo;
import com.simple.jvm.classfile.attributeinfo.impl.CodeAttribute;
import com.simple.jvm.classfile.attributeinfo.impl.ConstantValueAttribute;

/**
 * 字段和方法信息
 */
public class MemberInfo {

    private ConstantPool constantPool;      //  常量池
    private int accessFlags;                //  访问标志
    private int nameIdx;                    //  字段/方法名字的常量池索引
    private int descriptorIdx;              //  字段/方法描述符的常量池索引
    private AttributeInfo[] attributes;     //  属性表

    private MemberInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        this.accessFlags = reader.readU2();
        this.nameIdx = reader.readU2();
        this.descriptorIdx = reader.readU2();
        this.attributes = AttributeInfo.read(reader, constantPool);
    }

    /**
     * 读取字段表或方法表
     */
    static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int memberCount = reader.readU2();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            members[i] = new MemberInfo(reader, constantPool);
        }
        return members;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    /**
     * 从常量池查找字段或方法名
     */
    public String getName() {
        return constantPool.getUTF8(nameIdx);
    }

    /**
     * 从常量池查找字段或方法描述符
     */
    public String getDescriptor() {
        return constantPool.getUTF8(descriptorIdx);
    }

    /**
     * 从属性表查找Code属性
     */
    public CodeAttribute getCodeAttribute() {
        for (AttributeInfo attrInfo : attributes) {
            if (attrInfo instanceof CodeAttribute) {
                return (CodeAttribute) attrInfo;
            }
        }
        return null;
    }

    public ConstantValueAttribute ConstantValueAttribute() {
        for (AttributeInfo attrInfo : attributes) {
            if (attrInfo instanceof ConstantValueAttribute) {
                return (ConstantValueAttribute) attrInfo;
            }
        }
        return null;
    }

}
