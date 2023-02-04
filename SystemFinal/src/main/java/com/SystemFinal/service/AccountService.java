package com.SystemFinal.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SystemFinal.entity.Account;
@Service
public interface AccountService {
	public Iterable<Account> findAllAccount();
	public Account findById(int account_number);
	public void createAccount(Account acc);
	public void updateAccount(Account acc);
	public void deleteAccount(int account_number);
}
