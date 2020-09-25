package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.selland.model.Meal;
import com.selland.util.MealAdvisorConstants;
import com.selland.util.MealUtil;

public class DayOfWeekMealAdvisorIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
	    return input.matches(intentName("DayOfWeekMealAdvisorIntent"));
	}
	
	public Optional<Response> handle(HandlerInput input) {
		   Optional<Response> response;
	       String speechText = "";
	       
	       IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
	       Map<String, Slot> slots = intentRequest.getIntent().getSlots();
	       
	       LambdaLogger logger = ((Context) input.getContext()).getLogger();
	       
	       Optional<Meal> meal = MealUtil.getMeal(slots, logger);
	       Optional<String> dayOfWeek = getDayOfWeekUtterance(slots, logger);
	       
	       if (meal.isPresent() && dayOfWeek.isPresent()) {
	    	   // analyze meal attributes and build a custom speech text response
	    	   
	    	   logger.log("DayOfWeekMealAdvisorIntentHandler : Found MEAL " + meal.get().getName());
	    	   logger.log("DayOfWeekMealAdvisorIntentHandler : Found day of week " + dayOfWeek.get());
	    	   
	    	   speechText = MealUtil.formulateResponseSpeechText(meal.get().getName(), dayOfWeek.get());
	    	   
	    	   response = input.getResponseBuilder()
	                   .withSpeech(speechText)
	                   .withReprompt(MealAdvisorConstants.REPROMPT_MESSAGE)
	                   .withShouldEndSession(false)
	                   .build();
	    	   
	       } else {
	    	   
	    	   String unknownMealText = "I'm sorry. I am having trouble understanding what you want me to analyze. " + MealAdvisorConstants.HELP_MESSAGE;
	    	   response = input.getResponseBuilder()
	                    .withSpeech(unknownMealText)
	                    .withReprompt(unknownMealText)
	                    .withShouldEndSession(false)
	                    .build();
	            
	       }
	       
	       return response;
	}

    public Optional<String> getDayOfWeekUtterance(Map<String, Slot> slots, LambdaLogger logger) {

    	for (Slot slot : slots.values()) {
        	
            String slotName = slot.getName();
            String slotValue = slot.getValue();
            logger.log("DayOfWeekMealAdvisorIntentHandler : Slot name = " + slotName);
            logger.log("DayOfWeekMealAdvisorIntentHandler : Slot value = " + slotValue);
            
            if (slotName.equalsIgnoreCase("dayofWeek")) {
	    		logger.log("Processing day of week Slot: " + new Gson().toJson(slot));
	    		return Optional.of(slotValue);
            }
        }
        return Optional.empty();
        
    }
}
