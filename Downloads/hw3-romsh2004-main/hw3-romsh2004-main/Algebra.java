public class Algebra {
    public static void main(String args[]) {
        // בדיקות עצמיות
        System.out.println(plus(2, 3));
        System.out.println(minus(7, 2));
        System.out.println(minus(2, 7));
        System.out.println(times(3, 4));
        System.out.println(plus(2, times(4, 2)));
        System.out.println(pow(5, 3));
        System.out.println(pow(3, 5));
        System.out.println(div(12, 3));
        System.out.println(div(5, 5));
        System.out.println(div(25, 7));
        System.out.println(mod(25, 7));
        System.out.println(mod(120, 6));
        System.out.println(sqrt(36));
        System.out.println(sqrt(263169));
        System.out.println(sqrt(76123));
    }

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        // אם x2 חיובי, נוסיף 1, x2 פעמים
        if (x2 >= 0) {
            for (int i = 0; i < x2; i++) {
                x1++;
            }
        } else {
            // אם x2 שלילי, נחסיר 1, |x2| פעמים
            for (int i = 0; i > x2; i--) {
                x1--;
            }
        }
        return x1;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        // אם x2 חיובי, נחסיר 1, x2 פעמים
        if (x2 >= 0) {
            for (int i = 0; i < x2; i++) {
                x1--;
            }
        } else {
            // אם x2 שלילי (מינוס מינוס שווה פלוס), נוסיף 1, |x2| פעמים
            for (int i = 0; i > x2; i--) {
                x1++;
            }
        }
        return x1;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        // קביעת הסימן של התוצאה
        boolean negativeResult = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
        
        // עבודה עם ערכים מוחלטים כדי להקל על הלולאה
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;
        
        int result = 0;
        for (int i = 0; i < absX2; i++) {
            result = plus(result, absX1);
        }
        
        // החזרת הסימן במידת הצורך
        return negativeResult ? minus(0, result) : result;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        if (n == 0) return 1;
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = times(result, x);
        }
        return result;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0; // חלוקה באפס
        
        // קביעת הסימן
        boolean negativeResult = (x1 < 0 && x2 > 0) || (x1 > 0 && x2 < 0);
        
        // עבודה עם ערכים מוחלטים
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;
        
        int count = 0;
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2);
            count++;
        }
        
        return negativeResult ? minus(0, count) : count;
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        if (x2 == 0) return 0;
        
        // מודולו בדרך כלל מקבל את הסימן של המחולק (x1) ברוב השפות, אבל נממש פשוט:
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;
        
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2);
        }
        // אם המספר המקורי היה שלילי, השארית צריכה להיות תואמת (תלוי הגדרה, כאן נשאיר חיובי)
        return x1 < 0 ? minus(0, absX1) : absX1; 
    }

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        if (x <= 0) return 0;
        
        int low = 1;
        int high = x;
        int ans = 1;
        
        while (low <= high) {
            int mid = plus(low, div(minus(high, low), 2));
            int square = times(mid, mid);
            
            if (square == x) return mid;
            
            if (square < x) {
                ans = mid;
                low = plus(mid, 1);
            } else {
                high = minus(mid, 1);
            }
        }
        return ans;
    }
}