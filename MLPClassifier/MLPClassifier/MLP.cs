using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AForge.Neuro;
using AForge.Neuro.Learning;
using System.IO;

namespace MLPClassifier
{
    class MLP
    {
        public ActivationNetwork NeuralNet;
        public BackPropagationLearning Teacher;
        public int TagCount;
        static double EPSILON = 0.000001;
        public MLP (int height, int width, int[] neuronCount)
        {
            NeuralNet = new ActivationNetwork(new SigmoidFunction(2), height * width, neuronCount[0], neuronCount[1], neuronCount[2]); //new ActivationNetwork(new SigmoidFunction(2), height * width, neuronCount);
            NeuralNet.Randomize();
            Teacher = new BackPropagationLearning(NeuralNet);
            TagCount = neuronCount.Last();
        }

        public MLP (String fileName)
        {
            loadFromFile(fileName);
        }
        private void loadFromFile(String fileName)
        {
            NeuralNet = (ActivationNetwork)ActivationNetwork.Load(fileName);
            TagCount = NeuralNet.Output.Length; 
        }

        public void learn(String inputDir, int tag)
        {

            string[] filePaths = Directory.GetFiles(inputDir, "*.bmp", SearchOption.AllDirectories);
            double[][] output = new double[1000*filePaths.Length][];
            double[][] input = new double[1000*filePaths.Length][];
            for (int i = 0; i < 500*filePaths.Length; i++)
            {
                Feature feat = new Feature(filePaths[0], true);
                input[i] = feat.Pict;
                output[i] = feat.ConvertToTagArray(TagCount, tag);
                if (i % 512 == 0)
                    System.Console.WriteLine("position" + i / 512);
            }
            for (int i = 500*filePaths.Length; i < 1000 * filePaths.Length; i++)
            {
                Feature feat = new Feature(filePaths[1], true);
                input[i] = feat.Pict;
                output[i] = feat.ConvertToTagArray(TagCount, tag);
                if (i % 512 == 0)
                    System.Console.WriteLine("position" + i / 512);
            }
            int iter = 0;
            double err ;
            double error = 0;
            do
            {
                err = error;
                error = Teacher.RunEpoch(input, output);
                System.Console.WriteLine("error:" + error);
                iter++;

            } while (iter < 4000 && Math.Abs(err-error) != 0  && error > 0.5);
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
