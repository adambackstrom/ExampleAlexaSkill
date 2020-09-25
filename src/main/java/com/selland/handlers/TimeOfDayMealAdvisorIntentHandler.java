package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * This Handler will interpret requests for meals at a specific spoken time of day, like "noon", or "9 AM".
 *
 * As of yet this is unimplemented.
 */
public class TimeOfDayMealAdvisorIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
	    return input.matches(intentName("TimeOfDayMealAdvisorIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return null;
	}
}
