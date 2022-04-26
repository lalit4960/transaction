package com.project.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
	private int accId;
	private String name;
	private float accBalance;
	private String email;
	private String message;

}
