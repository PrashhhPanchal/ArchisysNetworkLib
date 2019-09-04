package com.archisys.archisys_network_lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LibPrefs {

        private static SharedPreferences sharedPreference = null;

        public static final String PREFS_FILENAME = "stockappsprefs";
        public static final String USERPIC = "userPic";
        public static final String USERACCEPT = "AcceptKey";
        public static final String FullName = "stock_fullname";
        public static final String Email = "stock_email";
        public static final String Authorization= "stock_Authorization";
        public static final String MOBILE_NUMBER = "stockbook_mobile_number";
        public static final String ISFirst = "FirstTime";
        public static final String OTPPREFNUMBER = "OTPPREFNUMBER"; // 0 -> already login , 1-> from LOgin OTP , 2-> from Registration OTP
        public static final String UUID = "UUID";
        public static final String DATE_KEY = "DATE";

        public static String BaseUrl="";
        public static String getDevice="";
        public static String getAuthorization="";
        public static boolean isShowLog=false;

        @SuppressWarnings("static-access")
        private static void openPrefs(Context context) {
            sharedPreference = context.getSharedPreferences(LibPrefs.PREFS_FILENAME,
                    context.MODE_PRIVATE);
        }

        public static void clearall(Context context) {
            LibPrefs.openPrefs(context);
            SharedPreferences.Editor LibPrefsEdit = LibPrefs.sharedPreference.edit();
            LibPrefsEdit.clear();
            LibPrefsEdit.commit();
        }

        public static void setValue(Context context, String key, String value) {

            LibPrefs.openPrefs(context);

            SharedPreferences.Editor LibPrefsEdit = LibPrefs.sharedPreference.edit();

            LibPrefsEdit.putString(key, value);
            LibPrefsEdit.commit();

            LibPrefsEdit = null;
            LibPrefs.sharedPreference = null;
        }
        public static void setValueInt(Context context, String key, int value) {

            LibPrefs.openPrefs(context);

            SharedPreferences.Editor LibPrefsEdit = LibPrefs.sharedPreference.edit();

            LibPrefsEdit.putInt(key, value);
            LibPrefsEdit.commit();

            LibPrefsEdit = null;
            LibPrefs.sharedPreference = null;
        }

        public static void setValueLong(Context context, String key, long value) {

            LibPrefs.openPrefs(context);

            SharedPreferences.Editor LibPrefsEdit = LibPrefs.sharedPreference.edit();

            LibPrefsEdit.putLong(key, value);
            LibPrefsEdit.commit();

            LibPrefsEdit = null;
            LibPrefs.sharedPreference = null;
        }

        public static long getValueLong(Context context, String key, long value) {

            LibPrefs.openPrefs(context);

            if (LibPrefs.sharedPreference.contains(key)) {
                long result = LibPrefs.sharedPreference.getLong(key, value);
                LibPrefs.sharedPreference = null;

                return result;
            }

            return 0;
        }

        public static boolean hasPrefs(Context context, String key) {

            LibPrefs.openPrefs(context);

            boolean LibPrefsreturn=LibPrefs.sharedPreference.contains(key);


            LibPrefs.sharedPreference = null;
            return LibPrefsreturn;
        }

        public static int gettheme(Context context, String key, int value) {

            LibPrefs.openPrefs(context);

            int result = LibPrefs.sharedPreference.getInt(key, value);

            LibPrefs.sharedPreference = null;

            return result;
        }

        public static String getValue(Context context, String key, String value) {

            LibPrefs.openPrefs(context);

            if (LibPrefs.sharedPreference.contains(key)) {
                String result = LibPrefs.sharedPreference.getString(key, value);
                LibPrefs.sharedPreference = null;

                return result;
            }

            return null;
        }

        public static int getValueInt(Context context, String key, int value) {

            LibPrefs.openPrefs(context);

            if (LibPrefs.sharedPreference.contains(key)) {
                int result = LibPrefs.sharedPreference.getInt(key, value);
                LibPrefs.sharedPreference = null;

                return result;
            }

            return 0;
        }


        public static void setValueBoolen(Context context, String key, Boolean ble) {
            LibPrefs.openPrefs(context);

            SharedPreferences.Editor LibPrefsEdit = LibPrefs.sharedPreference.edit();

            LibPrefsEdit.putBoolean(key, ble);
            LibPrefsEdit.commit();

            LibPrefsEdit = null;
            LibPrefs.sharedPreference = null;
        }

        public static Boolean getValueBoolen(Context context, String key, Boolean ble) {
            LibPrefs.openPrefs(context);

            Boolean result = LibPrefs.sharedPreference.getBoolean(key, ble);

            LibPrefs.sharedPreference = null;

            return result;
        }

    public static void LogInfo(String Tag,String data){
        if(isShowLog){
            Log.i(Tag,data);
        }
    }

}
