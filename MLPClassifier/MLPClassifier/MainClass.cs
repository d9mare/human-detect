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
            /*MLP mlp = new MLP(80, 80, new int[] { 600, 100, 4 });
            System.Console.WriteLine("init network");
            mlp.learn(@"e:\Poli\Master\Proiect cercetare\code\trunk\trainingsetext\");
            mlp.save("neuralnet.txt");*/

            MLP mlp = new MLP("neuralnet.txt");
            System.Console.WriteLine("NN loaded");

            Feature image = new Feature("117_2.bmp", false);
            System.Console.WriteLine("picture loaded");

            double[] rez = mlp.compute(image.Pict);
            System.Console.WriteLine(rez.Length);
            for (int i = 0; i < rez.Length; i++)
                System.Console.Write(rez[i].ToString() + " ");
            
            System.Console.ReadKey();
        }
    }
}
