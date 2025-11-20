// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

public static int plus(int x1, int x2) {
    if (x2 < 0) {
        return minus(x1, minus(0, x2)); 
    }    if (x1 < 0) {
        return minus(x2, minus(0, x1));
    }
    int result = x1;
    int i = 0;
    while (i < x2) {
        result++;
        i++;
    }
    return result;
	}

public static int minus(int x1, int x2) {
        if (x2 < 0) {
        return plus(x1, minus(0, x2));
    }
    if (x1 < 0) {
        int absX1 = minus(0, x1);
        int sum = plus(absX1, x2);
        return minus(0, sum); 
    }
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