class Point {
    public double x;
    public double y;

    public Point() {}

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }
}

public class GaussSeidelMethod {
    static double norm(double x_1, double y_1, double x_2, double y_2) {
        double x = x_1 - x_2;
        double y = y_1 - y_2;
        return Math.max(Math.abs(x), Math.abs(y));
    }

    static double phiForF(double x, double y) {
        return ((5 - y) / y);
    }

    static double fixedPointIterationForF(double x_K, double eps, double y_K) {
        double x_KPlusOne = phiForF(x_K, y_K);
        while(Math.abs(x_K - x_KPlusOne) >= eps) {
            x_K = x_KPlusOne;
            x_KPlusOne = phiForF(x_K, y_K);
        }
        return x_KPlusOne;
    }

    static double phiForG(double y, double x) {
        return (4 * x + 1);
    }

    static double fixedPointIterationForG(double y_K, double eps, double x_KPlusOne) {
        double y_KPlusOne = phiForG(y_K, x_KPlusOne);
        while(Math.abs(y_K - y_KPlusOne) >= eps) {
            y_K = y_KPlusOne;
            y_KPlusOne = phiForG(y_K, x_KPlusOne);
        }
        return y_KPlusOne;
    }

    static Point algorithm(double x_null, double y_null, double eps) {
        double x_K = x_null;
        double x_KPlusOne = fixedPointIterationForF(x_K, eps, y_null);
        double y_K = y_null;
        double y_KPlusOne = fixedPointIterationForG(y_K, eps, x_KPlusOne);
        int iterationsQuantity = 1;
        while(norm(x_KPlusOne, y_KPlusOne, x_K, y_K) >= eps) {
            x_K = x_KPlusOne;
            y_K = y_KPlusOne;
            x_KPlusOne = fixedPointIterationForF(x_K, eps, y_K);
            y_KPlusOne = fixedPointIterationForG(y_K, eps, x_KPlusOne);
            iterationsQuantity++;
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