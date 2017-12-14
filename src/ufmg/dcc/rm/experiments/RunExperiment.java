package ufmg.dcc.rm.experiments;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.rankaggreg.RankingAggregation;
import ufmg.dcc.rm.util.SortUtil;

public class RunExperiment {

	RankingAggregation method;
	final Integer[] cutoffNDCG = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public double avgNDCG;

	public RunExperiment(RankingAggregation m) {
		method = m;
		avgNDCG = 0;
	}

	public void runNDCG() {
		Double ndcgAcc;
		HashMap<Integer, ArrayList<Integer>> ranking = method.getOptimalRanking();
		HashMap<Integer, Question> forum = method.getForum();
		for (int i = 0; i < cutoffNDCG.length; i++) {
			ndcgAcc = 0.0;
			for (Integer qid : ranking.keySet()) {
				// System.out.println("********Question id: " + qid + "
				// *************");
				Double[] orgRanking = new Double[ranking.get(qid).size()];
				Integer[] optimalList = new Integer[ranking.get(qid).size()];
				optimalList = ranking.get(qid).toArray(optimalList);

				Double[] perfectRanking = new Double[forum.get(qid).getRankingTarget().values().toArray().length];
				perfectRanking = forum.get(qid).getRankingTarget().values().toArray(perfectRanking);

				Arrays.sort(perfectRanking);
				perfectRanking = SortUtil.reverseSortDesc(perfectRanking);

				for (int j = 0; j < optimalList.length; j++) {
					orgRanking[j] = forum.get(qid).getRankingTarget().get(optimalList[j]);
				}

				Double[] rangePerfect;
				Double[] rangeOrg;
				
				if (ranking.get(qid).size() < cutoffNDCG[i]) {
					rangePerfect = Arrays.copyOfRange(perfectRanking, 0, ranking.get(qid).size());
					rangeOrg = Arrays.copyOfRange(orgRanking, 0, ranking.get(qid).size());
				} else {
					rangePerfect = Arrays.copyOfRange(perfectRanking, 0, cutoffNDCG[i]);
					rangeOrg = Arrays.copyOfRange(orgRanking, 0, cutoffNDCG[i]);
				}

				double ndcg = MetricUtils.ndcg(rangeOrg, rangePerfect, true);
				forum.get(qid).setNDCG(i, ndcg);

				ndcgAcc += ndcg;
			}
			System.out.println("Avg ndcg@" + cutoffNDCG[i] + ": " + ndcgAcc / ranking.keySet().size());
			avgNDCG += ndcgAcc / ranking.keySet().size();
		}
		avgNDCG = avgNDCG / 10;
		System.out.println("Avg avg ndcg:" + avgNDCG);
	}

	public static void compareRankings(RankingAggregation a, RankingAggregation b)
			throws FileNotFoundException, UnsupportedEncodingException {

		String wekaFile = "wekaFile" + a.getName() + b.getName() + "_stack.csv";
		PrintWriter weka = new PrintWriter(wekaFile, "UTF-8");

		int countA = 0;
		int countB = 0;

		for (int i = 1; i <= 186; i++) {
			weka.print("feature" + i + ",");
		}
		weka.println("target");

		for (Integer qid : a.getForum().keySet()) {
			double avg_a = a.getForum().get(qid).getAvgNDCG();
			double avg_b = b.getForum().get(qid).getAvgNDCG();
			for (Integer aid : a.getForum().get(qid).getAnswers().keySet()) {
				for (int i = 1; i <= 186; i++) {
					if (a.getForum().get(qid).getAnswer(aid).getFeatures().containsKey(i)) {
						weka.print(a.getForum().get(qid).getAnswer(aid).getFeatures().get(i) + ",");
					} else
						weka.print("0.0,");
				}
				if (avg_a < avg_b) {
					weka.println(b.getName());
				}
				else {
					weka.println(a.getName());
				}
			}
			weka.flush();
			if (avg_a < avg_b) {
				countB++;
			}

			else {
				countA++;
			}
		}
		System.out.println(a.getName() + " victories: " + countA);
		System.out.println(b.getName() + " victories: " + countB);
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
