package com.simple.jvm.rtda;

/**
 * 操作数栈
 */
public class OperandStack {

    private int size = 0;
    private Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void pushInt(int val) {
        slots[size].num = val;
        size++;
    }

    public int popInt() {
        size--;
        return slots[size].num;
    }

    public void pushFloat(float val) {
        slots[size].num = (int) val;
        size++;
    }

    public float popFloat() {
        size--;
        return slots[size].num;
    }

    public void pushLong(long val) {
        slots[size].num = (int) val;
        slots[size + 1].num = (int) (val >> 32);
        size += 2;
    }

    public long popLong() {
        size -= 2;
        int low = slots[size].num;
        int high = slots[size + 1].num;
        return (long) (high) << 32 | (long) (low);
    }

    public void pushDouble(double val) {
        this.pushLong((long) val);
    }

    public double popDouble() {
        return this.popLong();
    }

    public void pushRef(Object ref) {
        slots[size].ref = ref;
        size++;
    }

    public Object popRef() {
        size--;
        Object ref = slots[size].ref;
        slots[size].ref = null;
        return ref;
    }

    public void pushSlot(Slot slot) {
        slots[size] = slot;
        size++;
    }

    public Slot popSlot(){
        size --;
        return slots[size];
    }

    public Slot[] getSlots() {
        return slots;
    }

}
