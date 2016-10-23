package aufgabe1;


public class Aufgabe1 {

    /** 
     * Calculates the factorial of n.
     * 
     * @param n The number to calculate with.
     * @return The faculty number of n as Int.
     */
    public static int recursive(int n){
        if ( n == 1) { // when to stop
            return 1;
        }else
            return recursive(n-1)*n; 
    }
}
