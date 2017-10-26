package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import dao.Answer;
import dao.Question;

public class DataReaderFromView implements FileParserStrategy {

	private String path;
	private String fileName;
	
	public DataReaderFromView(String path, String fileName) {
		super();
		this.path = path;
		this.fileName = fileName;
		
	}

	@Override
	public HashMap<Integer, Question> parse() throws FileNotFoundException {
		File in = null;
		Scanner scanner = null;
		in = new File(path+fileName);
		scanner = new Scanner(in);
		double 	targetResult;
		Answer ans;
		int qid;
		int id; 
		int fold;
		double predictedViewScore;
		HashMap<String, Double> predictedView;
		HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
		String q = null;
		
		String view = fileName.split("_")[2];
		
		try {
			q = scanner.nextLine();
			while (scanner.hasNext()) {
				//fold,id,qid,predictedResult,targetResult
				//0,458759,458721,0.0,22.0
				q = scanner.nextLine();
				String [] splits = q.split(",");
				
				fold = Integer.parseInt(splits[0]);
				id = Integer.parseInt(splits[1]);
				qid = Integer.parseInt(splits[2]);
				
				predictedViewScore = Double.parseDouble(splits[3]);
				
				predictedView = new HashMap<String, Double>();
				predictedView.put(view, predictedViewScore);
				targetResult = Double.parseDouble(splits[4]);

				ans = new Answer(qid, targetResult, fold, predictedView);
				
				if(!questions.containsKey(qid)){
					
					questions.put(qid, new Question(qid));
					questions.get(qid).setAnswer(ans);
				}
				else
				{
					if(questions.get(qid).getAnswers().containsKey(id))
					{
						questions.get(qid).getAnswer(id).setPredictedView(view, predictedViewScore);
					}
					else
					{
						questions.get(qid).setAnswer(ans);
					}
				}
				System.out.println("id: " + id +" targetResult: " + targetResult + " qid: " + qid);
				
			
			}
		} finally
		{
			scanner.close();		
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		DataReaderFromView data = new DataReaderFromView("/home/harlley/Projects/rm-project/data/experiments_results_qa/" , "stack_multiview_usergraph_results_stack.txt");
		data.parse();
	
	}

	
}
