package com.simple.jvm.classpath.impl;

import com.simple.jvm.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositeEntry implements Entry {

    private final List<Entry> entries = new ArrayList<>();

    public CompositeEntry(String pathList) {
        //  File.pathSeparator: 路径分隔符
        String[] paths = pathList.split(File.pathSeparator);
        for (String path : paths) {
            entries.add(Entry.create(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : entries) {
            try {
                return entry.readClass(className);
            } catch (Exception ignored) {
                //  ignored
            }
        }
        throw new IOException("class not found " + className);
    }

    @Override
    public String toString() {
        String[] strs = new String[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            strs[i] = entries.get(i).toString();
        }
        return String.join(File.pathSeparator, strs);
    }

}
