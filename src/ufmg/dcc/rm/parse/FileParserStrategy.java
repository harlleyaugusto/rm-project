package ufmg.dcc.rm.parse;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import ufmg.dcc.rm.qa.Answer;
import ufmg.dcc.rm.qa.Question;;

public interface FileParserStrategy {
	HashMap<Integer, Question>  parse() throws FileNotFoundException;
}
