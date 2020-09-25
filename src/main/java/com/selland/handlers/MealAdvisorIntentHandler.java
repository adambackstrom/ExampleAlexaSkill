package com.selland.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.selland.model.Meal;
import com.selland.util.MealUtil;
import com.selland.util.MealAdvisorConstants;

public class MealAdvisorIntentHandler implements RequestHandler {

	// This is the message a user will hear after they ask (and hear) about a specific meal
    public static String REPROMPT_MESSAGE = "Are there any other meals you would like to hear about?";
    
	public boolean canHandle(HandlerInput input) {
	    return input.matches(intentName("MealAdvisorIntent").or(intentName("AMAZON.YesIntent")));
	}
	
	/* 
	 * This handler only handles request for today's "date"
	 */
	public Optional<Response> handle(HandlerInput input) {
		Optional<Response> response;
	       
//	       String accessToken = input.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
	       
	       IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
	       Map<String, Slot> slots = intentRequest.getIntent().getSlots();
	       
	       LambdaLogger logger = ((Context) input.getContext()).getLogger();
	       
	       Optional<Meal> meal = MealUtil.getMeal(slots, logger);
	       
	       if (meal.isPresent()) {
	    	   // analyze meal attributes and build a custom speech description

	    	   String speechText = MealUtil.formulateResponseSpeechText(meal.get().getName(), (LocalDate) null);
	    	   
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
	       
	       // Add 'withSimpleCard' if device supports display interface - add a render directive
//	            return input.getResponseBuilder()
//	                    .withSpeech(speechText)
//	                    .withSimpleCard(title, primaryText)
//	                    .addRenderTemplateDirective(template)
//	                    .withReprompt(speechText)
//	                    .build();
	}

	// example of getting a persistent attribute
//	@Override
//	public boolean canHandle(HandlerInput input) {
//	    Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
//	    return persistentAttributes.get("title").equals("AWSPodcast");
//	}
//
	// example of setting a persistent attribute
//	@Override
//	public Optional<Object> handle(HandlerInput input) {
//	    Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
//	    persistentAttributes.put("title", "JavaPodcast");
//	    input.getAttributesManager().setPersistentAttributes(persistentAttributes);
//	    input.getAttributesManager().savePersistentAttributes();
//	
//	    return input.getResponseBuilder().build();
//	}
	
}
