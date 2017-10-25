package Util;

import java.util.List;
import java.util.Map;
import dao.Answer;;

public interface IFileParserStrategy {
	List<Answer>  Parse(String path);
}
