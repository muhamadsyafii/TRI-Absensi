package dev.syafii.triabsensi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class PrefUtils {
    private PrefUtils() {}


    private static final String PREFERENCE_NAME = "helper_preferences";
    private static final String KEY_IS_LOGIN = "user_is_login";

    private static PrefUtils instance;

    private SharedPreferences mSharedPreferences;


    /**
     * Method to Get instance of Singleton {@code HelperPreferences } class
     *
     * @param context - Context of the calling activity/application
     * @return - Instance of HelperPreferences
     */
    public static PrefUtils with(final Context context) {
        if (instance == null) {
            instance = new PrefUtils(context);
        }
        return instance;
    }

    /**
     * Private Constructor, initializing shared preferences
     *
     * @param context - Context to initialize preferences
     */
    private PrefUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    /**
     * Method to store {@code String} value corresponding to a key
     *
     * @param key   - Key
     * @param value - String value to be stored
     */
    public void saveString(final String key, final String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Method to store {@code String} value corresponding to a key
     *
     * @param key   - Key
     * @param value - String value to be stored
     */
    public void saveStringFromObject(final String key, final Object value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, new Gson().toJson(value));
        editor.apply();
    }


    /**
     * Method to store {@code int} value corresponding to a key
     *
     * @param key   - Key
     * @param value - int value to be stored
     */
    public void saveInt(final String key, final int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Method to store {@code long} value corresponding to a key
     *
     * @param key   - Key
     * @param value - long value to be stored
     */
    public void saveLong(final String key, final long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Method to store {@code boolean} value corresponding to a key
     *
     * @param key   - Key
     * @param value - boolean value to be stored
     */
    public void saveBoolean(final String key, final boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Method to store {@code float} value corresponding to a key
     *
     * @param key   - Key
     * @param value - Float value to be stored
     */
    public void saveFloat(final String key, final float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Method to clear all the shared preferences stored, this will be helpful in
     * scenarios like logout
     */
    public void clear() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Method to with {@code String} value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns null if not found
     */
    public String getString(final String key) {
        return mSharedPreferences.getString(key, "");
    }

    /**
     * Method to with {@code long} value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns 0 if not found
     */
    public long getLong(final String key) {
        return mSharedPreferences.getLong(key, 0L);
    }

    /**
     * Method to with {@code int} value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns 0 if not found
     */
    public int getInt(final String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    /**
     * Method to with {@code float} value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns 0 if not found
     */
    public double getFloat(final String key) {
        return mSharedPreferences.getFloat(key, 0.0F);
    }

    /**
     * Method to with {@code boolean} value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns false if not found
     */
    public boolean getBoolean(final String key) {
        return mSharedPreferences.getBoolean(key, false);
    }
    public Boolean getIsFirstInstall() {
        return mSharedPreferences.getBoolean(Constants.KEY_IS_FIRST_INSTALL, false);
    }

    public boolean isLogin() {
        return mSharedPreferences.getBoolean(Constants.KEY_IS_LOGIN, false);
    }

    public void setLoginStatus(boolean status) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGIN, status);
        if (!status) {
            editor.putString(Constants.KEY_USER, "");
        }
        editor.apply();
    }
}
