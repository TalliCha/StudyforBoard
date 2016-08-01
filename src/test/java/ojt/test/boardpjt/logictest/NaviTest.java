package ojt.test.boardpjt.logictest;

public class NaviTest {
	public static void main(String[] args) {

		// logicA();
		logicB();

	}

	@SuppressWarnings("unused")
	private static void logicA() {
		int start = 1;
		int max = 10;
		int naviSize = 10;

		int end = max / naviSize + 1;
		System.out.println("start : " + start);
		System.out.println("end : " + end);

		for (int i = 0; i < naviSize; i++) {
			if (start > end) {
				break;
			}
			System.out.print(start++ + "\t");
		}

	}

	private static void logicB() {
		int start = 1;
		double max = 21;
		double naviSize = 10;
		
		int end2 =  (int) Math.ceil(max/(naviSize)); 

//		int end =  (max % naviSize == 0) ?  max/naviSize : max/naviSize + 1;
		
		System.out.println("start : " + start);
		System.out.println("end : " + end2);
		
		for (int i = 0; i < naviSize; i++) {
			if (start > end2) {
				break;
			}
			System.out.print(start++ + "\t");
		}
	}
}