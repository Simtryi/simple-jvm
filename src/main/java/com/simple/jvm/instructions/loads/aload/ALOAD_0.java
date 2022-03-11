package com.simple.jvm.instructions.loads.aload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.LocalVars;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class ALOAD_0 extends NoOperandsInstruction {
    
    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        Object ref = localVars.getRef(0);

        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushRef(ref);
    }

}
