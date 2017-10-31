package ufmg.dcc.rm.rankaggreg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Question;

public abstract class RankingAggregation {

	protected HashMap<Integer, Question> forum;
	protected FileParserContext fpc;
	protected ArrayList<Integer> optimalRanking;

	protected abstract void before() throws FileNotFoundException;

	protected abstract void sorting() throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException;

	protected abstract void after();

	public RankingAggregation() {
		forum = new HashMap<Integer, Question>();
		fpc = new FileParserContext(new DataReaderFromView());
		optimalRanking = new ArrayList<Integer>();
	}

	protected void run() throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {
		this.before();
		this.sorting();
		this.after();
	}

}
