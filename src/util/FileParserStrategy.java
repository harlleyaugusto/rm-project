package util;

import java.io.FileNotFoundException;
import java.util.Map;
import dao.Answer;;

public interface FileParserStrategy {
	Map<Integer, Answer>  parse() throws FileNotFoundException;
}
