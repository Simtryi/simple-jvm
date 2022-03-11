package com.simple.jvm.instructions.math.xor;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * long类型按位异或
 */
public class LXOR extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v1 = operandStack.popLong();
        long v2 = operandStack.popLong();
        long res = v1 ^ v2;
        operandStack.pushLong(res);
    }

}

