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

	protected String name;
	
	protected String base;

	protected HashMap<Integer, Question> forum;

	protected HashMap<Integer, ArrayList<Integer>> optimalRanking;

	protected abstract void before() throws FileNotFoundException;

	protected abstract void sorting()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException;

	protected abstract void after() throws IOException;

	public RankingAggregation() {
		forum = new HashMap<Integer, Question>();
		optimalRanking = new HashMap<Integer, ArrayList<Integer>>();

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the forum
	 */
	public HashMap<Integer, Question> getForum() {
		return forum;
	}

	/**
	 * @param forum
	 *            the forum to set
	 */
	public void setForum(HashMap<Integer, Question> forum) {
		this.forum = forum;
	}

	/**
	 * @return the optimalRanking
	 */
	public HashMap<Integer, ArrayList<Integer>> getOptimalRanking() {
		return optimalRanking;
	}

	/**
	 * @param optimalRanking
	 *            the optimalRanking to set
	 */
	public void setOptimalRanking(HashMap<Integer, ArrayList<Integer>> optimalRanking) {
		this.optimalRanking = optimalRanking;
	}

	public void run() throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {
		this.before();
		this.sorting();
		this.after();
	}

	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}

}
