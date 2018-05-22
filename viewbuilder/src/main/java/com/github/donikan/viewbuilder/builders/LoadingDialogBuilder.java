package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.donikan.viewbuilder.R;
import com.github.donikan.viewbuilder.views.FontAwesomeTextView;
import com.github.donikan.viewbuilder.views.LoadingDialog;

/**
 * Created by DONIKAN on 22/05/2018.
 */
public class LoadingDialogBuilder {
    private Context mContext;
    private String mMessage;
    private boolean mCancelable;
    private int mOrientation;
    private int mSpinIcon;
    private float mSpinSize;
    private int mSpinColor;
    private long mSpinRotateDuration;
    private float mMessageSize;
    private int mMessageColor;

    private LinearLayout llContainer;
    private FontAwesomeTextView fatvSpin;
    private TextView tvMessage;

    public LoadingDialogBuilder(@NonNull Context mContext) {
        this.mContext = mContext;
        this.mMessage = "Loading ...";
        this.mCancelable = true;
        this.mOrientation = LinearLayoutManager.HORIZONTAL;
        this.mSpinIcon = LoadingDialog.SPIN_ICON_4;
        this.mSpinSize = LoadingDialog.SPIN_MEDIUM_SIZE;
        this.mSpinColor = R.color.colorAccent;
        this.mSpinRotateDuration = 800;
        this.mMessageSize = 16f;
        this.mMessageColor = android.R.color.black;
    }

    public LoadingDialogBuilder(@NonNull Context context, String message) {
        this(context);
        this.mMessage = message;
    }

    public LoadingDialogBuilder setCancelable(boolean cancelable) {
        this.mCancelable = cancelable;
        return this;
    }

    public LoadingDialogBuilder setMessage(String mMessage) {
        this.mMessage = mMessage;
        return this;
    }

    public LoadingDialogBuilder setOrientation(int orientation) {
        mOrientation = orientation;
        return this;
    }

    public LoadingDialogBuilder setSpinIcon(int icon) {
        this.mSpinIcon = icon;
        return this;
    }

    public LoadingDialogBuilder setSpinSize(float size) {
        this.mSpinSize = size;
        return this;
    }

    public LoadingDialogBuilder setSpinColor(@ColorRes int color) {
        this.mSpinColor = color;
        return this;
    }

    public LoadingDialogBuilder setDuration(long duration) {
        this.mSpinRotateDuration = duration;
        return this;
    }

    public LoadingDialogBuilder setMessageize(float size) {
        this.mMessageSize = size;
        return this;
    }

    public LoadingDialogBuilder setMessageColor(@ColorRes int color) {
        this.mMessageColor = color;
        return this;
    }

    public LoadingDialog create() {
        return new LoadingDialog(
                mContext,
                mMessage,
                mCancelable,
                mOrientation,
                mSpinIcon,
                mSpinSize,
                mSpinColor,
                mSpinRotateDuration,
                mMessageSize,
                mMessageColor);
    }

}
