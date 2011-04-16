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
            MLP mlp = new MLP(200, 200, new int[] { 1000, 100, 4 });
            System.Console.WriteLine("init network");
            mlp.learn(@"e:\Poli\Master\Proiect cercetare\code\trunk\training_set\");
        }
    }
}
