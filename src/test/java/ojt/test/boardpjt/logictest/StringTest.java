package ojt.test.boardpjt.logictest;

public class StringTest {
	public static void main(String[] args) {
		String name = "    a        b     c      d  d       d";
		String names[] = name.split(" ");
		StringBuffer stringBuffer = new StringBuffer("");
		
		for (String string : names) {
			System.out.println(string);
			if(string.length() > 0){
				stringBuffer.append(" "+string);
			}
		}
		System.out.println(stringBuffer);
		System.out.println(stringBuffer.toString().trim().replace(' ', '|'));
	}
}
