package ufmg.dcc.rm.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.util.conf.ConfigurationView;

public class DataReaderFromView implements FileParserStrategy {

	@Override
	public HashMap<Integer, Question> parse() throws FileNotFoundException {

		File in = null;
		Scanner scanner = null;
		double targetResult;
		Answer ans;
		int qid, aid, fold;
		double predictedViewScore;
		HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
		String q = null;
		HashMap<String, Double> predictionView;

		ConfigurationView conf = ConfigurationView.getInstance();

		for (int i = 0; i < conf.files_views.length; i++) {

			in = new File(conf.path_files + conf.files_views[i]);
			scanner = new Scanner(in);
			String view = conf.files_views[i].split("_")[2];

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

					predictedViewScore = Double.parseDouble(splits[3]);

	
					predictionView = new HashMap<String, Double>();
					predictionView.put(view, predictedViewScore);
					targetResult = Double.parseDouble(splits[4]);

					ans = new Answer(aid, qid, targetResult, fold, predictionView);

					if (!questions.containsKey(qid)) {
						Question quest = new Question(qid, ans);
						questions.put(qid, quest);
						questions.get(qid).setPredictedPerViewForEachAnswer(view, aid, predictedViewScore);

					} else {
						if (questions.get(qid).getAnswers().containsKey(aid)) {
							questions.get(qid).getAnswer(aid).setPredictedView(view, predictedViewScore);
							questions.get(qid).setPredictedPerViewForEachAnswer(view, aid, predictedViewScore);
						} else {
							questions.get(qid).setAnswer(ans);
							questions.get(qid).setPredictedPerViewForEachAnswer(view, aid, predictedViewScore);
						}
					}
					//questions.get(qid).setPredictedPerViewForEachAnswer(view, aid, predictedViewScore);
				}
			} finally {
				scanner.close();
			}

		}

		return questions;
	}

	
}
