public class NewtonMethodForNonLinearEquationsSystems {
    public static double norm(double x_1, double y_1, double x_2, double y_2) {
        double x = x_1 - x_2;
        double y = y_1 - y_2;
        return Math.max(Math.abs(x), Math.abs(y));
    }

    static double f(double x, double y) { return (y * x + y - 5); }
    static double g(double x, double y) { return (y - 4 * x - 1); }

    static double partialDerivativeDfDx(double x, double y) {
        return y;
    }
    static double partialDerivativeDfDy(double x, double y) {
        return x + 1;
    }
    static double partialDerivativeDgDx(double x, double y) {
        return -4;
    }
    static double partialDerivativeDgDy(double x, double y) {
        return 1;
    }

    static double jacobian(double x, double y) {
        return (partialDerivativeDfDx(x, y) * partialDerivativeDgDy(x, y) -
                partialDerivativeDgDx(x, y) * partialDerivativeDfDy(x, y));
    }

    static Point algorithm(double x_null, double y_null, double eps) {
        double x_K = x_null;
        double y_K = y_null;
        double x_KPlusOne = x_K - ((1 / jacobian(x_K, y_K)) *
                           (partialDerivativeDgDy(x_K, y_K) * f(x_K, y_K) -
                            partialDerivativeDfDy(x_K, y_K) * g(x_K, y_K)));
        double y_KPlusOne = y_K - ((1 / jacobian(x_K, y_K)) *
                           (- partialDerivativeDgDx(x_K, y_K) * f(x_K, y_K) +
                              partialDerivativeDfDx(x_K, y_K) * g(x_K, y_K)));
        System.out.println(x_KPlusOne);
        System.out.println(y_KPlusOne);
        System.out.println();
        int iterationsQuantity = 1;
        while (norm(x_KPlusOne, y_KPlusOne, x_K, y_K) >= eps) {
            x_K = x_KPlusOne;
            y_K = y_KPlusOne;
            x_KPlusOne = x_K - (1 / jacobian(x_K, y_K)) *
                        (partialDerivativeDgDy(x_K, y_K) * f(x_K, y_K) -
                                partialDerivativeDfDy(x_K, y_K) * g(x_K, y_K));
            y_KPlusOne = y_K - (1 / jacobian(x_K, y_K)) *
                        (- partialDerivativeDgDx(x_K, y_K) * f(x_K, y_K) +
                                partialDerivativeDfDx(x_K, y_K) * g(x_K, y_K));
            iterationsQuantity++;
            System.out.println(x_KPlusOne);
            System.out.println(y_KPlusOne);
            System.out.println();
        }
        System.out.println(iterationsQuantity);
        Point solution = new Point();
        solution.x = x_KPlusOne;
        solution.y = y_KPlusOne;
        return solution;
    }

    public static void main(String[] args) {
        Point solution = new Point(algorithm(-1.5, -6.0, Math.pow(10, -7)));
        System.out.println(solution.x);
        System.out.println(solution.y);
    }
}