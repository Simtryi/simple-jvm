package com.simple.jvm.instructions.loads.lload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

public class LLOAD_1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Long val = localVars.getLong(1);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong(val);
    }

}