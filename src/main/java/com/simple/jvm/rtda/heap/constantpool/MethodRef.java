package com.simple.jvm.rtda.heap.constantpool;

import com.simple.jvm.classfile.constantinfo.impl.ConstantMethodrefInfo;
import com.simple.jvm.rtda.heap.methodarea.Method;

import java.util.Map;

/**
 * 方法符号引用
 */
public class MethodRef extends SymRef {

    public String name;         //  方法名
    public String descriptor;   //  方法描述符
    private Method method;      //  方法

    public static MethodRef create(RunTimeConstantPool runTimeConstantPool, ConstantMethodrefInfo refInfo){
        MethodRef ref = new MethodRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public void copyMemberRefInfo(ConstantMethodrefInfo methodrefInfo){
        className = methodrefInfo.getClassName();
        Map<String, String> map = methodrefInfo.getNameAndDescriptor();
        name = map.get("name");
        descriptor = map.get("_type");
    }

    public String geName(){
        return name;
    }

    public String getDescriptor(){
        return descriptor;
    }

}
