package com.simple.jvm.rtda.heap.methodarea;

import com.simple.jvm.classfile.MemberInfo;
import com.simple.jvm.rtda.heap.constantpool.AccessFlags;

/**
 * 字段和方法的相关信息
 */
public class ClassMember {

    public int accessFlags;     //  访问标志
    public String name;         //  字段或方法名
    public String descriptor;   //  字段或方法描述符
    public Class clazz;         //  字段或方法所属的类

    /**
     * 从class文件中复制数据
     */
    public void copyMemberInfo(MemberInfo memberInfo) {
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    /**
     * 是否有权限访问该方法
     */
    public boolean isAccessibleTo(Class d) {
        if (isPublic()) {
            return true;
        }
        Class c = clazz;
        if (isProtected()) {
            return d == c || c.getPackageName().equals(d.getPackageName());
        }
        if (!isPrivate()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Class getClazz() {
        return clazz;
    }

}
