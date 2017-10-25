package dao;

public class Answer {
	private int id;
	private int qid;
	private double targetResult;
	private double predictedResult;
	private int fold;
	
	public Answer(int id, double targetResult, double predictedResult, int fold) {
		super();
		this.id = id;
		this.targetResult = targetResult;
		this.predictedResult = predictedResult;
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
		return predictedResult;
	}
	/**
	 * @param predictedResult the predictedResult to set
	 */
	public void setPredictedResult(double predictedResult) {
		this.predictedResult = predictedResult;
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
	
}
