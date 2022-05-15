public class FiniteDifferences {

    static public double f(double x) {
        return (0.15 * Math.exp(x) + 0.85 * Math.sin(x));
    }

    static int factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    static public double combinations(int k, int j) {
        return (factorial(k) / (factorial(j) * factorial(k - j)));
    }

    static double[][] finiteDifferences(double[] f) {
        int k = f.length;
        double[][] finiteDifferences = new double[4][k];
        for (int i = 0; i < k; i++) {
            finiteDifferences[0][i] = f[i];
        }
        k--;
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < k; j++) {
                finiteDifferences[i][j] =
                        finiteDifferences[i - 1][j + 1] - finiteDifferences[i - 1][j];
            }
            k--;
        }
        return finiteDifferences;
    }

    static public double interpolationAtTheBeginning(double x0, double h, double[] f, double x, double[][] delta) {
        double t = (x - x0) / h;
        return (
                f[0] + t * delta[1][0] +
                (t * (t - 1) * 0.5 * delta[2][0]) +
                (t * (t - 1) * (t - 2) * (1.0 / 6.0) * delta[3][0])
        );
    }

    static public double interpolationAtTheEnd(double xN, int n, double h, double[] f, double x, double[][] delta) {
        double t = (x - xN) / h;
        return (
                f[n] + t * delta[1][n - 1] + (t * (t - 1) * 0.5 * delta[2][n - 2]) +
                        (t * (t - 1) * (t - 2) * (1.0 / 6.0) * delta[3][n - 3])
        );
    }

    static public double interpolationAtTheMiddle(double xN, int n, double h, double[] f, double x, double[][] delta) {
        double t = (x - xN) / h;
        return (((f[n] + f[n + 1]) / 2.0) + (t - 1.0 / 2.0) * delta[1][n] +
                ((t - 1.0) * t / 4.0) * (delta[2][n] + delta[2][n - 1]) +
                (t * (t - 0.5) * (t - 1.0) * delta[3][n - 1] / 6.0)
        );
    }

    static public double reminderForInterpolationAtTheMiddle(double y, double xN, int k, double h, double x) {
        double t = (x - xN) / h;
        double reminder = f(y) * Math.pow(h, 2 * k) * (t - k) * t / factorial(2 * k);
        for (int i = 1; i < k; i++) {
            reminder *= (Math.pow(t, 2) - Math.pow(i, 2));
        }
        return reminder;
    }

    static public double reminderForInterpolation(double ksi, double param, int k, double h, double x) {
        double t = (x - param) / h;
        double reminder = Math.pow(h, k + 1) * f(ksi) / factorial(k + 1);
        for (int i = 0; i < k + 1; i++) {
            reminder *= (t - i);
        }
        return reminder;
    }

    static public double trueResidual(double x, double y) {
        return Math.abs(x - y);
    }

    public static void main(String[] args) {
        double[] x = new double[11];
        double[] f = new double[11];
        double h = 0.1;
        int n = 10;
        for (int i = 0; i < n + 1; i++) {
            x[i] = 0.15 + 0.1 * i;
            f[i] = f(x[i]);
        }
        double x1 = 0.15 + 1.0 / 15.0;
        double x2 = 0.65 + (1.0 / 2.0) * 0.1;
        double x3 = 1.15 - 1.0 / 30.0;
        double[][] delta = finiteDifferences(f);
        double interpolationInX1 = interpolationAtTheBeginning(x[0], h, f, x1, delta);
        double interpolationInX2 = interpolationAtTheMiddle(x[5], 5, h, f, x2, delta);
        double interpolationInX3 = interpolationAtTheEnd(x[n], n, h, f, x3, delta);
        System.out.println(interpolationInX1);
        System.out.println(interpolationInX2);
        System.out.println(interpolationInX3);
        System.out.println(trueResidual(f(x1), interpolationInX1));
        System.out.println(trueResidual(f(x2), interpolationInX2));
        System.out.println(trueResidual(f(x3), interpolationInX3));
        System.out.println(reminderForInterpolation(0.15, x[0], 3, 0.1, interpolationInX1));
        System.out.println(reminderForInterpolationAtTheMiddle(1.15, x[n], 2, 0.1, interpolationInX2));
        System.out.println(reminderForInterpolation(1.15, x[n], 3, 0.1, interpolationInX3));
    }
}
