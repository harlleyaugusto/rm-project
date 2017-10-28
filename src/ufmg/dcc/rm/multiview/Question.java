package ufmg.dcc.rm.multiview;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ufmg.dcc.rm.util.SortHashMapByValues;

public class Question {

	private int id;
	private HashMap<Integer, Answer> answers;

	private HashMap<String, HashMap<Integer, Double>> rankingPerView;

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

	private HashMap<Integer, Double> rankingTarget;

	public Question(int id, Answer ans) {
		super();
		this.id = id;
		answers = new HashMap<Integer, Answer>();
		rankingPerView =  new HashMap<String, HashMap<Integer, Double>>();
		setAnswer(ans);
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

	public static Answer getAnswer(HashMap<Integer, Question> qs, int aid) {
		for (int qid : qs.keySet())
			if (qs.get(qid).getAnswers().containsKey(aid))
				return qs.get(qid).getAnswers().get(aid);

		return null;
	}

	public void setAnswer(Answer ans) {
		answers.put(ans.getId(), ans);
	}

	public int getSizePredictedView() {
		int total = 0;
		for (int aid : answers.keySet()) {
			total += answers.get(aid).getSizePredictedView();
		}

		return total;
	}

	public  void sortingPerView() {
		for(String view : rankingPerView.keySet())
		{
			rankingPerView.put(view, (HashMap<Integer, Double>) SortHashMapByValues.sortByValue(rankingPerView.get(view)));
			
		}
		
		for(String view : rankingPerView.keySet())
		{
			System.out.print(view + " ");
			for(Integer aid : rankingPerView.get(view).keySet())
			{
				System.out.print(aid + ":" + rankingPerView.get(view).get(aid) + " ");
			}
			System.out.println();
		}
		
		/*Entry<Integer, Answer> entry = answers.entrySet().iterator().next();
		Answer ans = entry.getValue();
		HashMap<Integer, Double> ranking = new HashMap<Integer, Double>();

		rankingPerView = new HashMap<String, HashMap<Integer, Double>>();

		for (String view : ans.getPredictedView().keySet()) {
			for (Integer ansEachView : answers.keySet()) {
				ranking.put(ansEachView, answers.get(ansEachView).getPredictedView().get(view));
			}

			ranking = (HashMap<Integer, Double>) SortHashMapByValues.sortByValue(ranking);

			rankingPerView.put(view, ranking);

			System.out.print(view + " ");
			for (Integer aid : ranking.keySet())
				System.out.print(aid + ":" + ranking.get(aid) + " ");
			System.out.println();
		}

		return ranking;*/
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

	public HashMap<Integer, Double> rankingByTarget() {

		rankingTarget = new HashMap<Integer, Double>();

		for (Integer aid : answers.keySet()) {
			rankingTarget.put(aid, answers.get(aid).getTargetResult());
		}

		rankingTarget = (HashMap<Integer, Double>) SortHashMapByValues.sortByValue(rankingTarget);

		for (Integer aid : rankingTarget.keySet()) {
			System.out.print(aid + ":" + rankingTarget.get(aid) + " ");
		}

		return rankingTarget;
	}
}
