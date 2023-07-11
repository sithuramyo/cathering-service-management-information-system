package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.MenuImportData;

@Repository
public interface AdminMenuImportRepo extends JpaRepository<MenuImportData, Long> {

}
