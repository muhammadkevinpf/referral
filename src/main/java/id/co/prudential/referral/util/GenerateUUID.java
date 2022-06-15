package id.co.prudential.referral.util;

import java.util.UUID;

public class GenerateUUID {
	
	public static String generateID(){
		UUID id = UUID.randomUUID();
		return id.toString();
	}
	
}
