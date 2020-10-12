package com.selland.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.selland.handlers.exception.AskSdkExceptionHandler;
import com.selland.interceptors.LogRequestInterceptor;

public class SellandStreamHandler extends SkillStreamHandler {

	public SellandStreamHandler() {
		super(getSkill());
	}
	
	private static Skill getSkill() {
        return Skills.standard()
                .withSkillId("amzn1.ask.skill.d7e4998c-c135-4ac6-9d06-62f784ac6d19")
                .addRequestHandlers(
                	new DateMealAdvisorIntentHandler(),
                	new DayOfWeekMealAdvisorIntentHandler(),
                	new TimeOfDayMealAdvisorIntentHandler(),
                    new MealAdvisorIntentHandler(),
                    new LaunchRequestHandler(),
                    new HelpIntentHandler(),
                    new CancelandStopIntentHandler(),
                    new SessionEndedRequestHandler(),
                    new FallBackIntentHandler())
                .addExceptionHandler(new AskSdkExceptionHandler())
                .withAutoCreateTable(true)
                .addRequestInterceptors(new LogRequestInterceptor())
//                .addResponseInterceptors(new LogResponseInterceptor())
                .build();
    }

}
