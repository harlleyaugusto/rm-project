package Util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DataReader {

	public static void qa_data_reader(String path) throws IOException {
		File in = null;
		Scanner scanner = null;
		
		try {
			in = new File(path);
			scanner = new Scanner(in);

			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} finally
		{
			scanner.close();		
		}
	}

	public static void main(String[] args) throws IOException {
		qa_data_reader("/home/harlley/Projects/rm-project/data/qa_data/qa_stack/teste.fold0");
	}
}
