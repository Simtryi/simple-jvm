package com.simple.jvm.rtda.heap.constantpool;

import com.simple.jvm.classfile.constantinfo.impl.ConstantFieldrefInfo;
import com.simple.jvm.classfile.constantinfo.impl.ConstantInterfaceMethodrefInfo;
import com.simple.jvm.rtda.heap.methodarea.Method;

import java.util.Map;

/**
 * 接口方法符号引用
 */
public class InterfaceMethodRef extends SymRef {

    public String name;         //  接口方法名
    public String descriptor;   //  接口方法描述符
    private Method method;      //  接口方法

    public static InterfaceMethodRef create(RunTimeConstantPool runTimeConstantPool, ConstantInterfaceMethodrefInfo refInfo) {
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.runTimeConstantPool = runTimeConstantPool;
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public void copyMemberRefInfo(ConstantInterfaceMethodrefInfo interfaceMethodrefInfo){
        className = interfaceMethodrefInfo.getClassName();
        Map<String, String> map = interfaceMethodrefInfo.getNameAndDescriptor();
        name = map.get("name");
        descriptor = map.get("_type");
    }

    public String getName(){
        return this.name;
    }

    public String getDescriptor(){
        return this.descriptor;
    }

}
