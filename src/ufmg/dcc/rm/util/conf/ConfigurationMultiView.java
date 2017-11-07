package ufmg.dcc.rm.util.conf;

public class ConfigurationMultiView {
	private static ConfigurationMultiView instance;

	public static String path_files = "/home/harlley/Projects/rm-project/data/experiments_results_qa/";

	public static String[] files_views = { "MV_results_stack.txt", };

	public static ConfigurationMultiView getInstance() {
		if (instance == null) {
			instance = new ConfigurationMultiView();
		}
		return instance;
	}
}
