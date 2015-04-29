package com.kodingkingdom.educraft.assessment.questionandanswer;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class HintPrompt extends QuestionAnswerPrompt{

	int hintNumber;
	public HintPrompt(QuestionAnswerWorkSheet Worksheet, int SpeechNumber, int HintNumber) {
		super(Worksheet,SpeechNumber);hintNumber=HintNumber;}

	@Override
	public String getPromptText(ConversationContext context) {
		return worksheet.questionAnswerScript.get(worksheet.hintList.get(hintNumber));}

	@Override
	public boolean blocksForInput(ConversationContext context) {
		return false;}

	@Override
	public Prompt acceptInput(ConversationContext context, String input) {
		return worksheet.nextPrompt(this);}}
