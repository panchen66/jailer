package com.panchen.jailer.naming.recovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.panchen.jailer.naming.persistence.FileManage;

/**
 * Restore data at startup
 * 
 * @author pc
 *
 */
@Component
public class RecoveryManage {

    @Autowired
    FileManage fileManage;

    private void recoveryByPersistence() {

    }

    private void recoveryBytransportation() {
        
    }
}
