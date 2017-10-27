package ufmg.dcc.rm.parse;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import ufmg.dcc.rm.multiview.Answer;
import ufmg.dcc.rm.multiview.Question;;

public interface FileParserStrategy {
	HashMap<Integer, Question>  parse() throws FileNotFoundException;
}
