package com.simple.jvm.instructions.math.xor;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * int类型按位异或
 */
public class IXOR extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v1 = operandStack.popInt();
        int v2 = operandStack.popInt();
        int res = v1 ^ v2;
        operandStack.pushInt(res);
    }

}
