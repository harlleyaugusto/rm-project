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

import javax.security.auth.login.Configuration;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import ufmg.dcc.rm.experiments.RunExperiment;
import ufmg.dcc.rm.parse.DataReaderFeature;
import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.util.conf.ConfigurationFeature;

public class CrossEntropyMonteCarlo extends RankingAggregation {

	PrintWriter writer;
	protected FileParserContext fpc;

	public CrossEntropyMonteCarlo() {
		super();
		fpc = new FileParserContext(new DataReaderFromView());
		name = "CE";
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
		for (Integer qid : forum.keySet()) {

			System.out.println(
					"******" + count + "/" + forum.size() + "****** # answer: " + forum.get(qid).getAnswers().size());
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

			rCE(qid, forum.get(qid).getAnswers().size());

			writer.close();
		
		}
	}

	private void rCE(int qid, int sizeOfList) throws IOException, InterruptedException {

		String scriptFile = "scriptR/" + qid + ".R";
		PrintWriter writerScript = new PrintWriter(scriptFile, "UTF-8");

		writerScript.println("require(RankAggreg)");
		writerScript.println("d <- as.matrix(read.table(\"data/rankaggr/" + qid
				+ "_sorting\", header=FALSE, sep = \" \", as.is=TRUE), nrow=8,ncol=" + sizeOfList + ")");
		writerScript.println("d");

		int N = 10 * sizeOfList * sizeOfList;
		double rho;
		if (N < 100)
			rho = .1;
		else
			rho = .1;

		if (sizeOfList > 10)
			writerScript.println("RankAggreg(d," + 10 + ", method=\"CE\", distance = \"Spearman\", rho=.01, N ="
					+ 10 * (10 * 10) + ",  verbose = FALSE)");
		else
			writerScript.println("RankAggreg(d," + sizeOfList + ", method=\"CE\", distance = \"Spearman\", rho=" + rho
					+ ", N=" + 10 * (sizeOfList * sizeOfList) + ", verbose = FALSE)");

		writerScript.flush();
		writerScript.close();

		Process shell = null;
		shell = Runtime.getRuntime().exec("Rscript " + scriptFile);
		shell.waitFor();

		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
		String line;
		ArrayList<Integer> ranking = new ArrayList<Integer>();

		while ((line = reader.readLine()) != null) {
			System.out.flush();
			if (line.contains("The optimal list is:")) {
				String[] order = reader.readLine().trim().split(" ");
				for (int i = 0; i < order.length; i++) {
					ranking.add(new Integer(order[i]));
				}
			}
		}
		optimalRanking.put(qid, ranking);
		reader.close();

	}

	@Override
	protected void after() throws IOException {
		// TODO Auto-generated method stub
		RunExperiment re = new RunExperiment(this);
		re.runNDCG();

	}

	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {

		CrossEntropyMonteCarlo CE = new CrossEntropyMonteCarlo();

		CE.run();
		DataReaderFeature.parse(CE);
		
		System.out.println("*****************************");
		
		
		StackingAggregation SA = new StackingAggregation();

		SA.run();
		
		DataReaderFeature.parse(SA);

		RunExperiment.compareRankings(SA, CE);
		
		System.out.println("*****************************");

		GeneticAlgorithm GA = new GeneticAlgorithm();

		GA.run();
		
		DataReaderFeature.parse(GA);

		RunExperiment.compareRankings(SA, GA);
		
	}

}
