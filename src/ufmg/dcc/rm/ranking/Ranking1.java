package ufmg.dcc.rm.ranking;

import ufmg.dcc.rm.parse.*;
import ufmg.dcc.rm.qa.*;
import ufmg.dcc.rm.util.SortHashMapByValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Ranking1 {

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

		FileParserContext fpc = new FileParserContext(new DataReaderFromView());

		HashMap<Integer, Question> questions = fpc.parse();

		Answer ans = Question.getAnswer(questions, 2833789);

		HashMap<String, Double> pv = ans.getPredictedView();

		pv = (HashMap<String, Double>) SortHashMapByValues.sortByValue(pv);

		for (String view : pv.keySet())
			System.out.println("Question: " + ans.getQid() + " Fold: " + ans.getFold() + " targetResult: "
					+ ans.getTargetResult() + " View: " + view + " score: " + ans.getPredictedView().get(view));

		System.out.println("Questions size:" + questions.size());

		questions.get(458721).sortingAnswerPerView();
		questions.get(458721).rankingByTarget();
		Question.sortingAllAnswer(questions);

		/*
		 * Process shell = null; shell = Runtime.getRuntime().exec(
		 * "Rscript /home/harlley/Projects/rm-project/rankingScript.R");
		 * 
		 * BufferedReader reader = null; reader = new BufferedReader(new
		 * InputStreamReader(shell.getInputStream())); String line; while ((line
		 * = reader.readLine()) != null) { System.out.println(line);
		 * 
		 * }
		 */
	}
}
