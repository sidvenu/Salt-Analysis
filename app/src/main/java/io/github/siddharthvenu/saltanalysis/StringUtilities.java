package io.github.siddharthvenu.saltanalysis;

import android.text.Html;
import android.text.Spanned;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by siddh on 17-03-2017.
 */

class StringUtilities {
    static String stringChangeHtml(String s) {
        return s.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    static Spanned formatString(String s) {
        if (s.contains("<u>")) {
            StringBuilder builder = new StringBuilder(s);
            int fromIndex = 0;
            while (fromIndex<builder.toString().length()) {
                int curIndex = builder.indexOf("{{", fromIndex);
                fromIndex = curIndex + 1;
                if(curIndex<0) break;
                if (curIndex < builder.toString().length())
                    builder.insert(curIndex, "</u>");
                curIndex = builder.indexOf("}}", fromIndex);
                fromIndex = curIndex + 1;
                if(curIndex<0) break;
                if (curIndex < builder.toString().length())
                    builder.insert(curIndex, "<u>");
            }

            return Html.fromHtml(stringChangeHtml(builder.toString()));
        }
        return Html.fromHtml(stringChangeHtml(s));
    }

    public static String md5(final String s) {
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
}
