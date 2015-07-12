package test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.ansj.vec.Learn;
import com.ansj.vec.LearnDocVec;
import com.ansj.vec.Word2VEC;
import com.ansj.vec.domain.Neuron;
import com.ansj.vec.util.ReadWriteFile;

public class Doc2VecTest {

	public static void main(String[] args) throws IOException {

		File result = new File("file//clinicalcases.txt");

		Learn learn = new Learn();

		// 训练词向量

		learn.learnFile(result);

		learn.saveModel(new File("model//clinical.mod"));

		Word2VEC w2v = new Word2VEC();

		w2v.loadJavaModel("model//clinical.mod");

		System.out.println(w2v.distance("麻黄"));

		// 得到训练完的词向量，训练文本向量

		Map<String, Neuron> word2vec_model = learn.getWord2VecModel();

		LearnDocVec learn_doc = new LearnDocVec(word2vec_model);

		learn_doc.learnFile(result);

		// 文本向量写文件

		Map<Integer, float[]> doc_vec = learn_doc.getDocVector();

		StringBuilder sb = new StringBuilder("7037 200\n");

		for (int doc_no : doc_vec.keySet()) {

			StringBuilder doc = new StringBuilder("sent_" + doc_no + " ");

			float[] vector = doc_vec.get(doc_no);

			for (float e : vector) {

				doc.append(e + " ");
			}
			sb.append(doc.toString().trim() + "\n");

		}
		ReadWriteFile.writeFile("file//clinical_doc_200_java.vec",
				sb.toString());

	}

}
