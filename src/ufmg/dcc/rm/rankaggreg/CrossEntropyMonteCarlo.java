package ufmg.dcc.rm.rankaggreg;

import java.io.FileNotFoundException;
import java.util.HashMap;

import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;

public class CrossEntropyMonteCarlo extends RankingAggregation {

	public CrossEntropyMonteCarlo() {
		super();
	}

	@Override
	public void before() throws FileNotFoundException {
		forum = fpc.parse();
		Question.sortingAllAnswer(forum);
	}

	@Override
	public void sorting() {
		// TODO Auto-generated method stub
		for (Integer qid : forum.keySet()) {
			for (String view : forum.get(qid).getRankingView().keySet()) {
				
			}
		}
	}

	@Override
	protected void after() {
		// TODO Auto-generated method stub

	}

	/*
	 * public static void main(String[] args) throws FileNotFoundException { //
	 * TODO Auto-generated method stub
	 * 
	 * FileParserContext fpc = new FileParserContext(new DataReaderFromView());
	 * HashMap<Integer, Question> questions = fpc.parse(); }
	 */

}
