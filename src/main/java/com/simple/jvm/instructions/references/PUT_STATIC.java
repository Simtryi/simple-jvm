package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.FieldRef;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Field;
import com.simple.jvm.rtda.heap.methodarea.Method;
import com.simple.jvm.rtda.heap.methodarea.Slots;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 给类的某个静态变量赋值，需要两个操作数，
 * 第一个操作数是2字节索引，来自字节码，
 * 第二个操作数是要赋给静态变量的值，从操作数栈中弹出。
 */
public class PUT_STATIC extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        //  拿到当前方法、当前类和当前常量池
        Method currentMethod = frame.getMethod();
        Class currentClazz = currentMethod.getClazz();
        RunTimeConstantPool runTimeConstantPool = currentClazz.getConstantPool();

        //  从当前类的运行时常量池中找到一个字段符号引用
        FieldRef fieldRef = (FieldRef) runTimeConstantPool.getConstants(idx);

        //  解析符号引用可以知道要给类的哪个静态变量赋值
        Field field = fieldRef.resolvedField();
        Class clazz = field.getClazz();
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = clazz.staticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.substring(0, 1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                slots.setInt(slotId, stack.popInt());
                break;
            case "F":
                slots.setFloat(slotId, stack.popFloat());
                break;
            case "J":
                slots.setLong(slotId, stack.popLong());
                break;
            case "D":
                slots.setDouble(slotId, stack.popDouble());
                break;
            case "L":
            case "[":
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                break;
        }
    }

}
