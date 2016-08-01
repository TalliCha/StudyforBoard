package ojt.test.boardpjt.logictest;

import java.util.UUID;

public class UUIDlengthCHK {
	public static void main(String[] args) {
		

		for (int i = 0; i < 10; i++) {
			
		String uuid = UUID.randomUUID().toString();
		
		System.out.println(uuid);
		System.out.println(uuid.getBytes().length);
		System.out.println(uuid.length());
		}
	}
	
}
