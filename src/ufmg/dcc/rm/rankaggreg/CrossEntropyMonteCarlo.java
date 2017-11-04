package ufmg.dcc.rm.rankaggreg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;

import ufmg.dcc.rm.experiments.RunExperiment;
import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;

public class CrossEntropyMonteCarlo extends RankingAggregation {

	PrintWriter writer; //

	public CrossEntropyMonteCarlo() {
		super();
	}

	@Override
	public void before() throws FileNotFoundException {
		forum = fpc.parse();
		Question.sortingAllAnswerPerView(forum);
	}

	@Override
	public void sorting() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		boolean first = true;
		int count = 1;
		int total = 0;
		for (Integer qid : forum.keySet()) {

			System.out.println("******" + count + "/" + forum.size() + "******");
			count++;
			String file = "data/rankaggr/" + qid + "_sorting";
			writer = new PrintWriter(file, "UTF-8");
			writer.flush();
			for (String view : forum.get(qid).getRankingView().keySet()) {
				writer.flush();
				for (Integer aid : forum.get(qid).getRankingView().get(view).keySet()) {
					writer.flush();
					if (first) {
						writer.print(aid);
						first = false;
					} else {
						writer.print(" " + aid);
					}
				}
				first = true;
				writer.flush();
				writer.println();

			}

			result(qid, forum.get(qid).getAnswers().size());

			writer.close();
			total++;
			// if(total >20) break;
		}
	}

	private void result(int qid, int sizeOfList) throws IOException, InterruptedException {

		String scriptFile = "scriptR/" + qid + ".R";
		PrintWriter writerScript = new PrintWriter(scriptFile, "UTF-8");

		writerScript.println("require(RankAggreg)");
		writerScript.println("d <- as.matrix(read.table(\"data/rankaggr/" + qid
				+ "_sorting\", header=FALSE, sep = \" \", as.is=TRUE), nrow=8,ncol=" + sizeOfList + ")");
		writerScript.println("d");
		if (sizeOfList > 10)
			writerScript.println(
					"RankAggreg(d," + 10 + ", method=\"CE\", distance = \"Spearman\", rho=.1, verbose = FALSE)");
		else
			writerScript.println("RankAggreg(d," + sizeOfList
					+ ", method=\"CE\", distance = \"Spearman\", rho=.1, verbose = FALSE)");

		writerScript.flush();
		writerScript.close();

		Process shell = null;
		shell = Runtime.getRuntime().exec("Rscript " + scriptFile);
		shell.waitFor();

		// System.out.println("Question: " + qid);
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
		String line;
		ArrayList<Integer> ranking = new ArrayList<Integer>();

		while ((line = reader.readLine()) != null) {

			// System.out.println(line);
			System.out.flush();
			if (line.contains("The optimal list is:")) {
				String[] order = reader.readLine().trim().split(" ");
				for (int i = 0; i < order.length; i++) {
					// System.out.println(order[i]);
					ranking.add(new Integer(order[i]));
				}
			}
		}
		// System.out.println("ranking.size():" + ranking.size());
		optimalRanking.put(qid, ranking);
		// System.out.println("optimalRanking.size():" + optimalRanking.size());
		reader.close();

	}

	@Override
	protected void after() {
		// TODO Auto-generated method stub
		RunExperiment re = new RunExperiment(this);
		re.runNDCG();

	}

	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {

		CrossEntropyMonteCarlo CE = new CrossEntropyMonteCarlo();

		CE.run();

		// System.out.println(CE.forum.get(3719226).getAnswer(3719278).getPredictedView().get("user"));
		// System.out.println(CE.forum.get(3719226).getRankingView().get("user").get(3719278));
	}

}
