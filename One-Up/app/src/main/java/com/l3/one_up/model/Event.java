package com.l3.one_up.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.Date;

@ParseClassName("Event")
public class Event extends ParseObject {

    // Milliseconds in a day.
    private static final long MILLIS_24HRS = 1000 * 60 * 60 * 24;

    private static final String KEY_USER = "user";
    private static final String KEY_ACTIVITY = "activity";
    private static final String KEY_CATEGORY = KEY_ACTIVITY + ".category";
    private static final String KEY_INPUT = "inputType";
    private static final String KEY_TOTALXP = "totalXP";
    private static final String KEY_CREATED = "createdAt";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public ParseObject getActivity() {
        return getParseObject(KEY_ACTIVITY);
    }

    public void setActivity(ParseObject activity) {
        put(KEY_ACTIVITY, activity);
    }

    public JSONObject getInputType() {
        return getJSONObject(KEY_INPUT);
    }

    public void setInputType(JSONObject inputType) {
        put(KEY_INPUT, inputType);
    }

    public int getTotalXP() {
        return getInt(KEY_TOTALXP);
    }

    public void setTotalXP(int points) {
        put(KEY_TOTALXP, points);
    }

    @Override
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }

    public static class Query extends ParseQuery<Event> {

        public Query() {
            super(Event.class);
        }

        public Query getX(int howMany) {
            setLimit(howMany);
            return this;
        }

        public Query byUser(ParseUser user) {
            whereEqualTo(KEY_USER, user);
            return this;
        }

        public Query ofActivity(String activityId) {
            whereEqualTo(KEY_ACTIVITY, activityId);
            return this;
        }

        public Query ofCategory(String category) {
            whereEqualTo(KEY_CATEGORY, category);
            return this;
        }

        public Query includeUser() {
            include(KEY_USER);
            return this;
        }

        public Query includeActivity() {
            include(KEY_ACTIVITY);
            return this;
        }

        public Query onlyThisWeek() {
            whereGreaterThanOrEqualTo(KEY_CREATED, new Date(System.currentTimeMillis() - MILLIS_24HRS * 7));
            return this;
        }

        public Query mostRecentFirst() {
            orderByDescending(KEY_CREATED);
            return this;
        }

        public Query oldestFirst() {
            orderByAscending(KEY_CREATED);
            return this;
        }
    }
}
