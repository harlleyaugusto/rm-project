package dao;

import java.util.HashMap;
import java.util.Map;

public class Question {

	private int id;
	private HashMap<Integer, Answer> answers;// = new HashMap<Integer,
												// Answer>();

	public Question(int id) {
		super();
		this.id = id;
		answers = new HashMap<Integer, Answer>();
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
}
