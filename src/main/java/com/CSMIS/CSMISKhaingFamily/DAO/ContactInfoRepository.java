package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.ContactInfo;


@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, String> 
{
	ContactInfo findTopByOrderByIdDesc();
}