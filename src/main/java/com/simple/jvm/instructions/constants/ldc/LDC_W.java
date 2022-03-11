package com.simple.jvm.instructions.constants.ldc;

import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 用于加载int、float和字符串常量，java.lang.Class实例或者MethodType和MethodHandle实例
 */
public class LDC_W extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Class clazz = frame.getMethod().getClazz();
        RunTimeConstantPool runTimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        Object c = runTimeConstantPool.getConstants(idx);

        if (c instanceof Integer) {
            stack.pushInt((Integer) c);
            return;
        }

        if (c instanceof Float) {
            stack.pushFloat((Float) c);
            return;
        }

        throw new RuntimeException("todo ldc");
    }

}
