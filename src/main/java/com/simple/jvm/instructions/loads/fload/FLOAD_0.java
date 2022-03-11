package com.simple.jvm.instructions.loads.fload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class FLOAD_0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Float val = localVars.getFloat(0);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat(val);
    }

}
