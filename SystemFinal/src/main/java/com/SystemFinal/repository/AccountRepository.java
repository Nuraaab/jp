package com.SystemFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SystemFinal.entity.Account;
@Repository("AccountRepository")
public interface AccountRepository extends CrudRepository<Account, Integer>,JpaRepository<Account, Integer>{
	@Query(value="select balance from acco where account_number=?1",nativeQuery = true)
	public int getBalanceById(int account_number);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update acco set balance=balance+?2 where account_number=?1",nativeQuery = true)
	public void saveBalanceById(int account_number,int balance);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update acco set balance=balance-?2 where account_number=?1",nativeQuery = true)
	public void withdrawById(int account_number,int balance);
}
