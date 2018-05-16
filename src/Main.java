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
        test[0] = (float)(5);
        input = new float[360][1];
        output = new float[360][1];

        for (int n = 0; n < 360; n++){
            input[n][0] = n;
            output[n][0] = input[n][0];
        }
        float z = 0;

        for (int epoch = 0; epoch < 1000; epoch++) {
            for (int n = 0; n < 360; n++) {
                net.train(output[n], input[n], 0.005f);
               z = net.neuralNetworkExits(test)[0];
            }
        }
        System.out.println(z);

    }

}
