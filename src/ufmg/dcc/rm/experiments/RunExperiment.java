package ufmg.dcc.rm.experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.rankaggreg.RankingAggregation;

public class RunExperiment {

	RankingAggregation method;
	final Integer[] cutoffNDCG = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public RunExperiment(RankingAggregation m) {
		method = m;
	}

	public void runNDCG() {
		HashMap<Integer, ArrayList<Integer>> ranking = method.getOptimalRanking();
		HashMap<Integer, Question> forum = method.getForum();
		for (int i = 0; i < cutoffNDCG.length; i++) {
			for (Integer qid : ranking.keySet()) {
				// System.out.println("********Question id: " + qid + "
				// *************");
				Double[] orgRanking = new Double[ranking.get(qid).size()];
				Integer[] optimalList = new Integer[ranking.get(qid).size()];
				optimalList = ranking.get(qid).toArray(optimalList);

				Double[] perfectRanking = new Double[forum.get(qid).getRankingTarget().values().toArray().length];
				perfectRanking = forum.get(qid).getRankingTarget().values().toArray(perfectRanking);

				for (int j = 0; j < optimalList.length; j++) {
					orgRanking[j] = forum.get(qid).getRankingTarget().get(optimalList[j]);
				}

				Double[] rangePerfect;
				Double[] rangeOrg;
				if (ranking.get(qid).size() < cutoffNDCG[i]) {
					rangePerfect = Arrays.copyOfRange(orgRanking, 0, ranking.get(qid).size());
					rangeOrg = Arrays.copyOfRange(perfectRanking, 0, ranking.get(qid).size());
				} else {
					rangePerfect = Arrays.copyOfRange(orgRanking, 0, cutoffNDCG[i]);
					rangeOrg = Arrays.copyOfRange(perfectRanking, 0, cutoffNDCG[i]);
				}

				System.out.println("ndcg@" + cutoffNDCG[i] + ": " + MetricUtils.ndcg(rangeOrg, rangePerfect, true));
			}
			// System.out.println("*********************");
		}

	}

	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50 };
		Arrays.copyOfRange(arr, 0, 2); // returns {10, 20}
		Arrays.copyOfRange(arr, 1, 4); // returns {20, 30, 40}
		int[] teste = Arrays.copyOfRange(arr, 2, 10); // returns {30, 40, 50}
														// (length = 5)

		for (int i = 0; i < teste.length; i++) {
			System.out.println(teste[i]);
		}
	}

}