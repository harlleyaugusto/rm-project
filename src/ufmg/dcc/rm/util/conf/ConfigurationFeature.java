package ufmg.dcc.rm.util.conf;

public class ConfigurationFeature {
	private static ConfigurationFeature instance;

	public static String path_files = "data/qa_data/";

	// public static String[] files_views = { "MV_results_stack.txt", };

	public static String[] files_folds = { "/qa_data/teste.fold0.txt",
			"/qa_data/teste.fold1.txt", "/qa_data/teste.fold2.txt",
			"/qa_data/teste.fold3.txt", "/qa_data/teste.fold4.txt", };

	// public static String[] files_views = { "MV_results_english.txt", };

	public static ConfigurationFeature getInstance() {
		if (instance == null) {
			instance = new ConfigurationFeature();
		}
		return instance;
	}
}
