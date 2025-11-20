// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {

	}  

public static int plus(int x1, int x2) {
    // 1. אם x2 שלילי, הופך לחיסור: x1 - |x2|
    if (x2 < 0) {
        // minus(0, x2) מחזיר את הערך המוחלט של x2
        return minus(x1, minus(0, x2)); 
    } 
    // 2. אם x1 שלילי (אבל x2 חיובי), הופך לחיסור: x2 - |x1|
    if (x1 < 0) {
        return minus(x2, minus(0, x1));
    }
    
    // 3. מקרה בסיס: שניים חיוביים (או x2=0)
    int result = x1;
    int i = 0;
    while (i < x2) {
        result++;
        i++;
    }
    return result;
}

public static int minus(int x1, int x2) {
    // 1. אם x2 שלילי, הופך לחיבור: x1 + |x2|
    if (x2 < 0) {
        return plus(x1, minus(0, x2));
    }
    
    // 2. אם x1 שלילי ו-x2 חיובי: (-|x1|) - x2 = -( |x1| + x2 )
    if (x1 < 0) {
        // נשתמש ב-minus(0, x1) כדי לקבל את |x1|
        int absX1 = minus(0, x1);
        int sum = plus(absX1, x2);
        // נהפוך את סימן הסכום
        return minus(0, sum); 
    }
    
    // 3. מקרה בסיס: חיסור חוזר באמצעות -- (כששני המספרים חיוביים)
    int result = x1;
    int i = 0;
    while (i < x2) {
        result--;
        i++;
    }
    return result;
}

public static int times(int x1, int x2) {
    if (x1 == 0 || x2 == 0) {
        return 0;
    }
    boolean negativeResult = (x1 < 0) != (x2 < 0);
    int absX1 = (x1 < 0) ? minus(0, x1) : x1;
    int absX2 = (x2 < 0) ? minus(0, x2) : x2;
    int result = 0;
    int i = 0;
    while (i < absX2) {
        result = plus(result, absX1); 
        i = plus(i, 1); 
    }
        if (negativeResult) {
        return minus(0, result);
    }
    
    return result;
}

	public static int pow(int x, int n) {
		
		if (n == 0) {
        return 1;
    }
        if (x == 0) {
        return 0;
    }
    int result = 1;
    int i = 0;
    while (i < n) {
        result = times(result, x);
        i = plus(i, 1);
    }    
    return result;
}

public static int div(int x1, int x2) {
    if (x2 == 0) {
    return 0; 
    }
    if (minus(x1, x2) < 0) {
        return 0;
    }
    int count = 0;
    int current = x1;
    while (minus(current, x2) >= 0) { 
        current = minus(current, x2); 
        count = plus(count, 1); 
    } 
    return count;
}

public static int mod(int x1, int x2) {
    if (x2 == 0) {
         return 0; 
    }    
    if (minus(x1, x2) < 0) {
        return x1;
    }
    
    int current = x1;
    while (minus(current, x2) >= 0) { 
        current = minus(current, x2);
    }
    return current;
}

public static int sqrt(int x) {
    if (x < 0) {
        return 0; 
    }
    if (x == 0 || x == 1) {
        return x;
    }
    int low = 1;
    int high = div(x, 2); 
    int ans = 1; 
    while (low <= high) {        
        int diff = minus(high, low);
        int halfDiff = div(diff, 2);
        int mid = plus(low, halfDiff); 
        int midSquared = times(mid, mid);
        if (midSquared <= x) { 
            ans = mid; 
            low = plus(mid, 1); 
        } else { 
            high = minus(mid, 1); 
        }
    }
	

    
    return ans;
}	  
}