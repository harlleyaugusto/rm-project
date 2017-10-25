package util;

import java.io.FileNotFoundException;
import java.util.Map;

import dao.Answer;

public class FileParserContext {
	private FileParserStrategy strategy;
	
	public FileParserContext(FileParserStrategy fileParserStrategy)
    {
        strategy = fileParserStrategy;
    }
	
	public Map<Integer, Answer>  parse() throws FileNotFoundException
	{
		return strategy.parse();
	}

}
