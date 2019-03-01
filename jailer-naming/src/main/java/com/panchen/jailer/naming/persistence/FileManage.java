package com.panchen.jailer.naming.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.panchen.jailer.core.common.DataTree;
import com.panchen.jailer.core.util.Utils;
import org.springframework.stereotype.Component;
import com.panchen.jailer.core.cluster.ClusterManage;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class FileManage {

    @Autowired
    private ClusterManage clusterManage;

    private class FileSnapShot implements SnapShot {

        @Override
        public long deserialize(DataTree dt) throws IOException {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void serialize(DataTree dt, File name) throws IOException {
            // TODO Auto-generated method stub
        }

        @Override
        public File findMostRecentSnapshot() throws IOException {
            List<File> vaildSnapShots = getAllSnapShotFile();
            if (null == vaildSnapShots || 0 >= vaildSnapShots.size()) {
                return null;
            }
            Collections.sort(vaildSnapShots, new SnapShotComparator<File>());
            return vaildSnapShots.get(0);
        }


        @Override
        public Boolean vaild(File f) {
            return null;
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
