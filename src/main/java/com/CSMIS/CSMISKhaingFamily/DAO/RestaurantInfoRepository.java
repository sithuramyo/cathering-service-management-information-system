package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;

public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo, String> {
	@Query(value = "SELECT COUNT(*) FROM RestaurantInfo")
	int countRes();
	
	@Query(value = "SELECT ri FROM RestaurantInfo ri WHERE ri.id = :id")
	RestaurantInfo getResInfo(@Param("id") String id);

	 @Modifying
	 @Transactional
	 @Query(value = "DELETE From RestaurantInfo s WHERE s.id=?1",nativeQuery = true)
	 void deleteById(String id);

	 @Query(value = "SELECT r FROM RestaurantInfo r WHERE r.status = 'ACTIVE'")
	 RestaurantInfo findLastRow();
	 
	 @Query(value = "SELECT ci FROM RestaurantInfo ci ORDER BY ci.id DESC")
	 List<RestaurantInfo> findAllOrderByDesc();
	 default RestaurantInfo findLastRestaurant() {
	        List<RestaurantInfo> restaurant = findAllOrderByDesc();
	        if (!restaurant.isEmpty()) {
	            return restaurant.get(0);
	        }
	        return null;
	    }
	 
	 @Query("SELECT r FROM RestaurantInfo r WHERE r.status = 'ACTIVE'")
	    List<RestaurantInfo> findActiveRestaurants();
	 
 	 @Modifying
     @Transactional
     @Query("UPDATE RestaurantInfo r SET r.status = 'INACTIVE' WHERE r.id != ?1")
     int updateStatusToInactive(String id);
 	 
 	 @Modifying
	 @Transactional
	 @Query("UPDATE RestaurantInfo SET id=?1,resturantname=?2,resaddress=?3,resph=?4,resemail=?5,status=?6 WHERE id = ?7")
	 int updateStatusWithId(String id,String name,String address,String phone,String email,String status,String id1);
 	 
 	@Modifying
 	@Transactional
    @Query("DELETE FROM RestaurantInfo r where r.id=?1")
    void deleteResturant(String id);
 	
 	 @Query("SELECT s from RestaurantInfo s where s.status='ACTIVE'")
	 List<RestaurantInfo> resList();
}
