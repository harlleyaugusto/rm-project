package util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import dao.Answer;
import dao.Question;;

public interface FileParserStrategy {
	HashMap<Integer, Question>  parse() throws FileNotFoundException;
}
