package com.selland.handlers.exception;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.model.Response;

public class AskSdkExceptionHandler implements ExceptionHandler {

	private static Logger LOG = LoggerFactory.getLogger(AskSdkExceptionHandler.class);
	 
    @Override
    public boolean canHandle(HandlerInput input, Throwable throwable) {
        return throwable instanceof AskSdkException;
    }

    @Override
    public Optional<Response> handle(HandlerInput input, Throwable throwable) {
    	LOG.error("Exception handled: " + throwable.getMessage());
    	
        return input.getResponseBuilder()
                    .withSpeech("An error was encountered while handling your request. Try again later.")
                    .build();
    }

}
