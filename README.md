## Ranking aggregation approaches to rank answers in a Question and Answer forums

The Internet and Web  2.0 phenomenon huge repositories of human knowledge are being created, mostly because users are now able not only to consume but also to produce content. Many collaborative encyclopedias, blogs, and forums are being used in a daily-based. The freedom to create content has brought an issue on how a user can determine the quality of the information provided.   

To cope with this issue, many collaborative sites adopt quality control mechanisms, where users can indicate the quality of the content. However, the manual assessment is subject to human evaluation and it is no scale for the current information growth of these platforms. Thus, many services provided by these sites, such as ranking, recommendation, and even the manual quality assessment itself, would benefit from the adoption of automated or semi-automated
quality control mechanisms.    

Motivated by this idea, in this work, we propose a variation of the state-of-the-art ranking published by Hasan et al. focus on Question and Answer forums (Q&A). In such forums, users can post a question about a certain topic for which he/she receives answers from the other users. As soon as the answer is posted, all the users can evaluate it, tagging **upvote** or **downvote**. In forums such as Stack Overflow, the answers are expected to be correct and be ranked according to its quality. Normally, the answer at the top position is the best one, if it has already been indicated as so by the asker. Therefore, the answers are ranking regarding users' feedback and the best answer is presented at the top. 

While this strategy is effective in prioritizing the good answers, it still depends on the assessment of the user who asked the questions and the of the other users. Due to the manual process involved in this assessment, many good answers are not evaluated by the users. To overcome this, [Dalip et al.](https://dl.acm.org/doi/10.1002/asi.23650) proposed a learning to rank approach for ranking the answers in a Q&A forum. In that project,  the quality indicators are divided into semantically related groups. For each group is predicted a score of quality, then these predictions are combined with meta-learning strategy, in particular, a stacking learning strategy. 

Even though the approach proposed by [Dalip et al.](https://dl.acm.org/doi/10.1002/asi.23650) is considered state-of-the-art, no other method was tested to aggregate the quality predictions of each semantically related groups. Therefore, in this project, we propose to use approaches inspired by [ranking aggregation approaches] (https://link.springer.com/chapter/10.1007/0-306-47019-5_5). Specifically in this work, the ranking aggregation task was defined as an optimization problem and two approaches were applied: [Cross-entropy](https://link.springer.com/article/10.1023/A:1010091220143) (CE) and  [Genetic Algorithm](https://dl.acm.org/doi/book/10.5555/534133) (GA).

The overall results of this project are:
1. the ranking aggregation methods reached a good result, however, the method proposed by [Dalip et al.](https://dl.acm.org/doi/10.1002/asi.23650) is still considered state-of-the-art.
2. in general, just two or three groups of features are relevant to rank the answers, and the other ones can be left out. 

