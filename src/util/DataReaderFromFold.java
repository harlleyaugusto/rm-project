package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import dao.Answer;

public class DataReaderFromFold implements FileParserStrategy {

	private String path;
	
	
	public DataReaderFromFold(String path) {
		super();
		this.path = path;
	}

	@Override
	public Map<Integer, Answer> parse() throws FileNotFoundException {
		File in = null;
		Scanner scanner = null;
		in = new File(path);
		scanner = new Scanner(in);
		double 	targetResult;
		int qid;
		int id; 
		ArrayList<Double> features = new ArrayList();
		
		
		try {
						
			while (scanner.hasNext()) {
				String q = scanner.nextLine();
				String [] splits = q.split(" ");
				
				targetResult = Double.parseDouble(splits[0]);
				qid = Integer.parseInt(splits[1].split(":")[1]);
				id = Integer.parseInt(splits[splits.length-2].split(":")[1]);
				
				System.out.println("id: " + id +" targetResult: " + targetResult + " qid: " + qid);
				
				for(int i = 3 ; i < splits.length - 2; i++)
				{
					System.out.println(splits[i].split(":").length);
					features.add(Integer.parseInt(splits[i].split(":")[0]), Double.parseDouble(splits[i].split(":")[1]));
				}
			}
		} finally
		{
			scanner.close();		
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		DataReaderFromFold data = new DataReaderFromFold("/home/harlley/Projects/rm-project/data/qa_data/qa_stack/teste.foldteste");
		data.parse();
	
	}

	
}
