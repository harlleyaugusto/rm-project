package ufmg.dcc.rm.qa;

import java.util.HashMap;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

public class Answer {
	private int id;
	private int qid;
	private double targetResult;
	private double predictedScore;
	HashMap<String, Double> predictionView;// = new HashMap<String, Double>();
	private int fold;

	private HashMap<Integer, Double> features;
	
	public Answer(int id, int qid,double targetResult, int fold, HashMap<String, Double> predictionView) {
		super();
		this.id = id;
		this.targetResult = targetResult;
		this.predictionView = predictionView;
		this.fold = fold;
		this.qid = qid;
		features = new HashMap<Integer, Double>();
	}
	
	public Answer(int id, int qid,double targetResult, int fold, double predictedScore) {
		super();
		this.id = id;
		this.targetResult = targetResult;
		this.predictedScore = predictedScore;
		this.fold = fold;
		this.qid = qid;
		features = new HashMap<Integer, Double>();
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
	public double getPredictedScore() {
		return predictedScore;
	}
	/**
	 * @param predictedResultGlobal the predictedResultGlobal to set
	 */
	public void setPredictedScore(double predictedScore) {
		this.predictedScore = predictedScore;
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
	public void setPredictedView(HashMap<String, Double> predictionView) {
		this.predictionView = predictionView;
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
		return predictedScore;
	}
	/**
	 * @param predictedResult the predictedResult to set
	 */
	public void setPredictedResult(double predictedResult) {
		this.predictedScore = predictedResult;
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
	
	public void setFeatures(HashMap<Integer, Double> features)
	{	
		this.features = features;
	}
	
	public HashMap<Integer, Double> getFeatures(){
		return this.features;
	}
}
