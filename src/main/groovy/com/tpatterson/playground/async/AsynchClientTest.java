package com.tpatterson.playground.async;

import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;
import org.testng.annotations.Test;

public class AsynchClientTest
{
    // http://www.baeldung.com/async-http-client

    // Also, check this FLuent example from Apache which looks awesome.  https://stackoverflow.com/questions/3142915/how-do-you-create-an-asynchronous-http-request-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa


    @Test
    public void testGet()
    {
        AsyncHttpClient client = Dsl.asyncHttpClient();

        Request getRequest = new RequestBuilder(HttpConstants.Methods.GET)
            .setUrl("http://www.baeldung.com")
            .build();

        client.executeRequest(getRequest, new AsyncCompletionHandler<Object>() {
            @Override
            public Object onCompleted(Response response) {
                System.out.println(response.getResponseBody());
                return response;
            }
        });

        // Wait or the program ends
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception ex)
        {

        }

        // or, like below
//        Request getRequest = Dsl.get("http://www.baeldung.com").build()

    }

    @Test
    public void testGetWithFutures() throws Exception
    {
        AsyncHttpClient client = Dsl.asyncHttpClient();

        Request getRequest = new RequestBuilder(HttpConstants.Methods.GET)
            .setUrl("http://www.baeldung.com")
            .build();

        ListenableFuture<Response> futureResponse = client.executeRequest(getRequest);
        System.out.println(futureResponse.get().getResponseBody());


//        futureResponse.addListener()

    }

}
