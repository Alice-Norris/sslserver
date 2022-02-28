package com.snhu.sslserver;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class Controller {
    
    public String dataChecksumGenerator(String dataText) throws NoSuchAlgorithmException, IOException{
        MessageDigest fileHasher = MessageDigest.getInstance("SHA-256");
        fileHasher.update(dataText.getBytes());
        byte[] fileHash = fileHasher.digest();
        String checksum = DatatypeConverter.printHexBinary(fileHash);
        return checksum;
    }

	@RequestMapping(value="/hash", method = RequestMethod.GET)
	public ResponseEntity<String> hashFile(){
		try{
            String dataString = "Alice Norris";
			String checksumString = dataChecksumGenerator(dataString);
			ResponseEntity<String> response = new ResponseEntity<>("<p style = 'color:blue; font-weight: bold'>String: " + dataString + "</p> <p style= 'color:green; font-weight: bold'>Checksum of String: " + checksumString + "</p>", HttpStatus.OK);
			return response;
		} catch(NoSuchAlgorithmException missingAlgorithm) {
			System.out.println("AlgoException: " + missingAlgorithm.getMessage());
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		} catch(IOException badFile){
			System.out.println("FileException: " + badFile.getMessage());
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
}   
