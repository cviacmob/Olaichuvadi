package com.cviac.olaichuvadi.utilities;

import android.app.Application;
import android.content.ContextWrapper;

public class OlaichuvadiApp  extends Application {

    private boolean networkStatus = true;

    public boolean isNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(boolean networkStatus) {
        this.networkStatus = networkStatus;
    }
//
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

//    public void sendEmail(String emailid, String subject, String msgBody) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://apps.cviac.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        CVIACApi api = retrofit.create(CVIACApi.class);
//        EmailInfo emailinfo = new EmailInfo(emailid, subject, msgBody);
//
//        Call<SendEmailResponse> call = api.sendEmail(emailinfo);
//        call.enqueue(new Callback<SendEmailResponse>() {
//            @Override
//            public void onResponse(Response<SendEmailResponse> response, Retrofit retrofit) {
//                SendEmailResponse rsp = response.body();
////                Toast.makeText(PoojariApp.this,
////                        "Send Email Success", Toast.LENGTH_LONG).show();
//
//            }
}