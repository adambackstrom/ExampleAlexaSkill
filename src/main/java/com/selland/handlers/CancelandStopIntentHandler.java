package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.selland.util.MealAdvisorConstants;

public class CancelandStopIntentHandler implements RequestHandler {

	// For now, we don't need to discern between 'cancel' and 'stop'
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
	}
	
	public Optional<Response> handle(HandlerInput input) {
	        String speechText = MealAdvisorConstants.BYE_MESSAGE;
	        return input.getResponseBuilder()
	                .withSpeech(speechText)
	                .withSimpleCard("MealAdvisor", speechText)
	                .build();
	}

}
