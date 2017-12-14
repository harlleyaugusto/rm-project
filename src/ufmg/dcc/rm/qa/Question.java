package ufmg.dcc.rm.qa;

import java.util.HashMap;
import java.util.Map;

import ufmg.dcc.rm.util.SortUtil;

public class Question {

	private int id;
	private HashMap<Integer, Answer> answers;

	private HashMap<String, HashMap<Integer, Double>> rankingPerView;
	private HashMap<Integer, Double> rankingTarget;
	private HashMap<Integer, Double> rankingPredictedScore;
	private Double[] ndcg;

	/**
	 * @return the rankingGlobalScore
	 */
	public HashMap<Integer, Double> getRankingPredictedScore() {
		return rankingPredictedScore;
	}

	/**
	 * @param rankingGlobalScore
	 *            the rankingGlobalScore to set
	 */
	public void setRankingPredictedScore(HashMap<Integer, Double> rankingGlobalScore) {
		this.rankingPredictedScore = rankingGlobalScore;
	}

	public Question(int id, Answer ans) {
		super();
		this.id = id;
		answers = new HashMap<Integer, Answer>();
		rankingPerView = new HashMap<String, HashMap<Integer, Double>>();
		rankingTarget = new HashMap<Integer, Double>();
		setAnswer(ans);
		ndcg = new Double[10];
	}

	/**
	 * @return the rankingView
	 */
	public HashMap<String, HashMap<Integer, Double>> getRankingView() {
		return rankingPerView;
	}

	/**
	 * @param rankingView
	 *            the rankingView to set
	 */
	public void setRankingView(HashMap<String, HashMap<Integer, Double>> rankingView) {
		this.rankingPerView = rankingView;
	}

	/**
	 * @return the rankingTarget
	 */
	public HashMap<Integer, Double> getRankingTarget() {
		return rankingTarget;
	}

	/**
	 * @param rankingTarget
	 *            the rankingTarget to set
	 */
	public void setRankingTarget(HashMap<Integer, Double> rankingTarget) {
		this.rankingTarget = rankingTarget;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the answers
	 */
	public Map<Integer, Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(HashMap<Integer, Answer> answers) {
		this.answers = answers;
	}

	public Answer getAnswer(int aid) {
		if (answers.containsKey(aid))
			return answers.get(aid);
		else
			return null;
	}

	public void setAnswer(Answer ans) {
		answers.put(ans.getId(), ans);
		rankingTarget.put(ans.getId(), ans.getTargetResult());

	}

	public int getSizePredictedView() {
		int total = 0;
		for (int aid : answers.keySet()) {
			total += answers.get(aid).getSizePredictedView();
		}

		return total;
	}

	public void sortingAnswerPerView() {
		for (String view : rankingPerView.keySet()) {
			rankingPerView.put(view, (HashMap<Integer, Double>) SortUtil.sortByValue(rankingPerView.get(view)));

		}
	}

	public static void sortingAllAnswerPerView(HashMap<Integer, Question> questions) {
		for (Integer qid : questions.keySet())
			questions.get(qid).sortingAnswerPerView();
	}

	public void setPredictedPerViewForEachAnswer(String view, int aid, double score) {
		if (!rankingPerView.containsKey(view)) {
			HashMap<Integer, Double> ranking = new HashMap<Integer, Double>();
			ranking.put(aid, score);
			rankingPerView.put(view, ranking);
		} else {
			rankingPerView.get(view).put(aid, score);
		}
	}

	public HashMap<Integer, Double> sortingByTarget() {

		rankingTarget = new HashMap<Integer, Double>();

		for (Integer aid : answers.keySet()) {
			rankingTarget.put(aid, answers.get(aid).getTargetResult());
		}

		rankingTarget = (HashMap<Integer, Double>) SortUtil.sortByValue(rankingTarget);

		return rankingTarget;
	}

	public HashMap<Integer, Double> sortingByGlobalScore() {

		rankingPredictedScore = new HashMap<Integer, Double>();

		for (Integer aid : answers.keySet()) {
			rankingPredictedScore.put(aid, answers.get(aid).getPredictedScore());
		}

		rankingPredictedScore = (HashMap<Integer, Double>) SortUtil.sortByValue(rankingPredictedScore);

		return rankingPredictedScore;
	}

	public static void sortingAllQuestionsByGlobalScore(HashMap<Integer, Question> questions) {
		for (Integer qid : questions.keySet())
			questions.get(qid).sortingByGlobalScore();
	}

	public static Answer getAnswer(HashMap<Integer, Question> qs, int aid) {
		for (int qid : qs.keySet())
			if (qs.get(qid).getAnswers().containsKey(aid))
				return qs.get(qid).getAnswers().get(aid);

		return null;
	}

	public void setNDCG(int at, double ndcg) {
		this.ndcg[at] = ndcg;
	}

	public double getAvgNDCG() {
		double avg = 0;
		for (int i = 0; i < ndcg.length; i++)
			avg += ndcg[i];

		return avg/ndcg.length;
	}
}
