package com.simple.jvm.instructions.loads.dload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class DLOAD_2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        double val = localVars.getDouble(2);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble(val);
    }

}

