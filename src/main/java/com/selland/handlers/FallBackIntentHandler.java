package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.selland.util.MealAdvisorConstants;
/*
 *  AMAZON.FallbackIntent helps you handle 
 *  unexpected utterances, or when a customer says something that doesn’t map to any intents in your skill.
 */
public class FallBackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sorry, I am having trouble understanding what you want. " + MealAdvisorConstants.HELP_MESSAGE;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Meal Advice", speechText)
                .withReprompt(speechText)
                .build();
    }

}
