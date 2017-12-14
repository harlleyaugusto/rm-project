package ufmg.dcc.rm.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.ParseConversionEvent;

import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.rankaggreg.GeneticAlgorithm;
import ufmg.dcc.rm.rankaggreg.RankingAggregation;
import ufmg.dcc.rm.util.conf.ConfigurationFeature;

public class DataReaderFeature {

	public static void parse(RankingAggregation ra) throws FileNotFoundException {
		File in = null;
		Scanner scanner = null;

		ConfigurationFeature conf = ConfigurationFeature.getInstance();
		HashMap<Integer, Double> features;
		String q = null;
		int qid, aid, total = 0;
		int idFeature;
		double target, featureScore;
		String[] splits; 

		for (int i = 0; i < conf.files_folds.length; i++) {
			in = new File(conf.path_files + conf.files_folds[i]);
			scanner = new Scanner(in);

			try {
				while (scanner.hasNext()) {
					total++;
					features = new HashMap<Integer, Double>();
					q = scanner.nextLine();
					splits = q.split(" ");

					target = Double.parseDouble(splits[0]);
					qid = Integer.parseInt(splits[1].split(":")[1]);

					for (int j = 3; j < splits.length - 2; j++) {
						idFeature = Integer.parseInt(splits[j].split(":")[0]);
						featureScore = Double.parseDouble(splits[j].split(":")[1]);
						features.put(idFeature, featureScore);
					}
					aid = Integer.parseInt(splits[splits.length - 2].split(":")[1]);
					ra.getForum().get(qid).getAnswer(aid).setFeatures(features);
				}

			} finally {
				scanner.close();
			}
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException, IOException, InterruptedException {

		GeneticAlgorithm GA = new GeneticAlgorithm();
		GA.before();
		parse(GA);
		int total = 0;
		for (Integer qid : GA.getForum().keySet()) {
			for(Integer aid: GA.getForum().get(qid).getAnswers().keySet())
			{
				System.out.println("Feature: " + GA.getForum().get(qid).getAnswer(aid).getFeatures().size());
				total += GA.getForum().get(qid).getAnswer(aid).getFeatures().size();
			}
			
		}
		System.out.println("Total: " + total);
		
	}

}
