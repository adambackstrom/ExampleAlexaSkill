package com.selland.interceptors;

import org.json.JSONStringer;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import com.amazon.ask.request.interceptor.GenericRequestInterceptor;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

public class LogRequestInterceptor implements RequestInterceptor {
	
//	Request handlers, request and response interceptors, and exception handlers are all passed a HandlerInput instance when invoked. 
//	This class exposes various entities useful in request processing, including:
//
//	RequestEnvelope: Contains the incoming Request and other context.
//	AttributesManager: Provides access to request, session, and persistence attributes.
//	ServiceClientFactory: Constructs service clients capable of calling Alexa APIs.
//	ResponseBuilder: Helps to build responses.
//	Context: Provides an optional, context object passed in by the host container. 
//	For example, for skills running on AWS Lambda, this is the context object for the AWS Lambda function.

	public void process(HandlerInput input) {
		((Context) input.getContext()).getLogger().log("Incoming request = " + new Gson().toJson(input.getRequestEnvelope()));
	}
}
