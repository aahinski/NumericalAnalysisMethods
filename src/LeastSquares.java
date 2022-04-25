public class LeastSquares {

        static public double f(double x) {
            return (0.15 * Math.exp(x) + 0.85 * Math.sin(x));
        }

        static public double approximation(double[] c, double x) {
            return (c[0] + c[1] * x + c[2] * Math.pow(x, 2) + c[3] * Math.pow(x, 3) +
                    c[4] * Math.pow(x, 4) + c[5] * Math.pow(x, 5));
        }

        static public double[] gaussianElimination(double[][] A, double[] B)
        {
            int N = B.length;
            for (int k = 0; k < N; k++)
            {
                int max = k;
                for (int i = k + 1; i < N; i++)
                    if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                        max = i;
                double[] temp = A[k];
                A[k] = A[max];
                A[max] = temp;
                double t = B[k];
                B[k] = B[max];
                B[max] = t;
                for (int i = k + 1; i < N; i++)
                {
                    double factor = A[i][k] / A[k][k];
                    B[i] -= factor * B[k];
                    for (int j = k; j < N; j++)
                        A[i][j] -= factor * A[k][j];
                }
            }
            double[] solution = new double[N];
            for (int i = N - 1; i >= 0; i--)
            {
                double sum = 0.0;
                for (int j = i + 1; j < N; j++)
                    sum += A[i][j] * solution[j];
                solution[i] = (B[i] - sum) / A[i][i];
            }
            for (int i = 0; i < solution.length; i++) {
                System.out.println(solution[i]);
            }
            return solution;
        }

    public static void main(String[] args) {
        double[] x = new double[11];
        double[] f = new double[11];
        int n = 10;
        int m = n / 2;
        for (int i = 0; i < n + 1; i++) {
            x[i] = 0.15 + 0.1 * i;
            f[i] = f(x[i]);
        }
        System.out.println("x");
        for(double xi : x)
            System.out.println(xi);
        System.out.println("f");
        for(double fi : f)
            System.out.println(fi);
        double sum;
        double[][] A = new double[m + 1][m + 1];
        double[] b = new double[m + 1];
        for(int l = 0; l < m + 1; l++) {
            for(int k = 0; k < m + 1; k++) {
                sum = 0;
                for (int i = 0; i < n + 1; i++) {
                    sum += Math.pow(x[i], (k + l));
                }
                A[l][k] = sum;
            }
            sum = 0;
            for (int i = 0; i < n + 1; i++) {
               sum += f[i] * Math.pow(x[i], l);
            }
            b[l] = sum;
        }
        double[] c = gaussianElimination(A, b);
        double x1 = 0.15 + 1 / 15;
        double x2 = 0.7;
        double x3 = 1.15 - 1 / 30;
        System.out.println(approximation(c, x1));
        System.out.println(f(x1) - approximation(c, x1));
        System.out.println(approximation(c, x2));
        System.out.println(f(x2) - approximation(c, x2));
        System.out.println(approximation(c, x3));
        System.out.println(f(x3) - approximation(c, x3));
        sum = 0;
        for (int i = 0; i < n + 1; i++) {
            sum += Math.pow(f(x[i]) - approximation(c, x[i]), 2);
        }
        System.out.println(Math.pow(sum, 0.5));
    }
}