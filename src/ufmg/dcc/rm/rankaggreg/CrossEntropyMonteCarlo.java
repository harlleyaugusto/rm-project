package ufmg.dcc.rm.rankaggreg;

import java.io.FileNotFoundException;
import java.util.HashMap;

import ufmg.dcc.rm.parse.DataReaderFromView;
import ufmg.dcc.rm.parse.FileParserContext;
import ufmg.dcc.rm.qa.Question;

public class CrossEntropyMonteCarlo {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		FileParserContext fpc = new FileParserContext(new DataReaderFromView());
		HashMap<Integer, Question> questions = fpc.parse();
	}



}
