// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
    public static void main(String args[]) {
        // בדיקות עצמיות בסיסיות (כפי שמופיעות בקובץ המקורי)
        System.out.println(plus(2,3));   // 5
        System.out.println(minus(7,2));  // 5
        System.out.println(minus(2,7));  // -5
        System.out.println(times(3,4));  // 12
        System.out.println(plus(2,times(4,2)));  // 10
        System.out.println(pow(5,3));      // 125
        System.out.println(pow(3,5));      // 243
        System.out.println(div(12,3));   // 4    
        System.out.println(div(5,5));    // 1  
        System.out.println(div(25,7));   // 3
        System.out.println(mod(25,7));   // 4
        System.out.println(mod(120,6));  // 0    
        System.out.println(sqrt(36));    // 6
        System.out.println(sqrt(263169)); // 513
        System.out.println(sqrt(76123)); // 275
    }  

    // Returns x1 + x2
    public static int plus(int x1, int x2) {
        if (x2 < 0) {
            for (int i = 0; i > x2; i--) {
                x1--;
            }
        } else {
            for (int i = 0; i < x2; i++) {
                x1++;
            }
        }
        return x1;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        if (x2 < 0) {
            for (int i = 0; i > x2; i--) {
                x1++;
            }
        } else {
            for (int i = 0; i < x2; i++) {
                x1--;
            }
        }
        return x1;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) return 0;
        
        // בדיקת סימן התוצאה
        boolean isNegative = (x1 < 0) != (x2 < 0);
        
        // המרת x1 ו-x2 לערכים חיוביים לטובת הלולאה
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;
        
        int result = 0;
        for (int i = 0; i < absX2; i++) {
            result = plus(result, absX1);
        }
        
        return isNegative ? minus(0, result) : result;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        if (n == 0) return 1;
        if (x == 0) return 0;
        
        int result = 1;
        // אם הבסיס שלילי והחזקה אי-זוגית, התוצאה שלילית
        // אבל פונקציית times שלנו כבר מטפלת בסימנים נכון, אז פשוט נקרא לה.
        for (int i = 0; i < n; i++) {
            result = times(result, x);
        }
        return result;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        if (x2 == 0) return 0; // חלוקה באפס
        
        boolean isNegative = (x1 < 0) != (x2 < 0);
        
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;
        
        int count = 0;
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2);
            count++;
        }
        
        return isNegative ? minus(0, count) : count;
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        if (x2 == 0) return 0;
        
        // שארית ב-Java מקבלת את הסימן של המחולק (x1)
        // נחשב הכל בחיובי ואז נתקן סימן
        int absX1 = x1 < 0 ? minus(0, x1) : x1;
        int absX2 = x2 < 0 ? minus(0, x2) : x2;
        
        while (absX1 >= absX2) {
            absX1 = minus(absX1, absX2);
        }
        
        return (x1 < 0) ? minus(0, absX1) : absX1;
    }   

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        if (x <= 0) return 0;
        if (x == 1) return 1;
        
        int low = 1;
        int high = x;
        int ans = 1;
        
        // חיפוש בינארי
        while (low <= high) {
            int diff = minus(high, low);
            int mid = plus(low, div(diff, 2));
            int square = times(mid, mid);
            
            if (square == x) return mid;
            
            if (square < x) { // אם הריבוע קטן מ-x, אולי התשובה גדולה יותר
                ans = mid;
                low = plus(mid, 1);
            } else { // אם הריבוע גדול מ-x, התשובה בטוח קטנה יותר
                high = minus(mid, 1);
            }
        }
        return ans;
    }         
}