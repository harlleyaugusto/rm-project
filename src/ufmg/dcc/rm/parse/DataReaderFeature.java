package ufmg.dcc.rm.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;
import ufmg.dcc.rm.util.conf.ConfigurationFeature;

public class DataReaderFeature implements FileParserStrategy {

	@Override
	public HashMap<Integer, Question> parse() throws FileNotFoundException {
		File in = null;
		Scanner scanner = null;

		ConfigurationFeature conf = ConfigurationFeature.getInstance();
		HashMap<Integer, Question> questions = new HashMap<Integer, Question>();

		for (int i = 0; i < conf.files_folds.length; i++) {
			in = new File(conf.path_files + conf.files_folds[i]);
			scanner = new Scanner(in);

			try {

				while (scanner.hasNext()) {
					
				}
			} finally {
				scanner.close();
			}
		}
		return null;
	}

}
