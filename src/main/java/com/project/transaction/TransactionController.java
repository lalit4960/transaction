package com.project.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	

	
	@PostMapping("/updateBalance/{accID}")
	public ResponseEntity<Account> updateBalance(@PathVariable Integer accID){
		transactionService.updateBalance(accID, 2);
		return new ResponseEntity<Account>(HttpStatus.OK);
	}

}
