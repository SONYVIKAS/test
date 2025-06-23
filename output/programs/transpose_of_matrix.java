import java.util.List;
import java.util.ArrayList;

public class Matrix {
    private List<List<Integer>> mat;

    public Matrix(List<List<Integer>> mat) {
        this.mat = mat;
    }

    public List<List<Integer>> getMatrix() {
        return this.mat;
    }

    public List<List<Integer>> transpose() {
        if (this.mat!= null) {
            try {
                List<List<Integer>> transposedMatrix = new ArrayList<>();
                for (int i = 0; i < this.mat.get(0).size(); i++) {
                    List<Integer> row = new ArrayList<>();
                    for (List<Integer> col : this.mat) {
                        row.add(col.get(i));
                    }
                    transposedMatrix.add(row);
                }
                return transposedMatrix;
            } catch (Exception e) {
                return List.of("Failed to convert transpose because", e.getMessage());
            }
        } else {
            return null;
        }
    }