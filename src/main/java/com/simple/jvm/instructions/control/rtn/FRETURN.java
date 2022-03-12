package com.simple.jvm.instructions.control.rtn;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 返回float类型的值
 */
public class FRETURN extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        float val = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(val);
    }

}
