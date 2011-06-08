using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AForge.Neuro;
using System.IO;
namespace MLPClassifier
{
    class MainClass
    {
        static int  NR_POS = 4;
        static void Main(string[] args)
        {

            bool learn = true;
            String[] posStr = {"falling", "lying", "sitting", "standing"};
            MLP[] mlp = new MLP[NR_POS];
            if (learn == true)
            {
                for (int i = 3; i < NR_POS; i++)
                {

                    mlp[i] = new MLP(30, 30, new int[] {150, 10, 1});
                    System.Console.WriteLine("init network " + i);
                    mlp[i].learn(@"e:\eclipse workspace\FeatGen\images\SMALLTEST", i);
                    mlp[i].save("neuralnet_"+i+".txt");
                }
                System.Console.WriteLine("done...");
                System.Console.Read();
                
            }
             else
            {
            
                for (int  i = 3; i < NR_POS; i++)
                {
                    mlp[i] = new MLP("neuralnet_"+i+".txt");
                    System.Console.WriteLine("NN loaded "+i);

                }
         
                string[] filePaths = Directory.GetFiles(Directory.GetCurrentDirectory(), "*.bmp", SearchOption.AllDirectories);
                for (int k = 0; k < filePaths.Length; k++)
                {
                    Feature image = new Feature(filePaths[k], false);
                    System.Console.WriteLine(filePaths[k]);
                    for (int i = 3; i < NR_POS; i++)
                    {
                        double[] rez = mlp[i].compute(image.Pict);
                        System.Console.WriteLine("REZULTAT " + i);
                        for (int j = 0; j < rez.Length; j++)
                            System.Console.WriteLine(rez[j].ToString() + " ");
                    }
                }
                
                
                /*
                 * XOR test
                 * 
                 * double[] rez = mlp[3].compute(new double[]{0,0});
                System.Console.WriteLine("0 0 ");
                for (int j = 0; j < rez.Length; j++)
                    System.Console.WriteLine(rez[j].ToString() + " ");

                 rez = mlp[3].compute(new double[] { 0, 1 });
                System.Console.WriteLine("0 1 " );
                for (int j = 0; j < rez.Length; j++)
                    System.Console.WriteLine(rez[j].ToString() + " ");

                 rez = mlp[3].compute(new double[] { 1, 0 });
                System.Console.WriteLine("1 0 " );
                for (int j = 0; j < rez.Length; j++)
                    System.Console.WriteLine(rez[j].ToString() + " ");

                 rez = mlp[3].compute(new double[] { 1, 1 });
                System.Console.WriteLine("1 1 " );
                for (int j = 0; j < rez.Length; j++)
                    System.Console.WriteLine(rez[j].ToString() + " ");
                System.Console.WriteLine("done...");
                System.Console.Read();*/
                
             }
            
        }
    }
}
