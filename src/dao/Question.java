package dao;
import java.util.HashMap;
import java.util.Map;
public class Question {

	private int id;
	private Map<Integer, Answer> answers = new HashMap<Integer, Answer>();
	private int fold;
	
	public Question(int id, Map<Integer, Answer> answers, int fold) {
		super();
		this.id = id;
		this.answers = answers;
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
	 * @return the answers
	 */
	public Map<Integer, Answer> getAnswers() {
		return answers;
	}


	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Map<Integer, Answer> answers) {
		this.answers = answers;
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
