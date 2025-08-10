
class Matrix {
    private int[][] mat;

    // Constructor to initialize the matrix
    public Matrix(int[][] mat) {
        this.mat = mat;
    }

    // Method to get the matrix
    public int[][] getMatrix() {
        return mat;
    }

    // Method to transpose the matrix
    public int[][] transpose() {
        if (mat != null) {
            int rowCount = mat.length;
            int colCount = mat[0].length;
            int[][] transposed = new int[colCount][rowCount];

            // Transpose logic: swap rows with columns
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    transposed[j][i] = mat[i][j];
                }
            }
            return transposed;
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        Matrix matrixObj = new Matrix(mat);
        System.out.println("Original Matrix is: ");
        System.out.println(Arrays.deepToString(matrixObj.getMatrix()));

        System.out.println("Transpose of above matrix is: ");
        System.out.println(Arrays.deepToString(matrixObj.transpose()));
    }