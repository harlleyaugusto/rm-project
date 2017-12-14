package ufmg.dcc.rm.rankaggreg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ufmg.dcc.rm.experiments.RunExperiment;
import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Question;

public class GeneticAlgorithm extends RankingAggregation {

	PrintWriter writer; //
	protected FileParserContext fpc;
	protected int convIn;
	RunExperiment re;

	protected int popSize;
	protected double CP;
	protected double MP;
	protected int seed;
	protected int maxInter;

	public GeneticAlgorithm() {
		super();
		fpc = new FileParserContext(new DataReaderFromView());
		popSize = 100;
		convIn = 30;
		CP = .4;
		MP = .01;
		seed = 123;
		maxInter = 100;
		name = "GA";
	}

	public GeneticAlgorithm(int popSize, int convIn, double CP, double MP, int seed, int maxInter) {
		super();
		fpc = new FileParserContext(new DataReaderFromView());
		this.popSize = popSize;
		this.convIn = convIn;
		this.CP = CP;
		this.MP = MP;
		this.seed = seed;
		this.maxInter = maxInter;
		name = "GA";
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

			rGA(qid, forum.get(qid).getAnswers().size());

			writer.close();
		}
	}

	private void rGA(int qid, int sizeOfList) throws IOException, InterruptedException {

		String scriptFile = "scriptR/" + qid + ".R";
		PrintWriter writerScript = new PrintWriter(scriptFile, "UTF-8");

		writerScript.println("require(RankAggreg)");
		writerScript.println("d <- as.matrix(read.table(\"data/rankaggr/" + qid
				+ "_sorting\", header=FALSE, sep = \" \", as.is=TRUE), nrow=8,ncol=" + sizeOfList + ")");
		writerScript.println("d");

		int k;
		if (sizeOfList > 10)
			k = 10;
		else
			k = sizeOfList;

		writerScript.println("RankAggreg(d," + k + ", method=\"GA\", distance = \"Spearman\", seed=" + seed
				+ ", maxInter=1000, popSize=" + popSize + ", CP=" + CP + ", MP=" + MP + ", convIn =" + convIn
				+ ", verbose = FALSE)");

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
		re = new RunExperiment(this);
		re.runNDCG();

	}

	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {
		// TODO Auto-generated method stub

	}

}
