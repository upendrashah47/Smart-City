package com.us.smartcity.uc;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.us.smartcity.R;
import com.us.smartcity.utils.Config;
import com.us.smartcity.utils.Pref;


public class AlertDailogView {
    // Handle Ok Button by current activity
    public static final int BUTTON_OK = 1;
    // Handle Cancel Button by current activity
    public static final int BUTTON_CANCEL = 2;
    public static Dialog dialog;

    public static Dialog showAlert(Context context, String message, String btnText) {
        return showAlert(context, context.getResources().getString(R.string.message), message, btnText, false, "", null, 1);
    }

    public static Dialog showAlert(Context context, String title, String message, String btnText) {
        return showAlert(context, title, message, btnText, false, "", null, 1);
    }

    public static Dialog showAlert(Context context, String message, String btnText, OnCustPopUpDialogButoonClickListener clickListener) {
        return showAlert(context, context.getString(R.string.message), message, btnText, false, "", clickListener, 1);
    }

    public static Dialog showAlert(Context context, String message, String btnTitle_1,
                                   boolean isCancelButton, String btnTitle_2, final OnCustPopUpDialogButoonClickListener clickListener, final int tag) {
        return showAlert(context, context.getString(R.string.message), message, btnTitle_1, isCancelButton, btnTitle_2, clickListener, tag);
    }

    // Create Custom popup Alert dailog here
    public static Dialog showAlert(final Context context,
                                   String title, String message, String btnTitle_1,
                                   boolean isCancelButton, String btnTitle_2, final OnCustPopUpDialogButoonClickListener clickListener, final int tag) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.uc_alertdialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txt_message = (TextView) dialog.findViewById(R.id.txtMessage);
        final Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        final Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        LinearLayout llCancel = (LinearLayout) dialog.findViewById(R.id.llCancel);
        TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitle);

        if (title != null && !title.equals(""))
            txtTitle.setText(title);

        txt_message.setText(message);
        btnOk.setText(btnTitle_1);
        btnCancel.setText(btnTitle_2);

        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.OnButtonClick(tag, AlertDailogView.BUTTON_OK);
                dialog.dismiss();
                Pref.setInt(context, Config.PREF_IS_INTERNET_DIALOG_OPEN, 0);
            }
        });

        if (isCancelButton) {
            llCancel.setVisibility(View.VISIBLE);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (clickListener != null)
                        clickListener.OnButtonClick(tag,
                                AlertDailogView.BUTTON_CANCEL);
                    dialog.dismiss();
                }
            });

        } else {
            llCancel.setVisibility(View.GONE);
        }
        return dialog;
    }

    // define Listener here
    public interface OnCustPopUpDialogButoonClickListener {
        public abstract void OnButtonClick(int tag, int buttonIndex);
    }
}