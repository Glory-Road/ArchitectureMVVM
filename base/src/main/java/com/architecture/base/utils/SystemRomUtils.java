package com.architecture.base.utils;

import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemRomUtils {
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";

    public static String getRomName() {
        if (hasSystemProperties("ro.miui.ui.version.name")
                || hasSystemProperties("ro.miui.ui.version.code")
                || hasSystemProperties("ro.miui.internal.storage")) {
            return ROM_MIUI;
        } else if (hasSystemProperties("ro.build.version.emui")) {
            return ROM_EMUI;
        } else if (hasSystemProperties("ro.build.version.opporom")) {
            return ROM_OPPO;
        } else if (hasSystemProperties("ro.smartisan.version")) {
            return ROM_SMARTISAN;
        } else if (hasSystemProperties("ro.vivo.os.version")) {
            return ROM_VIVO;
        } else if (Build.DISPLAY.contains(ROM_FLYME)) {
            return ROM_FLYME;
        }
        return Build.MANUFACTURER.toUpperCase();
    }

    private static boolean hasSystemProperties(String name) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty(name) != null;
        } catch (final IOException e) {
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // ignore all exception
                }
            }
        }
    }
}
