package com.panchen.jailer.naming.persistence;

import java.io.File;
import java.io.IOException;
import com.panchen.jailer.core.common.DataTree;

public interface SnapShot {

    long deserialize(DataTree dt) throws IOException;

    void serialize(DataTree dt, File name) throws IOException;

    File findMostRecentSnapshot() throws IOException;

    Boolean vaild(File f);
}
