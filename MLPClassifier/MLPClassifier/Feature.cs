using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
namespace MLPClassifier
{
    class Feature
    {
        
        public double[] Pict;
        public int Tag;
        public static int Width;
        public static int Height;

        public Feature(String imageFile, bool tagged)
        {
            ReadPicture(imageFile);
            if (tagged == true)
            ReadTag(imageFile);
        }
        public double[] ConvertToTagArray(int length, int tag)
        {
            double[] tagSet = new double[length];
            /*for (int i = 0; i < length; i++)
                tagSet[i] = 0;
            */
            tagSet[0] = (Tag == tag ? 1 : 0);

            return tagSet;

        }
        private bool IsSameColor(Color first, Color second)
        {
            if (first.A.Equals(second.A)
            && first.R.Equals(second.R)
            && first.G.Equals(second.G)
            && first.B.Equals(second.B))
                return true;
            else
                return false;
        }
        private void ReadPicture (String imageFile)
        {
            Bitmap bmp = new Bitmap(imageFile);
            Pict = new double[bmp.Height * bmp.Width];
   
            for (int i = 0; i < bmp.Height; i++)
            {
                for (int j = 0; j < bmp.Width; j++)
                {
                    Pict[i * bmp.Width + j] = 0;
                    Color a = bmp.GetPixel(i, j);
                    if (IsSameColor( bmp.GetPixel(i, j),(Color.Black)) == false)
                    {
                        Pict[i * bmp.Width + j] = 1;
                    }
                }
            }
        }
        private void ReadTag (String imageFile)
        {
            Tag = Convert.ToInt32(((imageFile.Split(".".ToCharArray()))[0].ToString().Split("_".ToCharArray()))[1]);
            //System.Console.WriteLine(((imageFile.Split(".".ToCharArray()))[0].ToString().Split("_".ToCharArray()))[1]);
        }
    }
}
