package com.simple.jvm.instructions.constants.ldc;

import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 用于加载long和double常量
 */
public class LDC2_W extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        RunTimeConstantPool runTimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        Object c = runTimeConstantPool.getConstants(idx);
        
        if (c instanceof Long) {
            stack.pushLong((Long) c);
            return;
        }

        if (c instanceof Double) {
            stack.pushDouble((Double) c);
            return;
        }
        
        throw new ClassFormatError(c.toString());
    }

}
