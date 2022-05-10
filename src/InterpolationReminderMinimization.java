public class InterpolationReminderMinimization {

    static public double f(double x) {
        return (0.15 * Math.exp(x) + 0.85 * Math.sin(x));
    }

    static public double ChebyshevNode(double a, double b, double k, double n) {
        return ((a + b + (b - a) * Math.cos(((2 * k + 1) * Math.PI)/(2 * (n + 1)))) / 2);
    }

    static int factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    static double interpolationReminder(double a, double b, int n, double max) {
        return ((max * Math.pow((b - a), (n + 1)))/(factorial(n + 1) * Math.pow(2, (2 * n + 1))));
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
        double a = 0.15;
        double b = 1.15;
        int n = 10;
        double[] x = new double[11];
        for (int i = 0; i < n + 1; i++) {
            x[i] = 0.15 + 0.1 * i;
        }
        double[] ChebyshevNodes = new double[11];
        double[] f = new double[11];
        for (int k = 0; k < n + 1; k++) {
            ChebyshevNodes[k] = ChebyshevNode(a, b, k, n);
            System.out.println();
            System.out.println(ChebyshevNodes[k]);
            f[k] = f(ChebyshevNodes[k]);
            System.out.println(f[k]);
        }
        double x1 = 0.15 + 1.0 / 15.0;
        double x2 = 0.65 + (1.0 / 2.0) * 0.1;
        double x3 = 1.15 - 1.0 / 30.0;
        double interpolationInX1 = lagrangePolinomial(x1, ChebyshevNodes, f);
        double interpolationInX2 = lagrangePolinomial(x2, ChebyshevNodes, f);
        double interpolationInX3 = lagrangePolinomial(x3, ChebyshevNodes, f);
        System.out.println(interpolationInX1);
        System.out.println(interpolationInX2);
        System.out.println(interpolationInX3);
        System.out.println(trueResidual(f(x1), interpolationInX1));
        System.out.println(trueResidual(f(x2), interpolationInX2));
        System.out.println(trueResidual(f(x3), interpolationInX3));
        double max = 0.6661802798364;
        System.out.println(interpolationReminder(a, b, n, max));
    }
}
