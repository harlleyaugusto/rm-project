package dao;

import java.util.HashMap;

public class Answer {
	private int id;
	private int qid;
	private double targetResult;
	private double predictedResultGlobal;
	HashMap<String, Double> predictedView = new HashMap<String, Double>();
	private int fold;
	
	public Answer(int id, double targetResult, int fold, HashMap<String, Double> predictedView) {
		super();
		this.id = id;
		this.targetResult = targetResult;
		this.predictedView = predictedView;
		this.fold = fold;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the targetResult
	 */
	public double getTargetResult() {
		return targetResult;
	}
	/**
	 * @param targetResult the targetResult to set
	 */
	public void setTargetResult(double targetResult) {
		this.targetResult = targetResult;
	}
	/**
	 * @return the predictedResult
	 */
	public double getPredictedResult() {
		return predictedResultGlobal;
	}
	/**
	 * @param predictedResult the predictedResult to set
	 */
	public void setPredictedResult(double predictedResult) {
		this.predictedResultGlobal = predictedResult;
	}
	/**
	 * @return the fold
	 */
	public int getFold() {
		return fold;
	}
	/**
	 * @param fold the fold to set
	 */
	public void setFold(int fold) {
		this.fold = fold;
	}
	
	public void setPredictedView(String view, double score)
	{
		predictedView.put(view, score);
	}
}
