package com.simple.jvm.instructions.math.sh;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * long类型左移
 */
public class LSHL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        long v1 = operandStack.popLong();
        int s = v2 & 0x3f;
        long res = v1 << s;
        operandStack.pushLong(res);
    }

}

