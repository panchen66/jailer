package com.panchen.jailer.naming.persistence;

import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import com.panchen.jailer.core.common.DataTree;
import com.panchen.jailer.core.util.Utils;
import org.springframework.stereotype.Component;
import com.panchen.jailer.core.cluster.ClusterManage;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class FileManage {


    @Autowired
    private ClusterManage clusterManage;

    public class SnapShotActuator {
        private ThreadPoolExecutor snapShotThreadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public class FileSnapShot implements SnapShot {

        @Override
        public long deserialize(DataTree dt) throws IOException {
            long seq = findMostRecentSnapshotSeq();
            File snapShotF = new File("jailer-" + seq);
            try (ObjectOutputStream snapOS = new ObjectOutputStream(new FileOutputStream(snapShotF))) {
                snapOS.writeObject(clusterManage.self.dataTree);
            }
            return seq;
        }

        @Override
        public long serialize(DataTree dt) throws IOException, ClassNotFoundException {
            long seq = findMostRecentSnapshotSeq();
            File snapShotF = new File("jailer-" + ++seq);
            try (ObjectInputStream snapIS = new ObjectInputStream(new FileInputStream(snapShotF))) {
                clusterManage.self.dataTree = (DataTree) snapIS.readObject();
            }
            return seq;
        }

        @Override
        public long findMostRecentSnapshotSeq() throws IOException {
            List<File> vaildSnapShots = getAllSnapShotFile();
            if (null == vaildSnapShots || 0 >= vaildSnapShots.size()) {
                return 1l;
            }
            Collections.sort(vaildSnapShots, new SnapShotComparator<File>());
            String mostRecentSnapshotName = vaildSnapShots.get(0).getName();
            String[] seq = mostRecentSnapshotName.split("-");
            if (1 >= seq.length) {
                return 1l;
            }
            return Long.valueOf(seq[1]);
        }


        @Override
        public Boolean vaild(File f) {
            // todo
            return true;
        }

        /**
         * name like jailer-1~2^63-1
         * 
         * @author pc
         *
         * @param <T>
         */
        private class SnapShotComparator<T> implements Comparator<File> {

            @Override
            public int compare(File o1, File o2) {
                return Long.valueOf(o1.getName().substring(7, o1.getName().length() - 1)) > Long
                        .valueOf(o2.getName().substring(7, o2.getName().length() - 1)) ? 1 : 0;
            }


        }

        private List<File> getAllSnapShotFile() {
            List<File> snapShots = Utils.searchFolder(clusterManage.self.snapShotFolder);
            // vaild
            for (File f : snapShots) {
                vaild(f);
            }
            return snapShots;
        }

    }
}
