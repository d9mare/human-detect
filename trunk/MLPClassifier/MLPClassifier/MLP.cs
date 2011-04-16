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
        public MLP (int height, int width, int[] neuronCount)
        {
            NeuralNet = new ActivationNetwork(new SigmoidFunction(2), height * width, neuronCount);
            Teacher = new BackPropagationLearning(NeuralNet);
            TagCount = neuronCount.Last();
        }

        public void loadFromFile(String fileName)
        {

        }

        public void learn(String inputDir)
        {

            string[] filePaths = Directory.GetFiles(@"c:\MyDir\", "*.*", SearchOption.AllDirectories);
            for (int i = 0; i < filePaths.Length; i++)
            {
                Feature feat = new Feature(filePaths[i]);
                double[] output = feat.ConvertToTagArray(TagCount);
                Teacher.Run(feat.Pict, output);
                System.Console.WriteLine(filePaths[i]);
            }
            NeuralNet.Save("neuralnet.txt");
        }
    }
}
