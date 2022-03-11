package com.simple.jvm.instructions.loads.dload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

public class DLOAD_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        double val = localVars.getDouble(3);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushRef(val);
    }

}

