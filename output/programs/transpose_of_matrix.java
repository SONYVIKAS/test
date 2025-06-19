class Matrix {
    private List<List<Integer>> mat;

    public Matrix(List<List<Integer>> mat) {
        this.mat = mat;
    }

    public List<List<Integer>> getMatrix() {
        return mat;
    }

    public List<List<Integer>> transpose() {
        if (mat!= null) {
            try {
                List<List<Integer>> transposedMatrix = new ArrayList<>();
                for (int i = 0; i < mat.get(0).size(); i++) {
                    List<Integer> column = new ArrayList<>();
                    for (List<Integer> row : mat) {
                        column.add(row.get(i));
                    }
                    transposedMatrix.add(column);
                }
                return transposedMatrix;
            } catch (Exception e) {
                return List.of("Failed to convert transpose because", e.getMessage());
            }
        } else {
            return null;
        }
    }