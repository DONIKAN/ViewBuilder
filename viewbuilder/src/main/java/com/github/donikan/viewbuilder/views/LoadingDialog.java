package com.github.donikan.viewbuilder.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.donikan.viewbuilder.R;

/**
 * Created by DONIKAN on 22/05/2018.
 */

public class LoadingDialog extends Dialog {

    // Spin
    public static final int SPIN_ICON_1 = R.string.fa_spin_1;
    public static final int SPIN_ICON_2 = R.string.fa_spin_2;
    public static final int SPIN_ICON_3 = R.string.fa_spin_3;
    public static final int SPIN_ICON_4 = R.string.fa_spin_4;
    public static final int SPIN_ICON_5 = R.string.fa_spin_5;
    public static final int SPIN_ICON_6 = R.string.fa_spin_6;

    public static final float SPIN_SMALL_SIZE       = 25f;
    public static final float SPIN_MEDIUM_SIZE      = 50f;
    public static final float SPIN_LARGE_SIZE       = 75f;
    public static final float SPIN_EXTRA_LARGE_SIZE = 100f;

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

    public LoadingDialog(
            @NonNull Context context,
            String message,
            boolean cancelable,
            int orientation,
            int spinIcon,
            float spinSize,
            int spinColor,
            long spinRotateDuration,
            float messageSize,
            int messageColor) {
        super(context);
        this.mContext = context;
        this.mMessage = message;
        this.mCancelable = cancelable;
        this.mOrientation = orientation;
        this.mSpinIcon = spinIcon;
        this.mSpinSize = spinSize;
        this.mSpinColor = spinColor;
        this.mSpinRotateDuration = spinRotateDuration;
        this.mMessageSize = messageSize;
        this.mMessageColor = messageColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.loading_dialog);

        llContainer = findViewById(R.id.llContainer);
        fatvSpin = findViewById(R.id.fatvSpin);
        tvMessage = findViewById(R.id.tvMessage);

        setCancelable(mCancelable);

        if (fatvSpin != null) {
            try {
                fatvSpin.setText(mSpinIcon);
            } catch (Exception e) {

            }
            fatvSpin.setTextSize(mSpinSize);
            fatvSpin.setTextColor(mContext.getResources().getColor(mSpinColor));
        }
        if (tvMessage != null) {
            tvMessage.setText(mMessage);

            tvMessage.setTextSize(mMessageSize);
            tvMessage.setTextColor(mContext.getResources().getColor(mMessageColor));
//            tvMessage.
        }
        if (llContainer != null) llContainer.setOrientation(mOrientation);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // TODO: rotation
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(mSpinRotateDuration);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setFillAfter(true);

        if (fatvSpin != null) fatvSpin.setAnimation(rotate);
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        if (fatvSpin != null) fatvSpin.clearAnimation();
    }

}
