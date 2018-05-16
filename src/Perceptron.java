public class Perceptron {

    private float[] weight;
    private float bias;
    private int WEIGHT_NUMBER;
    private float delta;
    private float exit;

    /**
     * Constructor for the Perceptron class
     * Inizializing of weight and bias
     * @param WEIGHT_NUMBER
     */

    public Perceptron(int WEIGHT_NUMBER){

        this.WEIGHT_NUMBER = WEIGHT_NUMBER;
        weight = new float[WEIGHT_NUMBER];

        for (int cont = 0; cont < WEIGHT_NUMBER; cont++){
            weight[cont] = randomWithRange(-1, 1);
        }

        bias = 1;

    }

    /**
     * This function provides to calculate the internal activation, which is a function like this a = W1*i1 + W2*i2 + ... + Wn*in
     * @param input is an array of value given by the user
     * @return
     */

    private float internalActivation(float[] input) {

        float intActivation = 0;

        for (int cont = 0; cont < WEIGHT_NUMBER; cont++) {
            intActivation += weight[cont] * input[cont];
        }

        intActivation += bias;
        return intActivation;

    }

    /**
     * Calculate the exit of the perceptron
     * @param input
     * @return
     */

    public float getExit(float[] input){

        return internalActivation(input);

    }

    /**
     * This is the function to train the output Layer
     * @param expected is the value expected for this output-perceptron
     * @param input(s) are the exits of the hiddenLayer perceptrons
     * @param LEARNING_RATE
     */

    public void outputTraining(float expected, float[] input, float LEARNING_RATE){

        this.exit = getExit(input);
        float error = expected - exit;
        this.delta = error*LEARNING_RATE;

        for (int cont = 0; cont < WEIGHT_NUMBER; cont++){
            weight[cont] += LEARNING_RATE*delta;
        }

        bias += LEARNING_RATE*delta;

    }

    /**
     * This function is used to train the hidden layers
     * @param perceptronsNumber number of perceptrons in the next layer
     * @param input exits of the layer n-1
     * @param LEARNING_RATE
     * @param delta
     * @param layerWeight
     */

    public void hiddenTraining(int perceptronsNumber, int n, float[] input, float[] delta, float[][] layerWeight, float LEARNING_RATE){

        this.exit = getExit(input);
        float error = 0f;

        for (int i = 0; i < perceptronsNumber; i++){
            error += delta[i] * LEARNING_RATE * layerWeight[i][n];
        }

        this.delta = error*LEARNING_RATE;

        for (int cont = 0; cont < WEIGHT_NUMBER; cont++){
            weight[cont] += LEARNING_RATE*this.delta;
        }

        bias += LEARNING_RATE*this.delta;

    }

    public float getDelta(){
        return this.delta;
    }

    public float[] getWeights(){
        return this.weight;
    }

    /**
     * This is a wrapped function for the standard Math.random used to initialize the weights and the bias
     * @param min
     * @param max
     * @return
     */

    float randomWithRange(int min, int max){

        float range = (max - min) + 1;
        return (float)(Math.random() * range) + min;

    }

}
