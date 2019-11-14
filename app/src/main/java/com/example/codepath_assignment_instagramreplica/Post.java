package com.example.codepath_assignment_instagramreplica;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {

    // Must match column names in Parse
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";


    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescriotion(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        // PraseFile is what we get back when we request for the image from Parse
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        // ParseUser is what we get back when we request for the user from Parse
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }
}
