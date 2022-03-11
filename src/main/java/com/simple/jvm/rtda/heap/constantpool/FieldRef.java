package com.simple.jvm.rtda.heap.constantpool;

import com.simple.jvm.classfile.constantinfo.impl.ConstantFieldrefInfo;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Field;

import java.util.Map;

/**
 * 字段符号引用
 */
public class FieldRef extends SymRef {

    public String name;         //  字段名
    public String descriptor;   //  字段描述符
    private Field field;        //  字段

    public static FieldRef create(RunTimeConstantPool runTimeConstantPool, ConstantFieldrefInfo refInfo) {
        FieldRef ref = new FieldRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public void copyMemberRefInfo(ConstantFieldrefInfo fieldrefInfo){
        className = fieldrefInfo.getClassName();
        Map<String, String> map = fieldrefInfo.getNameAndDescriptor();
        name = map.get("name");
        descriptor = map.get("_type");
    }

    /**
     * 解析字段符号引用
     */
    public Field resolvedField() {
        if (null == field) {
            try {
                this.resolveFieldRef();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return field;
    }

    private void resolveFieldRef() throws NoSuchFieldException {
        Class d = runTimeConstantPool.getClazz();
        Class c = resolvedClass();

        Field field = lookupField(c, name, descriptor);
        if (null == field){
            throw new NoSuchFieldException();
        }

        if (!field.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }

        this.field = field;
    }

    private Field lookupField(Class c, String name, String descriptor) {
        for (Field field : c.fields) {
            if (field.name.equals(name) && field.descriptor.equals(descriptor)) {
                return field;
            }
        }

        for (Class iface : c.interfaces) {
            Field field = lookupField(iface, name, descriptor);
            if (null != field) return field;
        }

        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }

        return null;
    }

    public String getName(){
        return this.name;
    }

    public String getDescriptor(){
        return this.descriptor;
    }

}
