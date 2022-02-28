package com.snhu.sslserver;

import java.security.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SslServerApplication {
	
	public static void main(String[] args) {
		
		Security.setProperty("crypto.policy", "unlimited");
		SpringApplication.run(SslServerApplication.class, args);
	}
	

}