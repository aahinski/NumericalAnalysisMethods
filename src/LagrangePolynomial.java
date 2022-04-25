public class LagrangePolynomial {
    static public double f(double x) {
        return (0.15 * Math.exp(x) + 0.85 * Math.sin(x));
    }

    static int factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    static public double interpolationReminder(double value, double[] x, double max) {
        double w = 1;
        for(int j = 0; j < x.length; j++)
            w *= (value - x[j]);
        return (w * max / factorial(x.length));
    }

    static public double trueResidual(double x, double y) {
        return Math.abs(x - y);
    }

    static public double lagrangePolinomial(double value, double[] x, double[] f) {
        double sum = 0;
        double product;
        for(int k = 0; k < x.length; k++) {
            product = 1;
            for(int j = 0; j < x.length; j++) {
                if(j != k) {
                    product *= (value - x[j])/(x[k] - x[j]);
                }
            }
            product *= f[k];
            sum += product;
        }
        return sum;
    }

    public static void main(String[] args) {
        double[] x = new double[11];
        double[] f = new double[11];
        int n = 10;
        for (int i = 0; i < n + 1; i++) {
            x[i] = 0.15 + 0.1 * i;
            f[i] = f(x[i]);
        }
        double x1 = 0.15 + 1 / 15;
        double x2 = 0.7;
        double x3 = 1.15 - 1 / 30;
        double interpolationInX1 = lagrangePolinomial(x1, x, f);
        double interpolationInX2 = lagrangePolinomial(x2, x, f);
        double interpolationInX3 = lagrangePolinomial(x3, x, f);
        System.out.println(interpolationInX1);
        System.out.println(interpolationInX2);
        System.out.println(interpolationInX3);
        System.out.println(trueResidual(f(x1), interpolationInX1));
        System.out.println(trueResidual(f(x2), interpolationInX2));
        System.out.println(trueResidual(f(x3), interpolationInX3));
        double max = 0.6661802798364;
        System.out.println(interpolationReminder(x1, x, max));
        System.out.println(interpolationReminder(x2, x, max));
        System.out.println(interpolationReminder(x3, x, max));
    }
}
