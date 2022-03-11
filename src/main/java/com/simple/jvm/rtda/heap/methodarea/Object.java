package com.simple.jvm.rtda.heap.methodarea;

public class Object {

    Class clazz;
    Slots fields;   //  实例变量

    public Object(Class clazz){
        this.clazz = clazz;
        fields = new Slots(clazz.instanceSlotCount);
    }

    public Class getClazz(){
        return clazz;
    }

    public Slots getFields(){
        return fields;
    }

    public boolean isInstanceOf(Class clazz){
        return clazz.isAssignableFrom(clazz);
    }

}
