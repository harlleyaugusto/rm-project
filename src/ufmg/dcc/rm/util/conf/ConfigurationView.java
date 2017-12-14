package ufmg.dcc.rm.util.conf;

public class ConfigurationView {

	private static ConfigurationView instance;

	public static String path_files = "/home/harlley/Projects/rm-project/data/experiments_results_qa/";

	/*
	 * public static String[] files_views = {
	 * "stack_multiview_user_results_stack.txt",
	 * "stack_multiview_usergraph_results_stack.txt",
	 * "stack_multiview_style_results_stack.txt",
	 * "stack_multiview_structure_results_stack.txt",
	 * "stack_multiview_relevance_results_stack.txt",
	 * "stack_multiview_read_results_stack.txt",
	 * "stack_multiview_length_results_stack.txt",
	 * "stack_multiview_history_results_stack.txt", };
	 */

	public static String[] files_views = { "stack_multiview_user_results_stack.txt",
			"stack_multiview_history_results_stack.txt", };

	/*
	 * public static String[] files_views = {
	 * "cook_multiview_user_results_cook.txt",
	 * "cook_multiview_usergraph_results_cook.txt",
	 * "cook_multiview_style_results_cook.txt",
	 * "cook_multiview_structure_results_cook.txt",
	 * "cook_multiview_relevance_results_cook.txt",
	 * "cook_multiview_read_results_cook.txt",
	 * "cook_multiview_length_results_cook.txt",
	 * "cook_multiview_history_results_cook.txt", };
	 */

	/*
	 * public static String[] files_views = {
	 * "english_multiview_user_results_english.txt",
	 * "english_multiview_usergraph_results_english.txt",
	 * "english_multiview_style_results_english.txt",
	 * "english_multiview_structure_results_english.txt",
	 * "english_multiview_relevance_results_english.txt",
	 * "english_multiview_read_results_english.txt",
	 * "english_multiview_length_results_english.txt",
	 * "english_multiview_history_results_english.txt", };
	 */
	public static ConfigurationView getInstance() {
		if (instance == null) {
			instance = new ConfigurationView();
		}
		return instance;
	}
}
