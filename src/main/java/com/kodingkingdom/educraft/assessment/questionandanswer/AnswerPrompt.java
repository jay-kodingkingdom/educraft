package com.kodingkingdom.educraft.assessment.questionandanswer;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class AnswerPrompt extends QuestionAnswerPrompt{

	int questionNumber;
	public AnswerPrompt(QuestionAnswerWorkSheet Worksheet, int SpeechNumber, int QuestionNumber) {
		super(Worksheet,SpeechNumber);questionNumber=QuestionNumber;}

	@Override
	public String getPromptText(ConversationContext context) {
		return "";}

	@Override
	public boolean blocksForInput(ConversationContext context) {
		return true;}

	@Override
	public Prompt acceptInput(ConversationContext context, String input) {
		worksheet.result.attempts.get(questionNumber).add(input);
		return worksheet.nextPrompt(this);}}
