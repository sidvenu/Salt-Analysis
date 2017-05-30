package io.github.siddharthvenu.saltanalysis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by siddh on 17-03-2017.
 */

class ProjectUtilities {

    private static String stringChangeHtml(String str) {
        return str.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    static Spanned formatString(String str) {
        if (str.contains("<u>")) {
            StringBuilder builder = new StringBuilder(str);
            int fromIndex = 0;
            while (fromIndex < builder.toString().length()) {
                int curIndex = builder.indexOf("{{", fromIndex);
                fromIndex = curIndex + 1;
                if (curIndex < 0) break;
                if (curIndex < builder.toString().length())
                    builder.insert(curIndex, "</u>");
                curIndex = builder.indexOf("}}", fromIndex);
                fromIndex = curIndex + 1;
                if (curIndex < 0) break;
                if (curIndex < builder.toString().length())
                    builder.insert(curIndex, "<u>");
            }

            return Html.fromHtml(stringChangeHtml(builder.toString()));
        }
        return Html.fromHtml(stringChangeHtml(str));
    }

    static String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
/*
    static AdView getNewAdInstance(Context context, String adUnitId) throws IllegalAccessException, InstantiationException {
        final AdView adView = new AdView(context);
        adView.setVisibility(View.GONE);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(adUnitId);
        Log.v("HAHA","LOL");
        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        AdRequest.Builder requestBuilder = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9DE01AF95F35E03D3796E01505E5FFD4")
                .addTestDevice("FE818379EBC237735090013093B3F9A2");
        requestBuilder.addTestDevice(ProjectUtilities.md5(android_id).toUpperCase());

        AdRequest request = requestBuilder.build();
        adView.loadAd(request);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.v("HAHA", "ad failed to load");
                adView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }
        });
        return adView;
    }
*/
    static boolean isGeneralExpt(Experiment e) {
        return TextUtils.isEmpty(e.getObservation()) && TextUtils.isEmpty(e.getConclusion());
    }
}
