package com.example.codepath_assignment_instagramreplica;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // This registers the Post class to Parse, which is required before calling Parse.initialize
        ParseObject.registerSubclass(Post.class);

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("cherie-instagram") // should correspond to APP_ID env variable
                .clientKey(BuildConfig.CLIENT_KEY)  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("http://cherie-instagram.herokuapp.com/parse").build());
    }
}
