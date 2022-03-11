package com.simple.jvm.rtda;

/**
 * 栈帧
 */
public class Frame {

    Frame lower;
    private LocalVars localVars;        //  局部变量表
    private OperandStack operandStack;  //  操作数栈
    private Thread thread;
    private int nextPC;

    public Frame(Thread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Thread getThread() {
        return thread;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public int getNextPC() {
        return nextPC;
    }

}
