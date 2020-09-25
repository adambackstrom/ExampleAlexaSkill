package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
	       return input.matches(intentName("AMAZON.HelpIntent"));
	}
	
	public Optional<Response> handle(HandlerInput input) {
	        String speechText = "I am here to help you learn how food affects your body. What meal can I analyze?";
	        return input.getResponseBuilder()
	                .withSpeech(speechText)
	                .withSimpleCard("MealAdvisor", speechText)
	                .withReprompt(speechText)
	                .build();
	}

}
