public class Primes {
    public static void main(String[ ] args) {
        for (int n = 2; n <= 100; n++){
            if (isPrime(n)) {
                System.out.print(n + " ");
            }
        }
    }

    public static boolean isPrime(int n){
        for (int i = 2; i <= Math.pow(n, 0.5); i++){
            if (n % i == 0){
                return false;
            } 
        }
        return true;
    }
}   