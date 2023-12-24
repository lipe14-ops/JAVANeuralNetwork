import algebra.Matrix;
import algebra.RandomMatrix;

public class NeuralNet {
  static double leakyRELUActivation(double x) {
    if (x > 0) return x;
    return x * 0.1;
  }

  public static void main(String[] args) {
    int[] topology = { 2, 7, 70, 1 };
    NeuralModel network = new NeuralModel(topology);

    float entry[] = { 0, 0 };
    Matrix result = network.predict(entry);
    showNeuralNetOutput(result);
    
    entry[0] = 1;
    entry[1] = 0;
    result = network.predict(entry);
    showNeuralNetOutput(result);

    entry[0] = 0;
    entry[1] = 1;
    result = network.predict(entry);
    showNeuralNetOutput(result);

    entry[0] = 1;
    entry[1] = 1;
    result = network.predict(entry);
    showNeuralNetOutput(result);
  }

  static void showNeuralNetOutput(Matrix result) {
    System.out.printf("The output is: { ");
    for (int i = 0; i < result.rows; ++i) {
      System.out.printf("%f", result.getValue(i, 0));

      if (i != result.rows - 1) {
        System.out.printf(", ");
      }
    } 
    System.out.println(" }");
  }
}

class NeuralModel {
  int topology[];

  Matrix layers[];
  RandomMatrix weights[], biases;

  NeuralModel(int[] topology) {
    this.topology = topology;

    this.layers = new Matrix[this.topology.length];
    this.weights = new RandomMatrix[this.topology.length - 1];
    this.biases = new RandomMatrix(this.topology.length - 1, 1);

    this.layers[0] = new Matrix(this.topology[0], 1);

    for (int i = 1; i <= this.topology.length - 1; ++i) {
      this.layers[i] = new Matrix(this.topology[i], 1);
      this.weights[i - 1] = new RandomMatrix(this.topology[i], this.topology[i - 1]);
    }
  }

  Matrix predict(float[] entry) {
    assert entry.length != this.topology.length : "invalid entry.";
    Matrix inputLayer = this.layers[0];

    for (int i = 0; i < entry.length; ++i) {
      inputLayer.setValue(i, 0, entry[i]);
    }

    for (int i = 1; i <= this.topology.length - 1; ++i) {
      Matrix product = this.weights[i - 1].multiplyByMatrix(this.layers[i - 1]);
      double biasValue = this.biases.getValue(i - 1, 0);
      
      for (int row = 0; row < product.rows; ++row) {
        this.layers[i].setValue(row, 0, biasValue);

        for (int col = 0; col < product.cols; ++col) {
          this.layers[i].setValue(row, 0, this.layers[i].getValue(row, 0) + product.getValue(row, col));
        }

        this.layers[i].setValue(row, 0, leakyRELUActivation(this.layers[i].getValue(row, 0)));
      }
    }

    return this.layers[this.topology.length - 1];
  }

  static double leakyRELUActivation(double x) {
    if (x > 0) return x;
    return x * 0.1;
  }
}
