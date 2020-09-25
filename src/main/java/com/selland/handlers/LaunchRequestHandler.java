package com.selland.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

public class LaunchRequestHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
	    return input.matches(requestType(LaunchRequest.class));
	}
	
	public Optional<Response> handle(HandlerInput input) {
	        String speechText = "Welcome to Meal Advisor, ask me how a meal affected you.";
	        return input.getResponseBuilder()
	                .withSpeech(speechText)
	                .withSimpleCard("Meal Feedback", speechText)
	                .withReprompt(speechText)
	                .build();
	}

}
