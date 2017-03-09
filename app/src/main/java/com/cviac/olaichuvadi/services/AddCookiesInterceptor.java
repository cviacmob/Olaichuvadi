package com.cviac.olaichuvadi.services;

import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashSet;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) Prefs.getStringSet("oc_cookies", new HashSet<String>());
        StringBuffer sb = new StringBuffer();
        //int counter = 0;
        for (String cookie : preferences) {
            sb.append(cookie);
            sb.append("; ");
        }
        builder.addHeader("Cookie", sb.toString());
        return chain.proceed(builder.build());
    }
}