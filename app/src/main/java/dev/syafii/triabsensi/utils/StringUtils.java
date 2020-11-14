package dev.syafii.triabsensi.utils;

import android.text.Html;

public class StringUtils {
    public static String formatHTML(String string) {
        return string.replace("<ul>", "")
                .replace("</ul>", "")
                .replace("<li>", "<p>• ")
                .replace("</li>", "</p>");
    }

    public static String removeSpace(String string) {
        return string.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "");
    }

    public static String clearHTML(String string) {
        try {
            return String.valueOf(Html.fromHtml(string));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return string;
    }
}
