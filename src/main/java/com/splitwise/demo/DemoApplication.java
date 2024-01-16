package com.splitwise.demo;

import com.splitwise.demo.commands.Command;
import com.splitwise.demo.commands.CommandRegistry;
import com.splitwise.demo.utils.UsersPendingPreFetchUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.net.http.HttpClient;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements Runnable{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run() {
		UsersPendingPreFetchUtil util = new UsersPendingPreFetchUtil();
		util.preFetchAndPopulateAllThePendingDetails();
	}


}
