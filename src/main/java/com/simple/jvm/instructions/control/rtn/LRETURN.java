package com.simple.jvm.instructions.control.rtn;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 返回long类型的值
 */
public class LRETURN extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        long val = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(val);
    }

}
