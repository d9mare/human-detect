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
        static double EPSILON = 0.1;
        public MLP (int height, int width, int[] neuronCount)
        {
            NeuralNet = new ActivationNetwork(new BipolarSigmoidFunction(2), height * width, neuronCount); //new ActivationNetwork(new SigmoidFunction(2), height * width, neuronCount);
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

            Feature f1 = null, f2 = null;
            string[] filePaths = Directory.GetFiles(inputDir, "*.bmp", SearchOption.AllDirectories);
            int nrExamples = 1000 * filePaths.Length;
            double negExFactor = 0.8;
            double[][] output = new double[nrExamples][];
            double[][] input = new double[nrExamples][];
            
            
            Feature f3 = new Feature(filePaths[0], true);
            Feature f4 = new Feature(filePaths[0], true);

            System.Console.WriteLine(Feature.isTheSameFeature(f3, f4));

            Feature f5 = new Feature(filePaths[1], true);
            Feature f6 = new Feature(filePaths[1], true);

            System.Console.WriteLine(Feature.isTheSameFeature(f3, f4));


            System.Console.WriteLine(Feature.isTheSameFeature(f3, f5));

            for (int i = 0; i < (int)(negExFactor * nrExamples); i++)
            {
                Feature feat = new Feature(filePaths[0], true);
                f1 = feat;
                input[i] = feat.Pict;
                output[i] = feat.ConvertToTagArray(TagCount, tag);
                if (i % 512 == 0)
                    System.Console.WriteLine("position" + i / 512);
            }
            for (int i = (int)(negExFactor * nrExamples); i < nrExamples; i++)
            {
                Feature feat = new Feature(filePaths[1], true);
                input[i] = feat.Pict;
                f2 = feat;
                output[i] = feat.ConvertToTagArray(TagCount, tag);
                if (i % 512 == 0)
                    System.Console.WriteLine("position" + i / 512);
            }

            System.Console.WriteLine(Feature.isTheSameFeature(f1, f2));
            /*
             * XOR test
             * 
             * double[][] input = new double[][]{ new double[]{ 0, 0 }, 
                                               new double[]{ 0, 1 }, 
                                               new double[]{ 1, 1 }, 
                                               new double[]{ 1, 0 } };
            double[][] output = new double[][]{ new double[]{ 0 }, 
                                                new double[]{ 1 }, 
                                                new double[]{ 0 }, 
                                                new double[]{ 1 } };*/
            int iter = 0;
            double err ;
            double error = 0;
            do
            {
                err = error;
                error = Teacher.RunEpoch(input, output);
                System.Console.WriteLine("error:" + error);
                iter++;

            } while (iter < 400000 && Math.Abs(err-error) != 0  && error > EPSILON);
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
