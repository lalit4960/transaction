package com.project.transaction;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService {

	RestTemplate restTemplate = new RestTemplate();

	public void updateBalance(int accID, int amount) {
		ResponseEntity<Account> account = restTemplate.exchange("http://localhost:8080/account/" + accID,
				HttpMethod.GET, null, Account.class);
		Account acc = new ObjectMapper().convertValue(account.getBody(), Account.class);
		if (amount < 0) {
			if (acc.getAccBalance() < amount)
				System.out.println("Insufficient balance");
			else {
				acc.setAccBalance(acc.getAccBalance() - amount);
				acc.setMessage("Amount debited");
				restTemplate.exchange("http://localhost:8080/account/" + accID, HttpMethod.PUT, null, Account.class);
				restTemplate.exchange("http://localhost:8080//notify/useradded", HttpMethod.POST, null, Account.class);
			}
		} else {
			acc.setAccBalance(acc.getAccBalance() + amount);
			acc.setMessage("Amount credited");
			restTemplate.exchange("http://localhost:8080/account/" + accID, HttpMethod.PUT, null, Account.class);
			restTemplate.exchange("http://localhost:8080//notify/useradded", HttpMethod.POST, null, Account.class);
		}
	}
}
