package com.accenture.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {

	private static Retrofit retrofit = null;
	
	public static Retrofit getClient(String baseURL) {
		 // Create a logging interceptor to log request and responses
		  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		  interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		  
		  OkHttpClient client = new OkHttpClient.Builder()
		      .addInterceptor(interceptor).build();
		  
		if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
	}
}


