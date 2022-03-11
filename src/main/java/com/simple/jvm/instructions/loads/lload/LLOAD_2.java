package com.simple.jvm.instructions.loads.lload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class LLOAD_2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Long val = localVars.getLong(2);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong(val);
    }

}
