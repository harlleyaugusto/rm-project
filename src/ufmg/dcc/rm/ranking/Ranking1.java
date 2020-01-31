package ufmg.dcc.rm.ranking; 

import ufmg.dcc.rm.parse.*;
import ufmg.dcc.rm.qa.*;
import ufmg.dcc.rm.util.SortUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Ranking1 {

	public static void main(String[] args) throws IOException {

		FileParserContext fpc = new FileParserContext(new DataReaderFromView());

		HashMap<Integer, Question> questions = fpc.parse();

		Answer ans = Question.getAnswer(questions, 2833789);

		HashMap<String, Double> pv = ans.getPredictedView();

		pv = (HashMap<String, Double>) SortUtil.sortByValue(pv);

		for (String view : pv.keySet())
			System.out.println("Question: " + ans.getQid() + " Fold: " + ans.getFold() + " targetResult: "
					+ ans.getTargetResult() + " View: " + view + " score: " + ans.getPredictedView().get(view));

		System.out.println("Questions size:" + questions.size());

		questions.get(458721).sortingAnswerPerView();
		questions.get(458721).sortingByTarget();
		Question.sortingAllAnswerPerView(questions);

		Process shell = null;
		shell = Runtime.getRuntime().exec("Rscript /home/harlley/Projects/rm-project/rankingScript.R");

		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);

		}

	}
}
