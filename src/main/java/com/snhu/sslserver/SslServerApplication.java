package com.snhu.sslserver;

import java.security.Security;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		//setting cryptopolicy to allow higher bit levels, 
		//as Oracle does not allow default usage of full cryptographic strengths
		//to comply with some international regulations limiting cryptographic strength	
		Security.setProperty("crypto.policy", "unlimited");
		SpringApplication.run(SslServerApplication.class, args);
	}
	

}