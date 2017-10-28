package ufmg.dcc.rm.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.util.conf.ViewConfiguration;

public class DataReaderFromView implements FileParserStrategy {

	private String path;
	ArrayList<String> files;


	public DataReaderFromView(String path, ArrayList<String> files) {
		super();
		this.path = path;
		this.files = files;

	}

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

		ViewConfiguration conf = ViewConfiguration.getInstance();
		
		for (int i = 0; i < files.size(); i++) {

			in = new File(path + files.get(i));
			scanner = new Scanner(in);
			String view = files.get(i).split("_")[2];

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

					} else {
						if (questions.get(qid).getAnswers().containsKey(aid)) {
							questions.get(qid).getAnswer(aid).setPredictedView(view, predictedViewScore);
							questions.get(qid).setPredictedPerViewForEachAnswer(view, aid, predictedViewScore);
						} else {
							questions.get(qid).setAnswer(ans);
							questions.get(qid).setPredictedPerViewForEachAnswer(view, aid, predictedViewScore);
						}
					}
				}
			} finally {
				scanner.close();
			}

		}

		return questions;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> files = new ArrayList<String>();

		files.add("stack_multiview_user_results_stack.txt");
		files.add("stack_multiview_usergraph_results_stack.txt");
		files.add("stack_multiview_style_results_stack.txt");
		files.add("stack_multiview_structure_results_stack.txt");
		files.add("stack_multiview_relevance_results_stack.txt");
		files.add("stack_multiview_read_results_stack.txt");
		files.add("stack_multiview_length_results_stack.txt");
		files.add("stack_multiview_history_results_stack.txt");

		DataReaderFromView data = new DataReaderFromView(
				"/home/harlley/Projects/rm-project/data/experiments_results_qa/", files);
		HashMap<Integer, Question> questions = data.parse();
		int totalAns = 0;

		Answer ans = Question.getAnswer(questions, 3129290);
		
		for (String view : ans.getPredictedView().keySet())
			System.out.println("Question: " + ans.getQid() + " Fold: "+ ans.getFold() + " targetResult: " + ans.getTargetResult() +" View: " + view + " score: " + ans.getPredictedView().get(view));
		
		System.out.println("total: " + totalAns);

	}

}
