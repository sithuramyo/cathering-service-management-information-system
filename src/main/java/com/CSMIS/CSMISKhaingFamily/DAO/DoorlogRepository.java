package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.data.jpa.repository.JpaRepository;


import com.CSMIS.CSMISKhaingFamily.Entity.Doorlog;
import com.CSMIS.CSMISKhaingFamily.Entity.DoorlogKey;


public interface DoorlogRepository extends JpaRepository<Doorlog,DoorlogKey> {

}
