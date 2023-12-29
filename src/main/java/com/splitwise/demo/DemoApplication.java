package com.splitwise.demo;

import com.splitwise.demo.commands.Command;
import com.splitwise.demo.commands.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.net.http.HttpClient;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



//	@Override
//	public void run(String... args) throws Exception {
//		Scanner scanner = new Scanner(System.in);
//		while(true){
//			System.out.println("Awaiting input...");
//			String command = scanner.nextLine();
//			Optional<Command> executer = CommandRegistry.getInstance().get(command);
//			if(executer.isEmpty()){
//				System.out.println("Invalid Command,Please try again..");
//				continue;
//			}
//			Command cmd = executer.get();
//			try {
//				cmd.validateAndExecute(command);
//			} catch (Exception exception){
//				System.out.println("Invalid Command , please try again..");
//			}
//		}
//	}
}
