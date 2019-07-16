package com.panchen.jailer.naming.persistence;

import java.io.File;
import java.io.IOException;
import com.panchen.jailer.core.common.DataTree;

public interface SnapShot {

    long deserialize(DataTree dt) throws IOException;

    long serialize(DataTree dt) throws IOException,ClassNotFoundException;

    long findMostRecentSnapshotSeq() throws IOException;

    Boolean vaild(File f);
}
