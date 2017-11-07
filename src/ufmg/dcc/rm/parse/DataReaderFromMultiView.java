package ufmg.dcc.rm.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.util.conf.ConfigurationMultiView;
import ufmg.dcc.rm.util.conf.ConfigurationView;

public class DataReaderFromMultiView implements FileParserStrategy {

	@Override
	public HashMap<Integer, Question> parse() throws FileNotFoundException {

		File in = null;
		Scanner scanner = null;
		double targetResult;
		Answer ans;
		int qid, aid, fold;
		double globalPrediction;
		HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
		String q = null;

		ConfigurationMultiView conf = ConfigurationMultiView.getInstance();

		for (int i = 0; i < conf.files_views.length; i++) {

			in = new File(conf.path_files + conf.files_views[i]);
			scanner = new Scanner(in);

			try {
				q = scanner.nextLine();
				while (scanner.hasNext()) {
					// fold,id,qid,predictedResult,targetResult
					// 0,458759,458721,0.0,22.0
					q = scanner.nextLine();
					String[] splits = q.split(",");

					fold = Integer.parseInt(splits[0]);
					aid = Integer.parseInt(splits[1]);
					qid = Integer.parseInt(splits[2]);

					globalPrediction = Double.parseDouble(splits[3]);

					targetResult = Double.parseDouble(splits[4]);

					ans = new Answer(aid, qid, targetResult, fold, globalPrediction);

					if (!questions.containsKey(qid)) {
						Question quest = new Question(qid, ans);
						questions.put(qid, quest);
					} else {
						questions.get(qid).setAnswer(ans);

					}
				}
			} finally {
				scanner.close();
			}

		}

		return questions;
	}
}
