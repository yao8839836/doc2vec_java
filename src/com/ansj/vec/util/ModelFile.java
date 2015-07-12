package com.ansj.vec.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ModelFile {

	/**
	 * save the trained model to Disk
	 * 
	 * @param model
	 *            -the model to be saved
	 * @param modelname
	 *            -file name
	 */
	public static void SaveModel(Object model, String modelname) {
		try {

			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("model//" + modelname));
			oos.writeObject(model);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * load the model from disk
	 * 
	 * @param file
	 *            -the model filename
	 * @return-the trained classifier
	 */
	public static Object LoadModel(String file) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			Object classifier = ois.readObject();
			ois.close();
			return classifier;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将词向量矩阵写入Weka的Arff文件
	 *
	 * @param file
	 * @param matrix
	 * @throws IOException
	 */
	public static void writeFile(String file, float[][] matrix)
			throws IOException {

		File result = new File(file);
		OutputStream out = new FileOutputStream(result, false);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out,
				"utf-8"));

		bw.write("@relation word_embedding\n\n");

		int feature_size = matrix[0].length;

		for (int i = 0; i < feature_size; i++) {
			bw.write("@attribute dimension_" + i + " real\n");
		}

		bw.newLine();

		bw.write("@data\n");

		for (int n = 0; n < matrix.length; n++) {
			for (int i = 0; i < feature_size - 1; i++) {

				bw.write(matrix[n][i] + ",");

			}

			bw.write(matrix[n][feature_size - 1] + "\n");
		}

		bw.close();
		out.close();
	}

}
