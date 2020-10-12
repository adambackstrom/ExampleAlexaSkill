package com.selland.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Resolutions;
import com.amazon.ask.model.slu.entityresolution.ValueWrapper;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.selland.model.Meal;

/**
 * This class is mainly responsible for interrogating a user's meals and matching it with an incoming slot value
 * for a meal.
 * This class also contains logic around what should be an interface to a meal storage facility, currently a DynamoDB
 * table, for accessing meal information that would be persisted via another mechanism like via a wearable or mobile
 * interface for gathering meal information. This however is commented out. This class instead randomly generates responses
 * to given meal names.
 */
public class MealUtil {

	private MealUtil() { }
	
    /**
     * Interrogates the given Slot map for meal ID and if it finds one, returns a Meal enum.
     * 
     * @param slots
     * @param logger
     * @return Optional<Meal>
     */
    public static Optional<Meal> getMeal(Map<String, Slot> slots, LambdaLogger logger) {
    	
        for (Slot slot : slots.values()) {
        	
            String slotName = slot.getName();
//            logger.log("Slot name = " + slotName);
//            logger.log("Slot value = " + slot.getValue());
            
            if (slotName.equalsIgnoreCase("mealname")) {
	    		logger.log("Processing Meal Slot: " + new Gson().toJson(slot));
	            
	            Resolutions res = slot.getResolutions();
	            
	            if (null != res) {
		            List<Resolution> resolutions = res.getResolutionsPerAuthority();
		            for (Resolution resolution : resolutions) {
		            	
		            	List<ValueWrapper> values = resolution.getValues();
		            	final String slotResolutionName = values.get(0).getValue().getName();
		            	final String slotResolutionId = values.get(0).getValue().getId();
		            	
		                logger.log("slotResolutionName = " + slotResolutionName);
		                logger.log("slotResolutionId = " + slotResolutionId);
		                
		            	Optional<Meal> meal = Stream.of(Meal.values())
		            			.filter(s -> s.getId().equals(slotResolutionId))
		            			.findAny();
		            	
		            	if (meal.isPresent()) {
		            		return meal;
		            	}
		            }
	            }
            }            
        }
        return Optional.empty();
    }

	// Find the meal most likely to be referenced by the given date if given.
	// Interrogate the meal data and formulate a response
	public static String formulateResponseSpeechText(String mealName, String dayOfWeekUtterance) {

		// find the meal in the Meal Table that matches with the given dayOfWeekUtterance
//		List<MealSet> meals = MealTableGateway.getMeals("a8336657-1598-4106-8b16-a482ce0ec7c4");

		 // this would be replaced by an actual Meal grade calculation
		char grade = MealUtil.getRandomLetterGrade();

		String response = MealUtil.getMealGradeResponse(grade, mealName, dayOfWeekUtterance);
		String advice = MealUtil.getRandomGradeAdvice(grade);
		response += advice;
	       
		return response;
	}
	
	public static String formulateResponseSpeechText(String mealName, LocalDate date) {
		String response = "";
		
		// Find the meal most likely to be referenced by the given date if given.
		// Interrogate the meal data and formulate a response
//		List<MealSet> meals = MealTableGateway.getMeals("a8336657-1598-4106-8b16-a482ce0ec7c4");
		
		 // this would be replaced by an actual Meal grade
		char grade = MealUtil.getRandomLetterGrade();
		
		response = MealUtil.getMealGradeResponse(grade, mealName, date);
		String advice = MealUtil.getRandomGradeAdvice(grade);
		response += advice;
	       
		return response;
	}
	
    public static char getRandomLetterGrade() {
        final String LETTER_GRADES = "ABCDF";
        
        int rndCharAt = new Random().nextInt(LETTER_GRADES.length() - 1);
        return LETTER_GRADES.charAt(rndCharAt);
    }
    
    public static String getRandomGradeAdvice(char grade) {
        
    	if (grade == 'B') {
	        int index = new Random().nextInt(MealAdvisorConstants.B_GRADE_ADVICE_LIST.size());
	        return MealAdvisorConstants.B_GRADE_ADVICE_LIST.get(index);
    	} else if (grade == 'C') {
	        int index = new Random().nextInt(MealAdvisorConstants.C_GRADE_ADVICE_LIST.size());
	        return MealAdvisorConstants.C_GRADE_ADVICE_LIST.get(index);
    	} else if (grade == 'D') {
	        int index = new Random().nextInt(MealAdvisorConstants.D_GRADE_ADVICE_LIST.size());
	        return MealAdvisorConstants.D_GRADE_ADVICE_LIST.get(index);
    	} else if (grade == 'F') {
	        int index = new Random().nextInt(MealAdvisorConstants.F_GRADE_ADVICE_LIST.size());
	        return MealAdvisorConstants.F_GRADE_ADVICE_LIST.get(index);
    	}
    	return "";
    }
    
    public static String getMealGradeResponse(char grade, String mealName, LocalDate date) {
    	
    	String responseText = "Could not analyze the meal you specified";
    	
    	if (grade == 'A') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.A_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.A_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'B') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.B_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.B_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'C') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.C_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.C_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'D') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.D_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.D_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'F') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.F_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.F_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	}
    	return responseText;
    }
    
    public static String getMealGradeResponse(char grade, String mealName, String date) {
    	
    	String responseText = "Could not analyze the meal you specified";
    	
    	if (grade == 'A') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.A_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.A_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'B') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.B_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.B_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'C') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.C_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.C_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'D') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.D_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.D_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	} else if (grade == 'F') {
    		if (null != date) {
    			responseText = String.format(MealAdvisorConstants.F_GRADE_DATE_MEAL_RESPONSE, mealName, date);
    		} else {
    			responseText = String.format(MealAdvisorConstants.F_GRADE_NO_DATE_MEAL_RESPONSE, mealName);
    		}
    	}
    	return responseText;
    }
}
	
	
