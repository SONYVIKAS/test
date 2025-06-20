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
                int[][] transpose = new int[mat[0].length][mat.length];
                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[0].length; j++) {
                        transpose[j][i] = mat[i][j];
                    }
                }
                return transpose;
            } catch (Exception e) {
                return new int[][]{{-1}};
            }
        } else {
            return null;
        }
    }