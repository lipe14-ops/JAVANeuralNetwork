package algebra;

import algebra.Matrix;
import java.util.Random;

public class RandomMatrix extends Matrix {

  public RandomMatrix(int rows, int cols) {
    super(rows, cols);
    this.randomizeMatrix();
  }

  public void randomizeMatrix() {
    Random randomNumberGen = new Random();
    
    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.cols ; ++j) {
        this.setValue(i, j, 
          randomNumberGen.nextDouble(2) - 1
        );
      }
    }
  }
}

