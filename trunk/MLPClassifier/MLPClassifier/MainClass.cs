using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AForge.Neuro;

namespace MLPClassifier
{
    class MainClass
    {
        static void Main(string[] args)
        {

            bool learn = true;
            if (learn == true)
            {
                MLP mlp = new MLP(20, 20, new int[] { 50, 10, 4 });
                System.Console.WriteLine("init network");
                mlp.learn(@"e:\eclipse workspace\FeatGen\trainingsetext2");
                mlp.save("neuralnet.txt");
            }
            else
            {
                //MLP mlp = new MLP(20, 20, new int[] { 50, 10, 4 });
                MLP mlp = new MLP("neuralnet.txt");
                System.Console.WriteLine("NN loaded");

                Feature image = new Feature("11_2.bmp", false);
                System.Console.WriteLine("picture loaded");

                double[] rez = mlp.compute(image.Pict);
                System.Console.WriteLine(rez.Length);
                for (int i = 0; i < rez.Length; i++)
                    System.Console.Write(rez[i].ToString() + " ");
            }
            
            
            System.Console.WriteLine("done...");
            System.Console.ReadKey();
        }
    }
}
