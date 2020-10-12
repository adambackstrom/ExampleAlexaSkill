package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.selland.util.MealUtil;
import com.selland.util.MealAdvisorConstants;

/**
 * This class handles Date-specific meal requests
 *
 *  Examples of how verbal Date types are interpreted by Alexa
 * 	//	"today": 2016-11-24
 * 	//	"now": 2016-11-24
 * 	//	"tomorrow": 2016-11-25
 * 	//	"november twenty-fifth": 2016-11-25
 * 	//	"next monday": 2016-11-30
 * 	//	"this week": 2016-W48
 * 	//	"next week": 2016-W49
 * 	//	"this weekend": 2016-W48-WE
 * 	//	"this month": 2016-11
 * 	//	"next year": 2017
 * 	//	"this decade": 201X
 * 	//	"next winter": 2018-WI
 * 	//	"right now": 2016-11-24
 */
public class DateMealAdvisorIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
	    return input.matches(intentName("DateMealAdvisorIntent"));
	}
	
	public Optional<Response> handle(HandlerInput input) {
	   Optional<Response> response;
		
	   String speechText = "";
	       
       IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
       Map<String, Slot> slots = intentRequest.getIntent().getSlots();
       
       LambdaLogger logger = ((Context) input.getContext()).getLogger();
       
       Optional<Meal> meal = MealUtil.getMeal(slots, logger);
       Optional<String> date = getDate(slots, logger);
       
       if (meal.isPresent()) {
    	   // analyze meal attributes and build a custom speech text response
    	   
    	   logger.log("Found MEAL " + meal.get().getName());
    	   
    	   LocalDate parsedDate = null;
    			   
    	   if (date.isPresent()) {
    		   logger.log("Found DATE " + date.get());
    		   
    		   // dates must map to a specific date, such as 2015-10-3, 2019-10-14
    		   try {
    			   
    				DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
    				parsedDate = LocalDate.parse(date.get(), dtf);
    				
    		   } catch (java.time.format.DateTimeParseException ex) {
    			   logger.log("Error parsing DATE : " + ex.getMessage());
    			   speechText =  MealAdvisorConstants.INVALID_DATE_MESSAGE;
    		   }
    	   }
    	   
    	   speechText = MealUtil.formulateResponseSpeechText(meal.get().getName(), parsedDate);
    	   
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
	
    public Optional<String> getDate(Map<String, Slot> slots, LambdaLogger logger) {

    	for (Slot slot : slots.values()) {
        	
            String slotName = slot.getName();
            String slotValue = slot.getValue();

//            logger.log("Slot name = " + slotName);
//            logger.log("Slot value = " + slotValue);
            
            if (slotName.equalsIgnoreCase("date")) {
	    		logger.log("Processing Date Slot: " + new Gson().toJson(slot));
	    		return Optional.of(slotValue);
            }
        }
        return Optional.empty();
    }
}
