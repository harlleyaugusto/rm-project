package ufmg.dcc.rm.util.conf;

import java.util.ArrayList;

public class ViewConfiguration {
	
	private static ViewConfiguration instance;
	
	private static String path_files = "/home/harlley/Projects/rm-project/data/experiments_results_qa/";

	 public static String[] files_views  = {path_files + "stack_multiview_user_results_stack.txt",
			 path_files +"stack_multiview_usergraph_results_stack.txt",
			 path_files +"stack_multiview_style_results_stack.txt",
			 path_files +"stack_multiview_structure_results_stack.txt",
			 path_files +"stack_multiview_relevance_results_stack.txt", 
			 path_files +"stack_multiview_read_results_stack.txt",
			 path_files +"stack_multiview_length_results_stack.txt",
			 path_files + "stack_multiview_history_results_stack.txt",};
	
	public static ViewConfiguration getInstance()
    { 
        if (instance == null)
        {
            instance = new ViewConfiguration();
        }
        return instance;
    }
}
