package com.simple.jvm.rtda;

/**
 * 栈帧
 */
public class Frame {

    Frame lower;
    private LocalVars localVars;        //  局部变量表
    private OperandStack operandStack;  //  操作数栈

    public Frame(int maxLocals, int maxStack) {
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

}
