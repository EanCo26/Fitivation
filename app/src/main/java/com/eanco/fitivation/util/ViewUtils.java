package com.eanco.fitivation.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class ViewUtils {

    public static String getTextViewString(Dialog view, int rId) {

        TextView textView = view.findViewById(rId);
        return textView.getText().toString();
    }

    public static String getTextViewString(View view, int rId) {

        TextView textView = view.findViewById(rId);
        return textView.getText().toString();
    }

    public static void setupSpinner(Spinner spinner, Context context, int rId) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context, rId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setSpinnerSelectionValue(Spinner spinner, String value) {
        if(ObjectUtils.isNotEmpty(spinner)){
            for (int position = 0; position < spinner.getCount(); position++) {
                if(StringUtils.equals(spinner.getItemAtPosition(position).toString(), value)) {
                    spinner.setSelection(position);
                    break;
                }
            }
        }
    }

    public static String getSpinnerSelectionValue(Spinner spinner) {
        if(ObjectUtils.isNotEmpty(spinner)){
            return spinner.getSelectedItem().toString();
        }
        return StringUtils.EMPTY;
    }
}
