package com.simple.jvm.rtda.heap.methodarea;

import com.simple.jvm.rtda.jvmstack.Slot;

public class Slots {

    private Slot[] slots;

    public Slots(int slotCount) {
        if (slotCount > 0) {
            slots = new Slot[slotCount];
            for (int i = 0; i < slotCount; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void setInt(int idx, int val) {
        slots[idx].num = val;
    }

    public int getInt(int idx) {
        return slots[idx].num;
    }

    public void setFloat(int idx, float val) {
        slots[idx].num = (int) val;
    }

    public float getFloat(int idx) {
        return slots[idx].num;
    }

    public void setLong(int idx, long val) {
        slots[idx].num = (int) val;
        slots[idx + 1].num = (int) (val >> 32);
    }

    public long getLong(int idx) {
        int low = slots[idx].num;
        int high = slots[idx + 1].num;
        return (long) high << 32 | (long) low;
    }

    public void setDouble(int idx, double val) {
        setLong(idx, (long) val);
    }

    public Double getDouble(int idx) {
        return (double) getLong(idx);
    }

    public void setRef(int idx, Object ref) {
        slots[idx].ref = ref;
    }

    public Object getRef(int idx){
        return slots[idx].ref;
    }

}
