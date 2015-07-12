package test;

import java.io.File;
import java.io.IOException;

import com.ansj.vec.Learn;
import com.ansj.vec.Word2VEC;

public class Word2VecTest {

	public static void main(String[] args) throws IOException {

		File result = new File("file//amazon_docs.txt");

		Learn lean = new Learn();

		lean.learnFile(result);

		lean.saveModel(new File("model//amazon_vector.mod"));

		Word2VEC w2v = new Word2VEC();

		w2v.loadJavaModel("model//amazon_vector.mod");

		float[] vector = w2v.getWordVector("windows");

		for (float d : vector) {

			System.out.println(d);
		}

		System.out.println(w2v.distance("windows"));

		System.out.println(w2v.analogy("microsoft", "windows", "apple"));

	}

}
