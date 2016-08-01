package ojt.test.util;

public class SearchKeyWord {

	public static String trans(String keyWord) {
		String keyWords[] = keyWord.split(" ");
		StringBuffer stringBuffer = new StringBuffer("");
		if (keyWord.length() > 0) {

			for (String word : keyWords) {
				if (word.length() > 0) {
					stringBuffer.append(" " + word);
				}
			}
			// System.out.println(stringBuffer);

			return stringBuffer.toString().trim().replace(' ', '|');
		} else {
			return ".";
		}
	}

	public static void main(String[] args) {
		String keys = "a        b";
		// String keys = " a b c d d d";
		System.out.println(keys);
		System.out.println(trans(keys));

	}
}
