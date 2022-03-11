package com.simple.jvm.rtda.heap.methodarea;

import com.simple.jvm.classfile.MemberInfo;
import com.simple.jvm.classfile.attributeinfo.impl.ConstantValueAttribute;

/**
 * 字段信息
 */
public class Field extends ClassMember {

    public int constValueIndex;
    public int slotId;

    /**
     * 根据class文件的字段信息创建字段表
     */
    public Field[] create(Class clazz, MemberInfo[] cfFields) {
        Field[] fields = new Field[cfFields.length];
        for (int i = 0; i < cfFields.length; i++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;
            fields[i].copyMemberInfo(cfFields[i]);
            fields[i].copyAttributes(cfFields[i]);
        }
        return fields;
    }

    public void copyAttributes(MemberInfo cfField) {
        ConstantValueAttribute valAttr = cfField.ConstantValueAttribute();
        if (null != valAttr) {
            constValueIndex = valAttr.getConstantValueIdx();
        }
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public int getSlotId() {
        return slotId;
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

}
