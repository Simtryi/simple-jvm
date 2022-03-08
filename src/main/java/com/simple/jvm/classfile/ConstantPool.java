package com.simple.jvm.classfile;

import com.simple.jvm.classfile.constantinfo.ConstantInfo;
import com.simple.jvm.classfile.constantinfo.impl.ConstantClassInfo;
import com.simple.jvm.classfile.constantinfo.impl.ConstantNameAndTypeInfo;
import com.simple.jvm.classfile.constantinfo.impl.ConstantUtf8Info;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量池
 */
public class ConstantPool {

    private ConstantInfo[] constantInfos;   //  常量信息表
    private final int size;                 //  常量池大小

    public ConstantPool(ClassReader reader) {
        size = reader.readU2();
        constantInfos = new ConstantInfo[size];

        //  有效的常量池索引是1~n-1，0是无效索引，表示不指向任何常量
        for (int i = 1; i < size; i++) {
            constantInfos[i] = ConstantInfo.read(reader, this);

            //  CONSTANT_Long_info和CONSTANT_Double_info各占两个位置
            switch (constantInfos[i].getTag()) {
                case ConstantInfo.CONSTANT_LONG:
                case ConstantInfo.CONSTANT_DOUBLE:
                    i++;
                    break;
            }
        }
    }

    /**
     * 从常量池查找字段或方法的名字和描述符
     */
    public Map<String, String> getNameAndType(int idx) {
        ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo) constantInfos[idx];
        Map<String, String> map = new HashMap<>();
        map.put("name", getUTF8(nameAndTypeInfo.nameIdx));
        map.put("_type", getUTF8(nameAndTypeInfo.descIdx));
        return map;
    }

    /**
     * 从常量池查找类名
     */
    public String getClassName(int idx) {
        ConstantClassInfo classInfo = (ConstantClassInfo) constantInfos[idx];
        return getUTF8(classInfo.nameIdx);
    }

    /**
     * 从常量池查找UTF-8字符串
     */
    public String getUTF8(int idx) {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) constantInfos[idx];
        return utf8Info == null ? "" : utf8Info.getStr();
    }

    public int getSize() {
        return size;
    }

}
