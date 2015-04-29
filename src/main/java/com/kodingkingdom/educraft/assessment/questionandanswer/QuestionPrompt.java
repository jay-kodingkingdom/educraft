package com.kodingkingdom.educraft.assessment.questionandanswer;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class QuestionPrompt extends QuestionAnswerPrompt{

	public QuestionPrompt(QuestionAnswerWorkSheet Worksheet, int SpeechNumber) {
		super(Worksheet,SpeechNumber);}

	@Override
	public String getPromptText(ConversationContext context) {
		return worksheet.questionAnswerScript.get(speechNumber);}

	@Override
	public boolean blocksForInput(ConversationContext context) {
		return false;}

	@Override
	public Prompt acceptInput(ConversationContext context, String input) {
		return worksheet.nextPrompt(this);}}
