public class Main {

    public static void main(String[] args){

        NeuralNetwork net;
        net = new NeuralNetwork(1, 3, 3, 1);

        float[][] input;
        float[][] output;
        float[] test;
        float accuracy;
        Perceptron p;
        p = new Perceptron(5);
        accuracy = 0;
        test = new float[1];
        test[0] = 6f;
        input = new float[360][1];
        output = new float[360][1];

        for (int n = 0; n < 360; n++){
            input[n][0] = Perceptron.sigmoid(n);
            output[n][0] = Perceptron.sigmoid(n);
        }

        for (int epoch = 0; epoch < 100; epoch++){
            for (int n = 0; n < 360; n++){
                net.train(output[n], input[n], 0.05f);
            }
        }
        float z = net.neuralNetworkExits(test)[0];
        System.out.println(invertSigmo(invertSigmo(z)));

    }

    public static float invertSigmo(float n){
        float inverted;
        inverted = (float)Math.log(n/(1-n));
        return inverted;
    }

}
