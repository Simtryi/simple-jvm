package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.FieldRef;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Field;
import com.simple.jvm.rtda.heap.methodarea.Method;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 给实例变量赋值，需要三个操作数，
 * 前两个操作数是常量池索引和变量值，
 * 第三个操作数是对象引用，从操作数栈中弹出
 */
public class PUT_FIELD extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        Class currentClazz = currentMethod.getClazz();
        RunTimeConstantPool runTimeConstantPool = currentClazz.getConstantPool();
        FieldRef fieldRef = (FieldRef) runTimeConstantPool.getConstants(this.idx);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            //  解析后的字段必须是实例字段，否则抛出IncompatibleClassChangeError
            throw new IncompatibleClassChangeError();
        }

        if (field.isFinal()) {
            //  如果是final字段，则是能在构造函数中初始化，否则抛出IllegalAccessError
            if (currentClazz != field.getClazz() || !"<init>".equals(currentMethod.getName())){
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();
        //  根据字段类型从操作数栈中弹出相应的变量值，然后弹出对象引用，
        //  如果引用是null，需要抛出NullPointerException，否则通过引用给实例变量赋值
        switch (descriptor.substring(0, 1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                int valInt = stack.popInt();
                Object refInt = stack.popRef();
                if (null == refInt) {
                    throw new NullPointerException();
                }
                refInt.getFields().setInt(slotId, valInt);
                break;
            case "F":
                float valFloat = stack.popFloat();
                Object refFloat = stack.popRef();
                if (null == refFloat) {
                    throw new NullPointerException();
                }
                refFloat.getFields().setFloat(slotId, valFloat);
                break;
            case "J":
                long valLong = stack.popLong();
                Object refLong = stack.popRef();
                if (null == refLong) {
                    throw new NullPointerException();
                }
                refLong.getFields().setLong(slotId, valLong);
                break;
            case "D":
                double valDouble = stack.popDouble();
                Object refDouble = stack.popRef();
                if (null == refDouble) {
                    throw new NullPointerException();
                }
                refDouble.getFields().setDouble(slotId, valDouble);
                break;
            case "L":
            case "[":
                Object val = stack.popRef();
                Object ref = stack.popRef();
                if (null == ref) {
                    throw new NullPointerException();
                }
                ref.getFields().setRef(slotId, val);
                break;
            default:
                break;
        }
    }

}
