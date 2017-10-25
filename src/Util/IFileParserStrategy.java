package Util;

import java.util.Map;
import dao.Answer;;

public interface IFileParserStrategy {
	Map<Integer, Answer>  Parse(String path);
}
