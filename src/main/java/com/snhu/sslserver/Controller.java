package com.snhu.sslserver;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class Controller {
    
    //takes data in the form of a string, returning the SHA-256 hash of the given string
    public String dataChecksumGenerator(String dataText) throws NoSuchAlgorithmException{
        MessageDigest dataHasher = MessageDigest.getInstance("SHA-256");
        dataHasher.update(dataText.getBytes());
        byte[] dataHash = dataHasher.digest();
        System.out.println(dataHash);
        String checksum = DatatypeConverter.printHexBinary(dataHash);
        return checksum;
    }

    //creates the mapping for the /hash URL, throws an algorithm exception if SHA-256 is not available
	@RequestMapping(value="/hash", method = RequestMethod.GET)
	public ResponseEntity<String> hashFile(){
		try{
            String dataString = "Alice Norris"; //string to be converted
			String checksumString = dataChecksumGenerator(dataString); //get SHA-256 hash
            //return HTML of the string and its checksum, with some styling 
			ResponseEntity<String> response = new ResponseEntity<>("<p style = 'color:blue; font-weight: bold'>String: " + dataString + "</p> <p style= 'color:green; font-weight: bold'>Checksum of String: " + checksumString + "</p>", HttpStatus.OK);
			return response; 
		} catch(NoSuchAlgorithmException missingAlgorithm) {
			System.out.println("AlgoException: " + missingAlgorithm.getMessage());
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		} 
	}
}   
