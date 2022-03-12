package com.simple.jvm.rtda.jvmstack;

import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.heap.methodarea.Method;

/**
 * 栈帧
 */
public class Frame {

    Frame lower;
    private LocalVars localVars;        //  局部变量表
    private OperandStack operandStack;  //  操作数栈
    private Thread thread;
    private Method method;
    private int nextPC;

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.maxLocals);
        operandStack = new OperandStack(method.maxStack);
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

    public void revertNextPC(){
        nextPC = thread.getPC();
    }

    public Method getMethod(){
        return method;
    }

}
