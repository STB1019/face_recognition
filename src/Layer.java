public class Layer {

    private Perceptron[] perceptron;

    /**
     * Constructor for InputLayer class
     * @param PERC_NUMB number of perceptron
     * @param inputNumber number of inputs (and also number of weights for all of the perceptrons)
     */

    public Layer(int PERC_NUMB, int inputNumber){

        perceptron = new Perceptron[PERC_NUMB];

        for (int percNumber = 0; percNumber < PERC_NUMB; percNumber++){
            perceptron[percNumber] = new Perceptron(inputNumber);
        }
    }

    /**
     * Used to get percNumber
     * @return number of perceptrons
     */

    public int getPercNumber(){
        return perceptron.length;
    }

    /**
     * Getter for perceptron
     * @param n perceptron in position n
     * @return object of type Perceptron
     */

    public Perceptron getPerceptron(int n){
        return perceptron[n];
    }

}
