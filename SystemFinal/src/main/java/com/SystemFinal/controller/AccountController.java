package com.SystemFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import com.SystemFinal.entity.Account;
import com.SystemFinal.repository.AccountRepository;
import com.SystemFinal.service.AccountService;

@RestController
@RequestMapping("api/acco/")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = "findall", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}, headers = "Accept=Application/json")
	public ResponseEntity<Iterable<Account>> findAll(){
	try {
		return new ResponseEntity<Iterable<Account>>(accountService.findAllAccount(),HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<Iterable<Account>>(HttpStatus.BAD_REQUEST);
	}
}
	@GetMapping(value = "find/{account_number}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}, headers = "Accept=Application/json")
	public ResponseEntity<Account> find(@PathVariable("account_number") int account_number){
			try {
				return new ResponseEntity<Account>(accountService.findById(account_number),HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
			}
	}	
	
	@PostMapping(value = "create", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}, consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE}, headers = "Accept=Application/json")
	public ResponseEntity<Void> create(@RequestBody Account acc){
			try {
				accountService.createAccount(acc);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
	}
	
	@PutMapping(value = "update", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}, consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE}, headers = "Accept=Application/json")
	public ResponseEntity<Void> update(@RequestBody Account acc){
			try {
				accountService.updateAccount(acc);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("account_number") int  account_number){
			try {
				accountService.deleteAccount(account_number);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
	}
	

	
}
