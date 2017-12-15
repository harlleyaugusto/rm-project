package ufmg.dcc.rm.rankaggreg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import ufmg.dcc.rm.experiments.RunExperiment;
import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.util.SortUtil;

public class BordaCount extends RankingAggregation {

	protected FileParserContext fpc;

	public BordaCount() {
		super();
		fpc = new FileParserContext(new DataReaderFromView());
		name = "BordaCount";
	}

	@Override
	protected void before() throws FileNotFoundException {
		forum = fpc.parse();
		Question.sortingAllAnswerPerView(forum);
	}

	@Override
	protected void sorting() {
		// TODO Auto-generated method stub
		int count = 1;
		for (Integer qid : forum.keySet()) {
			System.out.println(
					"******" + count++ + "/" + forum.size() + "****** # answer: " + forum.get(qid).getAnswers().size());
			optimalRanking.put(qid, new ArrayList(bc(forum.get(qid).getRankingView()).keySet()));
		}

	}

	private HashMap<Integer, Integer> bc(HashMap<String, HashMap<Integer, Double>> rankingView) {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> count = new HashMap<>();
		int point;
		for (String view : rankingView.keySet()) {
			if (rankingView.get(view).size() > 10)
				point = 10;
			else
				point = rankingView.get(view).size();

			for (Integer aid : rankingView.get(view).keySet()) {
				if (point > 0) {
					if (count.containsKey(aid))
						count.put(aid, count.get(aid) + point);
					else
						count.put(aid, point);
					point--;
				}
			}
		}
		count = (HashMap<Integer, Integer>) SortUtil.sortByValue(count);
		return count;
	}

	@Override
	protected void after() throws IOException {
		// TODO Auto-generated method stub
		RunExperiment re = new RunExperiment(this);
		re.runNDCG();
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {
		BordaCount BC = new BordaCount();
		BC.run();
		
	}

}
