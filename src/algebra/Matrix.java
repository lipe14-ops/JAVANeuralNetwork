package algebra;

public class Matrix {
  public int rows, cols;
  public double values[][];

  public Matrix(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.values = new double[this.rows][this.cols];

    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.cols ; ++j) {
        this.setValue(i, j, 0);
      }
    }
  }

  public void setValue(int i, int j, double v) {
    this.values[i][j] = v;
  }

  public double getValue(int i, int j) {
    return this.values[i][j];
  }

  public Matrix multiplyByMatrix(Matrix matrix) {
    assert this.cols != matrix.rows : "Matrises cannot multiply.";
    Matrix tmpMatrix = new Matrix(this.rows, matrix.cols);

    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < matrix.cols; ++j) {
        for (int k = 0; k < this.cols; ++k) {
          double value = tmpMatrix.getValue(i, j) + this.getValue(i, k) * matrix.getValue(k, j);
          tmpMatrix.setValue(i, j, value);
        }
      }
    }
    
    return tmpMatrix;
  }
}

