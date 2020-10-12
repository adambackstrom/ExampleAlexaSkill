package com.selland.persistence;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

/**
 * The main access to the Meal table data store.
 *
 * The MealTable asynchronously receives and stores meal data for a user
 */
public class MealTableGateway {
	
	public static void saveMealSet(MealSet mealSet) {
		AmazonDynamoDB dynamoClient = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper dynamoMapper = new DynamoDBMapper(dynamoClient);
		dynamoMapper.save(mealSet);
	}
	
	public static List<MealSet> getMeals(String mealSetId) {
		AmazonDynamoDB dynamoClient = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper dynamoMapper = new DynamoDBMapper(dynamoClient);

		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(mealSetId));

		DynamoDBQueryExpression<MealSet> queryExpression = new DynamoDBQueryExpression<MealSet>()
		    .withIndexName("mealSetId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("mealSetId = :v1")
		    .withExpressionAttributeValues(eav);

		return dynamoMapper.query(MealSet.class, queryExpression);
	}

	// Leave in for posterity sake - we're not persisting MealSets via this code
//	public MealSet buildMealSet(String requestId, String id, int nbrMealsPerDay, int nbrDays) {
//		MealSet mealSet = new MealSet();
//		mealSet.setRequestId(requestId);
//		mealSet.setId(id);
//		for (int i = 0; i < nbrDays * nbrMealsPerDay; i++) {
//			
//			Meal meal = new Meal(mealEvent.getType(), mealEvent.getUniqueId(), mealEvent.getTimestamp(),
//					mealEvent.getDetails(), mealEvent.getSize(), mealEvent.getCarbs());
//			mealSet.getMeals().add(meal);
//		}
//		return mealSet;
//	}

}
