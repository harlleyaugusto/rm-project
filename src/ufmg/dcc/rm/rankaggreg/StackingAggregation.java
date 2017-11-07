package ufmg.dcc.rm.rankaggreg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ufmg.dcc.rm.experiments.RunExperiment;
import ufmg.dcc.rm.parse.DataReaderFromMultiView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Question;

public class StackingAggregation extends RankingAggregation {

	PrintWriter writer; //
	protected FileParserContext fpc;

	public StackingAggregation() {
		// TODO Auto-generated constructor stub
		super();
		fpc = new FileParserContext(new DataReaderFromMultiView());
	}

	@Override
	protected void before() throws FileNotFoundException {
		// TODO Auto-generated method stub
		forum = fpc.parse();

	/*	for (Integer qid : forum.keySet()) {
			for (Integer aid : forum.get(qid).getAnswers().keySet())
				System.out.println(forum.get(qid).getAnswers().get(aid).getId() + "," + qid + "," + forum.get(qid).getAnswers().get(aid).getPredictedResultGlobal() + "," + forum.get(qid).getAnswers().get(aid).getTargetResult());
		}

		int a = 10;*/
	}

	@Override
	protected void sorting()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		Question.sortingAllQuestionsByGlobalScore(forum);
		
		for (Integer qid : forum.keySet()) {
			ArrayList<Integer> ranking = new ArrayList<Integer>();
			
			for(Integer aid:forum.get(qid).getRankingGlobalScore().keySet())
			{
				ranking.add(aid);
			}
		
			optimalRanking.put(qid, ranking);
		}	
	}

	@Override
	protected void after() {
		// TODO Auto-generated method stub
		RunExperiment re = new RunExperiment(this);
		re.runNDCG();
	}

	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {

		StackingAggregation CE = new StackingAggregation();

		CE.run();

		// System.out.println(CE.forum.get(3719226).getAnswer(3719278).getPredictedView().get("user"));
		// System.out.println(CE.forum.get(3719226).getRankingView().get("user").get(3719278));
	}

}
