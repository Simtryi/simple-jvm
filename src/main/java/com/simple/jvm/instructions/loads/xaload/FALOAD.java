package com.simple.jvm.instructions.loads.xaload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 加载float类型数组
 */
public class FALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int idx = stack.popInt();
        Object arrRef = stack.popRef();

        checkNotNull(arrRef);
        float[] floats = arrRef.getFloats();
        checkIndex(floats.length, idx);
        stack.pushFloat(floats[idx]);
    }

}