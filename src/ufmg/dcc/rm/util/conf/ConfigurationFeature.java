package ufmg.dcc.rm.util.conf;

public class ConfigurationFeature {
	private static ConfigurationFeature instance;

	public static String path_files = "data/qa_data/";

	// public static String[] files_views = { "MV_results_stack.txt", };

	// public static String[] files_folds = { "/qa_cook/teste.fold0",
	// "/qa_cook/teste.fold1", "/qa_cook/teste.fold2",
	// "/qa_cook/teste.fold3", "/qa_cook/teste.fold4", };

	// public static String[] files_folds = { "/qa_english/teste.fold0",
	// "/qa_english/teste.fold1",
	// "/qa_english/teste.fold2", "/qa_english/teste.fold3",
	// "/qa_english/teste.fold4", };

	public static String[] files_folds = { "/qa_stack/teste.fold0", "/qa_stack/teste.fold1", "/qa_stack/teste.fold2",
			"/qa_stack/teste.fold3", "/qa_stack/teste.fold4", };

	public static ConfigurationFeature getInstance() {
		if (instance == null) {
			instance = new ConfigurationFeature();
		}
		return instance;
	}
}
