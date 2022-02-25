public class NewtonMethod {
    static double algorithm(double x_null, double eps) {
        double x_K = x_null;
        double x_KPlusOne = x_K - 1 -
                ((Math.pow(x_K, 2) - (4 * x_K) + 3)/((2 * x_K) - 2 - 0.5 * Math.exp(x_K)));
        int iterationsQuantity = 1;
        while(Math.abs(x_K - x_KPlusOne) > eps) {
            x_K = x_KPlusOne;
            x_KPlusOne = x_K - 1 -
                    ((Math.pow(x_K, 2) - (4 * x_K) + 3)/((2 * x_K) - 2 - 0.5 * Math.exp(x_K)));
            iterationsQuantity++;
        }
        System.out.println(iterationsQuantity);
        return x_KPlusOne;
    }

    public static void main(String[] args) {
        System.out.print(algorithm(0.25, Math.pow(10, -7)));
    }
}
