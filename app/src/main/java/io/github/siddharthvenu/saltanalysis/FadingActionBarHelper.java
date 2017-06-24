package io.github.siddharthvenu.saltanalysis;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelperBase;

/**
 * Created by siddh on 18-03-2017.
 */

final class FadingActionBarHelper extends FadingActionBarHelperBase {

    private ActionBar mActionBar;

    @Override
    public void initActionBar(Activity activity) {
        mActionBar = getActionBar(activity);
        super.initActionBar(activity);
    }

    private ActionBar getActionBar(Activity activity) {
        if (activity instanceof AppCompatActivity) {
            return ((AppCompatActivity) activity).getSupportActionBar();
        }
        ActionBar actionBar = getActionBarWithReflection(activity, "getSupportActionBar");
        if (actionBar == null) {
            throw new RuntimeException("Activity should derive from ActionBarActivity "
                    + "or implement a method called getSupportActionBar");
        }
        return actionBar;
    }

    @Override
    protected int getActionBarHeight() {
        return mActionBar.getHeight();
    }

    @Override
    protected boolean isActionBarNull() {
        return mActionBar == null;
    }

    @Override
    protected void setActionBarBackgroundDrawable(Drawable drawable) {
        mActionBar.setBackgroundDrawable(drawable);
    }
}
