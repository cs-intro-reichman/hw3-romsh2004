public class Anagram {
	public static void main(String args[]) {
		System.out.println(isAnagram("silent","listen")); 
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); 
		System.out.println(isAnagram("Madam Curie","Radium came")); 
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); 

		System.out.println(preProcess("What? No way!!!"));
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		String str = "1234567";
		Boolean pass = true;
		for (int i = 0; i < 10000; i++) {
			String randomAnagram = randomAnagram(str);
			String str1 = preProcess(str);
			String str2 = preProcess(randomAnagram);
			if (!isAnagram(str1, str2)) {
				pass = false;
				break;
			}
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	public static boolean isAnagram(String str1, String str2) {
		String s1 = preProcess(str1);
		String s2 = preProcess(str2);
		
		if (s1.length() != s2.length()) return false;
		
		int[] count = new int[256]; 
		for (int i = 0; i < s1.length(); i++) count[s1.charAt(i)]++;
		for (int i = 0; i < s2.length(); i++) count[s2.charAt(i)]--;
		for (int i = 0; i < 256; i++) if (count[i] != 0) return false;
		return true;
	}
	   
	public static String preProcess(String str) {
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				if (c >= 'A' && c <= 'Z') {
					res += (char)(c + 32);
				} else {
					res += c;
				}
			}
		}
		return res;
	} 
	   
	public static String randomAnagram(String str) {
		String newStr = "";
		String remaining = str;
		while (remaining.length() > 0) {
			int randomIndex = (int)(Math.random() * remaining.length());
			newStr += remaining.charAt(randomIndex);
			remaining = remaining.substring(0, randomIndex) + remaining.substring(randomIndex + 1);
		}
		return newStr;
	}
}