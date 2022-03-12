package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.ClassInitLogic;
import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.FieldRef;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Field;
import com.simple.jvm.rtda.heap.methodarea.Slots;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 取出类的某个静态变量值，需要一个操作数
 */
public class GET_STATIC extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool runTimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef ref = (FieldRef) runTimeConstantPool.getConstants(this.idx);
        Field field = ref.resolvedField();
        Class clazz = field.getClazz();

        if (!clazz.getInitStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.getThread(), clazz);
            return;
        }
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = clazz.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.substring(0, 1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                stack.pushInt(slots.getInt(slotId));
                break;
            case "F":
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case "J":
                stack.pushLong(slots.getLong(slotId));
                break;
            case "D":
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case "L":
            case "[":
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }

}
