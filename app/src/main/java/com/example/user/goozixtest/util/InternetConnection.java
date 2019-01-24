package com.example.user.goozixtest.util;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.Objects;

public class InternetConnection {
    // Check Internet Conection
    public static boolean checkConection(Context context) {
        return ((ConnectivityManager) Objects.requireNonNull(context.getSystemService
                (Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo() != null;
    }
}
