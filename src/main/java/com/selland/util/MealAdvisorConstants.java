package com.selland.util;

import java.util.Arrays;
import java.util.List;

public class MealAdvisorConstants {

	// This is the message a user will hear after they ask (and hear) about a specific meal
    public static final String REPROMPT_MESSAGE = "Are there any other meals you would like to hear about?";

    // This is the message a user will hear when they ask Alexa for help in this skill
    public static final String HELP_MESSAGE = "I can analyze your past meals and give you a bit of advice.  Ask me about a past meal or snack, and I'll tell you what I know.  What would you like to do?";

    public static final String BYE_MESSAGE = "Bye! Thanks for using Meal Advisor.";
    
    public static final String INVALID_DATE_MESSAGE = "You gave me a date I can't handle. Please try again.";
    
    /*
     * Meal grade responses
     */
    public static final String A_GRADE_NO_DATE_MEAL_RESPONSE = "Your %1$s was very good for you! Keep it up!";
    
    public static final String A_GRADE_DATE_MEAL_RESPONSE = "Your %1$s on %2$s was very good for you! Keep it up!";
    
    public static final String B_GRADE_NO_DATE_MEAL_RESPONSE = "Your %1$s was okay for you. You had a slight undesirable increase in your glycemic response. ";
    
    public static final String B_GRADE_DATE_MEAL_RESPONSE = "Your %1$s on %2$s was okay for you. You had a slight undesirable increase in your glycemic response. ";
    
    public static final String C_GRADE_NO_DATE_MEAL_RESPONSE = "Your %1$s was not that good for you. You had an undesirable increase in your glycemic response. ";
    
    public static final String C_GRADE_DATE_MEAL_RESPONSE = "Your %1$s on %2$s was not that good for you. You had an undesirable increase in your glycemic response. ";

    public static final String D_GRADE_NO_DATE_MEAL_RESPONSE = "Your %1$s was not good for you. You had a <say-as interpret-as='interjection'>strong</say-as> undesirable increase in your glycemic response. ";
    
    public static final String D_GRADE_DATE_MEAL_RESPONSE = "Your %1$s on %2$s was not good for you. You had a <say-as interpret-as='interjection'>strong</say-as> undesirable increase in your glycemic response. ";

    public static final String F_GRADE_NO_DATE_MEAL_RESPONSE = "Your %1$s was <say-as interpret-as='interjection'>very bad</say-as> for you. You had a very strong undesirable increase in your glycemic response. ";
    
    public static final String F_GRADE_DATE_MEAL_RESPONSE = "Your %1$s on %2$s was <say-as interpret-as='interjection'>very bad</say-as> for you. You had a very strong undesirable increase in your glycemic response. ";

    	    		
    /*
     * Advices
     */
    public static final String B_GRADE_ADVICE_1 = "Maybe next time you could try going for a short walk after you eat. ";
    
    public static final String B_GRADE_ADVICE_2 = "Take note of whether you drank alcohol with the meal as alcohol negatively affects your blood sugar. ";
    
    public static final String B_GRADE_ADVICE_3 = "Take note of whether you drank alcohol with the meal as alcohol negatively affects your blood sugar. ";

    public static final String B_GRADE_ADVICE_4 = "Take note of whether you drank alcohol with the meal as alcohol negatively affects your blood sugar. ";

    public static final String C_GRADE_ADVICE_1 = "Next time you could take note of how many carbs are in the meal and try not to eat so many of them. ";
    
    public static final String C_GRADE_ADVICE_2 = "Next time you would need to exercise aggressively after you eat in order to offset the negative affects of this meal. ";
    
    public static final String C_GRADE_ADVICE_3 = "Next time don't eat while watching TV and sitting on the couch! Get up and move! ";
    
    public static final String C_GRADE_ADVICE_4 = "Try eating vegetables or salad next time. ";
    
    public static final String D_GRADE_ADVICE_1 = "You need to avoid eating the same meal or the same type of meal again. ";
    	
    public static final String D_GRADE_ADVICE_2 = "You'd have to run a 10K after this meal in order to offset the negative effects. ";
    
    public static final String D_GRADE_ADVICE_3 = "Keep it up you stupid <say-as interpret-as='expletive'>bleeping</say-as> and you'll be dead in a year. ";
    
    public static final String D_GRADE_ADVICE_4 = "Keep it up and you'll be dead in a year. ";
    
    public static final String F_GRADE_ADVICE_1 = "You need to avoid eating the same meal or the same type of meal again if you want to live another year. ";
    
    public static final String F_GRADE_ADVICE_2 = "You'll be injecting insulin before every meal for sure if you keep this up! ";
    
    public static final String F_GRADE_ADVICE_3 = "You're as good as dead you <say-as interpret-as='expletive'>bleeping</say-as> if you keep this up! ";

    public static final String F_GRADE_ADVICE_4 = "C'mon man you're killing yourself! ";

    public static final List<String> B_GRADE_ADVICE_LIST = Arrays.asList( new String[] {B_GRADE_ADVICE_1, B_GRADE_ADVICE_2, B_GRADE_ADVICE_3, B_GRADE_ADVICE_4});
    
    public static final List<String> C_GRADE_ADVICE_LIST = Arrays.asList( new String[] {C_GRADE_ADVICE_1, C_GRADE_ADVICE_2, C_GRADE_ADVICE_3, C_GRADE_ADVICE_4});
    
    public static final List<String> D_GRADE_ADVICE_LIST = Arrays.asList( new String[] {D_GRADE_ADVICE_1, D_GRADE_ADVICE_2, D_GRADE_ADVICE_3, D_GRADE_ADVICE_4});
    
    public static final List<String> F_GRADE_ADVICE_LIST = Arrays.asList( new String[] {F_GRADE_ADVICE_1, F_GRADE_ADVICE_2, F_GRADE_ADVICE_3, F_GRADE_ADVICE_4});
    
    
}
