package Others;

import java.util.Random;

public class GenerateOTP {

	public char[] sendOTP(int length) {
		System.out.println("your otp for this transaction is : ");
		String numbers="0123456789";
		Random r = new Random();
		char[] otp = new char[length];
		for(int i=0;i<length;i++) {
			otp[i] = numbers.charAt(r.nextInt(numbers.length()));
		}
		System.out.println(otp);
		return otp;
	}
}
