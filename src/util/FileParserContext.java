package util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import dao.Answer;
import dao.Question;

public class FileParserContext {
	private FileParserStrategy strategy;
	
	public FileParserContext(FileParserStrategy fileParserStrategy)
    {
        strategy = fileParserStrategy;
    }
	
	public HashMap<Integer, Question>  parse() throws FileNotFoundException
	{
		return strategy.parse();
	}

}
