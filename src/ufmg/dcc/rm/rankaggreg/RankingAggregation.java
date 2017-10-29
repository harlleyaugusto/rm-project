package ufmg.dcc.rm.rankaggreg;

import java.io.FileNotFoundException;
import java.util.HashMap;

import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Question;

public abstract class RankingAggregation {
	
	
	protected HashMap<Integer, Question> forum;
	protected FileParserContext fpc; //
	
	protected abstract void before() throws FileNotFoundException;
	protected abstract void sorting();
	protected abstract void after();
	
	public RankingAggregation()
	{
		forum = new HashMap<Integer, Question>();
		fpc = new FileParserContext(new DataReaderFromView());
	}
	
	protected void run() throws FileNotFoundException
	{
		this.before();
		this.sorting();
		this.after();
	}
	
	

	
}
