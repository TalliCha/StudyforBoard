package ojt.test.boardpjt.logictest;

public class LoginTest {
	static int pno = 15;
	static int pageNavi = 10;

	public static void main(String[] args) {

//		 logicA();
//		 logicB();
	}

	@SuppressWarnings("unused")
	private static void logicA() {
		int start = (pno - 1) / pageNavi * pageNavi + 1;
		for (int i = 0; i < pageNavi; i++) {
			System.out.println(start++);
		}
	}
	
	@SuppressWarnings("unused")
	private static void logicB() {
		int start = pno -( (pno-1 ) % pageNavi );
		for (int i = 0; i < pageNavi; i++) {
			System.out.println(start++);
		}
	}
}
