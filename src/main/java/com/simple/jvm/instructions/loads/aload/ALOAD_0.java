package com.simple.jvm.instructions.loads.aload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

public class ALOAD_0 extends NoOperandsInstruction {
    
    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Object ref = localVars.getRef(0);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushRef(ref);
    }

}
