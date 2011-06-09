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
    class PCA
    {
        public PrincipalComponentAnalysis   pca;
        public double[][]                   baseMatrix;
        public static int                   PCA_COMPONENTS = 5;


        public PCA(double[][] matrix)
        {
            double [,] matrixPCA = new double[matrix.Length, matrix[0].Length];

            for (int i = 0; i < matrix.Length; i++)
            {
                for (int j = 0; j < matrix[0].Length; j++)
                {
                    matrixPCA[i, j] = matrix[i][j];
                }
            }

            // Creates the Principal Component Analysis of the given source
            pca = new PrincipalComponentAnalysis(matrixPCA, AnalysisMethod.Center);

            // Compute the Principal Component Analysis
            pca.Compute();

            baseMatrix = new double[matrix.Length][];

            for (int i = 0; i < matrix.Length; i++)
            {
                baseMatrix[i] = new double[matrix[0].Length];

                for (int j = 0; j < matrix[0].Length; j++)
                {
                    baseMatrix[i][j] = matrix[i][j];
                }
            }
        }


        // projects the matrix
        public double[][] Transform(double[][] matrix)
        {
            double[,]   matrixPCA = new double[matrix.Length, matrix[0].Length];
            double[,]   components;
            double[][]  result = new double[matrix.Length][]; 
 
            for (int i = 0; i < matrix.Length; i++)
            {
                for (int j = 0; j < matrix[0].Length; j++)
                {
                    matrixPCA[i, j] = matrix[i][j];
                }
            }

            components = pca.Transform(matrixPCA, PCA_COMPONENTS);

            for (int i = 0; i < matrix.Length; i++)
            {
                result[i] = new double[PCA_COMPONENTS];

                for (int j = 0; j < PCA_COMPONENTS; j++)
                {
                    result[i][j] = components[i, j];
                }
            }

            return result;
        }


        // projects the vector
        public double[] Transform(double[] vect)
        {
            double[,] matrixPCA = new double[1, vect.Length];
            double[,] components;
            double[]  result = new double[PCA_COMPONENTS];

            for (int i = 0; i < vect.Length; i++)
            {
                matrixPCA[0, i] = vect[i];
            }

            components = pca.Transform(matrixPCA, PCA_COMPONENTS);

            for (int i = 0; i < PCA_COMPONENTS; i++)
            {
                result[i] = components[0, i];
            }

            return result;
        }
    }
}
