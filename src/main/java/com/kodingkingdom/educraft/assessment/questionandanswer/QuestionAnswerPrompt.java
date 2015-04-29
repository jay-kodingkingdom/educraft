package com.kodingkingdom.educraft.assessment.questionandanswer;

import org.bukkit.conversations.Prompt;

public abstract class QuestionAnswerPrompt implements Prompt{
	int speechNumber;
	QuestionAnswerWorkSheet worksheet;
	public QuestionAnswerPrompt(QuestionAnswerWorkSheet Worksheet,int SpeechNumber){worksheet=Worksheet;speechNumber=SpeechNumber;}}
