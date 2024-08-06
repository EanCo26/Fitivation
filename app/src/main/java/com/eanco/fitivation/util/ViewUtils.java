package com.eanco.fitivation.util;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

public class ViewUtils {

    public static String getTextViewString(Dialog view, int rId) {

        TextView textView = view.findViewById(rId);
        return textView.getText().toString();
    }

    public static String getTextViewString(View view, int rId) {

        TextView textView = view.findViewById(rId);
        return textView.getText().toString();
    }
}
