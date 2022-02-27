package com.snhu.sslserver;

import java.security.Security;
import javax.crypto.Cipher;
import com.sun.crypto.provider.SunJCE;
import java.security.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
@SpringBootApplication
public class SslServerApplication {
	
	public static void main(String[] args) {
		Provider securityProvider = Security.getProvider("SunJCE");

		SpringApplication.run(SslServerApplication.class, args);
	}
	
	@RequestMapping(value="/hash")
	public void hashFile(){
		Cipher checksumGenerationCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		return;
	}
}
