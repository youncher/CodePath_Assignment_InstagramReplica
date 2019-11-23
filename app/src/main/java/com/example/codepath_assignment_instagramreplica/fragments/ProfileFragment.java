package com.example.codepath_assignment_instagramreplica.fragments;

import android.util.Log;

import com.example.codepath_assignment_instagramreplica.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    private final String TAG = "ProfileFragment";

    @Override
    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER); // Adding to query to get user in the query
        postQuery.setLimit(20);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        // Network calls are expensive, so preferable to run in background
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    //Log.d(TAG, "Post " + (i + 1) + " by " + post.getUser().getUsername() + " Description: " + post.getDescription());
                    Log.d(TAG, "Post " + (i + 1) + ": " + post.getDescription());
                }
            }
        });
    }
}
