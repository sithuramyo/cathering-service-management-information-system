package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.Email;
import com.CSMIS.CSMISKhaingFamily.Entity.User;


@Repository
@Component
public interface UserRepository extends JpaRepository<User, String> 
{

	 @Query("SELECT e FROM Email e")
	 List<Email> searchUser();
}