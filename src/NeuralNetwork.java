
public class NeuralNetwork {

    private Layer inputLayer, outputLayer;
    private Layer[] hiddenLayer;
    private int LAYERS;
    private float[] outputExits;
    private float[][] exits;
    private float[] inputExits;
    private float[][] delta;
    private float[] inputDelta;
    private float[] outputDelta;
    private float[][][] weights;
    private float[][] inputWeights;
    private float[][] outputWeights;


    /**
     * Constructor for the Neural Network
     * @param inputNumber is the number of the inputs
     * @param PERC_NUMBER number of perceptron in input and hidden layer
     * @param LAYER_NUMBER number of hiddenLayer;
     * @param OUTPUT_PERC_NUMBER number of perceptron in the outputLayer
     */

    public NeuralNetwork(int inputNumber, int PERC_NUMBER, int LAYER_NUMBER, int OUTPUT_PERC_NUMBER){

        inputLayer = new Layer(PERC_NUMBER, inputNumber);
        inputExits = new float[PERC_NUMBER];
        inputDelta = new float[PERC_NUMBER];
        inputWeights = new float[PERC_NUMBER][inputNumber];
        hiddenLayer = new Layer[LAYER_NUMBER];
        this.LAYERS = LAYER_NUMBER;

        for (int n = 0; n < hiddenLayer.length; n++){
            hiddenLayer[n] = new Layer(PERC_NUMBER, PERC_NUMBER);
        }

        exits = new float[LAYER_NUMBER][PERC_NUMBER];
        outputLayer = new Layer(OUTPUT_PERC_NUMBER, PERC_NUMBER);
        outputDelta = new float[OUTPUT_PERC_NUMBER];
        outputWeights = new float[OUTPUT_PERC_NUMBER][PERC_NUMBER];
        outputExits = new float[OUTPUT_PERC_NUMBER];
        delta = new float[LAYER_NUMBER][PERC_NUMBER];
        weights = new float[LAYER_NUMBER][PERC_NUMBER][PERC_NUMBER];

    }

    /**
     * With this function you set the exits for the complete neural network
     * @param input is an array which contains the set of input
     */

    public float[] neuralNetworkExits(float input[]){

        //Set the exits for the input layer
        for (int n = 0; n < inputLayer.getPercNumber(); n++){
            inputExits[n] = inputLayer.getPerceptron(n).getExit(input);
            inputDelta[n] = inputLayer.getPerceptron(n).getDelta();
            inputWeights[n] = inputLayer.getPerceptron(n).getWeights();
        }

        for (int n = 0; n < hiddenLayer[0].getPercNumber(); n++){
            exits[0][n] = hiddenLayer[0].getPerceptron(n).getExit(inputExits);
            delta[0][n] = hiddenLayer[0].getPerceptron(n).getDelta();
            weights[0][n] = hiddenLayer[0].getPerceptron(n).getWeights();
        }

        //Set the exits for the hidden layers
        for (int l = 1; l < this.LAYERS ; l++){
            for (int n = 0; n < hiddenLayer[l].getPercNumber(); n++){
                exits[l][n] = hiddenLayer[l].getPerceptron(n).getExit(exits[l-1]);
                delta[l][n] = hiddenLayer[l].getPerceptron(n).getDelta();
                weights[l][n] = hiddenLayer[l].getPerceptron(n).getWeights();
            }
        }

        //Set the exits for the output layer
        for (int n = 0; n < outputLayer.getPercNumber(); n++){
            outputExits[n] = outputLayer.getPerceptron(n).getExit(exits[hiddenLayer.length-1]);
            outputDelta[n] = outputLayer.getPerceptron(n).getDelta();
            outputWeights[n] = outputLayer.getPerceptron(n).getWeights();
        }

        return outputExits;
    }

    /**
     * This method is the main method of the neural network, is used to train the perceptrons
     * @param expected is an array that contains the expected values
     * @param input is an array that contains the input values
     */

    public void train(float[] expected, float[] input, float LEARNING_RATE){

        neuralNetworkExits(input);

        for (int n = 0; n < outputLayer.getPercNumber(); n++){
            outputLayer.getPerceptron(n).outputTraining(outputLayer.getPerceptron(n).sigmoid(expected[n]), exits[LAYERS-1], LEARNING_RATE);
        }

        for (int n = 0; n < hiddenLayer[LAYERS-1].getPercNumber(); n++) {
            hiddenLayer[LAYERS-1].getPerceptron(n).hiddenTraining(outputLayer.getPercNumber(), n, exits[LAYERS-2], outputDelta, outputWeights, LEARNING_RATE);
        }

        for (int l = LAYERS-2; l > 0; l--){
            for (int n = 0; n < hiddenLayer[l].getPercNumber(); n++){
                hiddenLayer[l].getPerceptron(n).hiddenTraining(hiddenLayer[l+1].getPercNumber(), n, exits[l-1], delta[l+1], weights[l+1], LEARNING_RATE);
            }
        }

        for (int n = 0; n < hiddenLayer[0].getPercNumber(); n++) {
            hiddenLayer[0].getPerceptron(n).hiddenTraining(hiddenLayer[1].getPercNumber(), n, inputExits, delta[1], weights[1], LEARNING_RATE);
        }

        for (int n = 0; n < inputLayer.getPercNumber(); n++) {
            inputLayer.getPerceptron(n).hiddenTraining(hiddenLayer[0].getPercNumber(), n, inputExits, delta[0], weights[0], LEARNING_RATE);
        }

    }

}
