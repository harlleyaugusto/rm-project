package ufmg.dcc.rm.util.conf;

public class ConfigurationView {

	private static ConfigurationView instance;

	public static String path_files = "/home/harlley/Projects/rm-project/data/experiments_results_qa/";

	public static String[] files_views = { "stack_multiview_user_results_stack.txt",
			"stack_multiview_usergraph_results_stack.txt", "stack_multiview_style_results_stack.txt",
			"stack_multiview_structure_results_stack.txt", "stack_multiview_relevance_results_stack.txt",
			"stack_multiview_read_results_stack.txt", "stack_multiview_length_results_stack.txt",
			"stack_multiview_history_results_stack.txt", };

	public static ConfigurationView getInstance() {
		if (instance == null) {
			instance = new ConfigurationView();
		}
		return instance;
	}
}
