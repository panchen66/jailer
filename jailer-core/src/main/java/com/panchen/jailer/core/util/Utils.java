package com.panchen.jailer.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<File> searchFolder(File origin) {
        if (!origin.exists()) {
            return null;
        }
        File[] files = origin.listFiles();
        if (null == files || files.length == 0) {
            return null;
        }
        List<File> res = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                res.addAll(searchFolder(file));
            } else {
                res.add(file);
            }
        }
        return res;
    }

}
