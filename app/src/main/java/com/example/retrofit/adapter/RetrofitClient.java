package com.example.retrofit.adapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static  String baseUrl = "https://picsum.photos/";


    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

//    public static OkHttpClient provideOkHttpClient() {
//        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//        okHttpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder newRequest = request.newBuilder().header("authorization", "Basic YWtpemFwaXVzZXI6MSRwWjlkNjdmVjIyYk4=");
//                return chain.proceed(newRequest.build());
//            }
//        });
//
//        return okHttpClientBuilder.build();
//    }
}


