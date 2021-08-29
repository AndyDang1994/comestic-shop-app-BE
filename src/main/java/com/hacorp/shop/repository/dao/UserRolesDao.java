/**
 * 
 */
package com.hacorp.shop.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hacorp.shop.repository.entity.UserRole;


/**
 * @author Krakenpham
 *
 */
@Repository
public interface UserRolesDao extends JpaRepository<UserRole, Long> {

}
