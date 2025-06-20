public class Matrix {
    private int[][] mat;

    public Matrix(int[][] mat) {
        this.mat = mat;
    }

    public int[][] getMatrix() {
        return mat;
    }

    public int[][] transpose() {
        if (mat!= null) {
            try {
                int rows = mat.length;
                int cols = mat[0].length;
                int[][] transposedMat = new int[cols][rows];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        transposedMat[j][i] = mat[i][j];
                    }
                }
                return transposedMat;
            } catch (Exception e) {
                return new int[][]{{0}};
            }
        } else {
            return new int[][]{{0}};
        }
    }