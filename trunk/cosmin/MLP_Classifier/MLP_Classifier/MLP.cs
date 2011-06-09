using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AForge.Neuro;
using AForge.Neuro.Learning;
using Accord.Statistics.Analysis;
using System.IO;

namespace MLP_Classifier
{
    class MLP
    {
        public ActivationNetwork            NeuralNet;
        public BackPropagationLearning      Teacher;
        public int                          TagCount;
        public int                          featureSize;
        public PCA                          pca;
        public static double                EPSILON = 0.1;
        public static int                   MAX_ITER = 1000;


        public MLP (int height, int width, int[] neuronCount)
        {
            featureSize = height * width;
            NeuralNet = new ActivationNetwork(new BipolarSigmoidFunction(2), PCA.PCA_COMPONENTS /* height * width */, neuronCount);
            NeuralNet.Randomize();
            Teacher = new BackPropagationLearning(NeuralNet);
            TagCount = neuronCount.Last();
        }


        public MLP (String fileName, String trainingDir)
        {
            loadFromFile(fileName);

            string[] filePaths = Directory.GetFiles(trainingDir, "*.bmp", SearchOption.AllDirectories);
            int nTests = filePaths.Length;
            double[][] input = new double[nTests][];

            for (int i = 0; i < nTests; i++)
            {
                Feature feat = new Feature(filePaths[i], false);
                input[i] = feat.Pict;
            }

            this.pca = new PCA(input);
        }


        private void loadFromFile(String fileName)
        {
            NeuralNet = (ActivationNetwork)ActivationNetwork.Load(fileName);
            TagCount = NeuralNet.Output.Length; 
        }


        public void learn(String trainingDir, int tag)
        {
            string[] filePaths = Directory.GetFiles(trainingDir, "*.bmp", SearchOption.AllDirectories);
            int         nTests = filePaths.Length;
            double[][]  output = new double[nTests][];
            double[][]  input = new double[nTests][];
            double[][]  inputPCA;

            for (int i = 0; i < nTests; i++)
            {
                Feature feat = new Feature(filePaths[i], true);
                input[i] = feat.Pict;
                output[i] = feat.ConvertToTagArray(TagCount, tag);
            }

            this.pca = new PCA(input);
            inputPCA = pca.Transform(input);

            int iter = 0;
            double err ;
            double error = 0;

            do
            {
                err = error;
                error = Teacher.RunEpoch(inputPCA, output);
                System.Console.WriteLine("error:" + error);
                iter++;
            } while ((iter < MAX_ITER) && (Math.Abs(err - error) != 0) && (error > EPSILON));
        }


        public void save (String file)
        {
            NeuralNet.Save(file);
        }


        public double[] compute(double[] pict)
        {
            return NeuralNet.Compute(pict);
        }
    }
}
