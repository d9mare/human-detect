using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AForge.Neuro;
using System.IO;

namespace MLP_Classifier
{
    class MainClass
    {
        static int NR_POS = 4;


        static void Main(string[] args)
        {
            bool        learn = false;
            String[]    posStr = {"falling", "lying", "sitting", "standing"};
            MLP[]       mlp = new MLP[NR_POS];

            if (learn)
            {
                for (int i = 0; i < NR_POS; i++)
                {
                    mlp[i] = new MLP(15, 15, new int[] {20, 5, 1});
                    
                    System.Console.WriteLine("init network " + i);

                    mlp[i].learn(@"D:\[Faculta]\Research\MLP_Classifier\Training_tests\", i);
                    mlp[i].save("neuralnet_"+i+".txt");
                }

                System.Console.WriteLine("done...");
                System.Console.Read();
            }
            else
            {
                
                for (int  i = 0; i < NR_POS; i++)
                {
                    mlp[i] = new MLP("neuralnet_" + i + ".txt", @"D:\[Faculta]\Research\MLP_Classifier\Training_tests\");
                    System.Console.WriteLine("NN loaded " + i);
                }
                

                string[] filePaths = Directory.GetFiles(@"D:\[Faculta]\Research\MLP_Classifier\Guess\", "*.bmp", SearchOption.AllDirectories);

                for (int k = 0; k < filePaths.Length; k++)
                {
                    Feature image = new Feature(filePaths[k], false);
                    System.Console.WriteLine(filePaths[k]);

                    for (int i = 0; i < NR_POS; i++)
                    {
                        double[] rez = mlp[i].compute(mlp[i].pca.Transform(image.Pict));
                        System.Console.Write("Result NN :" + i + ":  ");

                        for (int j = 0; j < rez.Length; j++)
                        {
                            System.Console.WriteLine(rez[j].ToString() + " ");
                        }
                    }
                }       
             }

            System.Console.Read();
        }
    }
}
