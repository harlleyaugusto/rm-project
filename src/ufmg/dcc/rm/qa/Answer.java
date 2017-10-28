package ufmg.dcc.rm.qa;

import java.util.HashMap;

public class Answer {
	private int id;
	private int qid;
	private double targetResult;
	private double globalPrediction;
	HashMap<String, Double> predictionView;// = new HashMap<String, Double>();
	private int fold;
	
	
	public Answer(int id, int qid,double targetResult, int fold, HashMap<String, Double> predictedView) {
		super();
		predictedView = new HashMap<String, Double>();
		this.id = id;
		this.targetResult = targetResult;
		this.predictionView = predictedView;
		this.fold = fold;
		this.qid = qid;
	}
	
	/**
	 * @return the qid
	 */
	public int getQid() {
		return qid;
	}
	/**
	 * @param qid the qid to set
	 */
	public void setQid(int qid) {
		this.qid = qid;
	}
	/**
	 * @return the predictedResultGlobal
	 */
	public double getPredictedResultGlobal() {
		return globalPrediction;
	}
	/**
	 * @param predictedResultGlobal the predictedResultGlobal to set
	 */
	public void setPredictedResultGlobal(double predictedResultGlobal) {
		this.globalPrediction = predictedResultGlobal;
	}
	/**
	 * @return the predictedView
	 */
	public HashMap<String, Double> getPredictedView() {
		return predictionView;
	}
	/**
	 * @param predictedView the predictedView to set
	 */
	public void setPredictedView(HashMap<String, Double> predictedView) {
		this.predictionView = predictedView;
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
		return globalPrediction;
	}
	/**
	 * @param predictedResult the predictedResult to set
	 */
	public void setPredictedResult(double predictedResult) {
		this.globalPrediction = predictedResult;
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
		predictionView.put(view, score);
	}
	
	public int getSizePredictedView(){
		return predictionView.size();
	}
}
