public class SecantMethod {
    static double f(double x) {
        return (Math.pow((x - 1), 2) - (0.5 * Math.exp(x)));
    }

    static double algorithm(double x_null, double x_one, double eps) {
        double x_KMinusOne = x_null;
        double x_K = x_one;
        double x_KPlusOne = x_K - f(x_K) * ((x_K - x_KMinusOne) / (f(x_K) - f(x_KMinusOne)));
        int iterationsQuantity = 1;
        while(Math.abs(x_K - x_KPlusOne) > eps) {
            x_KMinusOne = x_K;
            x_K = x_KPlusOne;
            x_KPlusOne = x_K - f(x_K) * ((x_K - x_KMinusOne) / (f(x_K) - f(x_KMinusOne)));
            iterationsQuantity++;
        }
        System.out.println(iterationsQuantity);
        return x_KPlusOne;
    }

    public static void main(String[] args) {
        System.out.println(algorithm(0.25, 0.2, Math.pow(10, -7)));
        System.out.println(f(algorithm(0.25, 0.2, Math.pow(10, -7))));
    }
}
