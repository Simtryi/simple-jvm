package com.simple.jvm.instructions.loads.iload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

public class ILOAD_1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(1);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(val);
    }

}

