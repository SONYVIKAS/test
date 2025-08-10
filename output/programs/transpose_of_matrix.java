
// Define Matrix class
public class Matrix {
    private int[][] mat;

    // Constructor
    public Matrix(int[][] mat) {
        this.mat = mat;
    }

    // Method to get matrix
    public int[][] getMatrix() {
        return this.mat;
    }

    // Method to calculate transpose of matrix
    public int[][] transpose() {
        int[][] transpose = new int[this.mat[0].length][this.mat.length];
        for (int i = 0; i < this.mat.length; i++) {
            for (int j = 0; j < this.mat[0].length; j++) {
                transpose[j][i] = this.mat[i][j];
            }
        }
        return transpose;
    }

    // Main method
    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        Matrix matrixObj = new Matrix(mat);
        System.out.println("Original Matrix is : ");
        for (int[] row : matrixObj.getMatrix()) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("Transpose of above matrix is : ");
        for (int[] row : matrixObj.transpose()) {
            System.out.println(Arrays.toString(row));
        }
    }