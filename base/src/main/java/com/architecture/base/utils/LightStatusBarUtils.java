package com.architecture.base.utils;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class LightStatusBarUtils {
    private static final ILightStatusBar IMPL;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            IMPL = new RLightStatusBarImpl();
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            IMPL = new MLightStatusBarImpl();
        } else if (MIUILightStatusBarImpl.isMe()) {
            IMPL = new MIUILightStatusBarImpl();
        } else if (MeizuLightStatusBarImpl.isMe()) {
            IMPL = new MeizuLightStatusBarImpl();
        } else if (OppoLightStatusBarImpl.isMe()) {
            IMPL = new OppoLightStatusBarImpl();
        } else {
            IMPL = new BaseStatusBarImpl();
        }
    }

    static void setLightStatusBar(Window window, boolean lightStatusBar) {
        IMPL.setLightStatusBar(window, lightStatusBar);
    }

    interface ILightStatusBar {
        /**
         * Set whether ths status bar is light
         *
         * @param window         The window to set
         * @param lightStatusBar True if the status bar color is light
         */
        void setLightStatusBar(Window window, boolean lightStatusBar);
    }

    private static class BaseStatusBarImpl implements ILightStatusBar {

        @Override
        public void setLightStatusBar(Window window, boolean lightStatusBar) {

        }
    }

    private static class RLightStatusBarImpl extends BaseStatusBarImpl {
        @TargetApi(Build.VERSION_CODES.R)
        @Override
        public void setLightStatusBar(Window window, boolean lightStatusBar) {
            WindowInsetsController controller = window.getInsetsController();
            if (lightStatusBar) {
                if (controller != null) {
                    controller.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);
                }
            } else {
                if (controller != null) {
                    controller.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS);
                }
            }
        }
    }

    private static class MLightStatusBarImpl extends BaseStatusBarImpl {

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void setLightStatusBar(Window window, boolean lightStatusBar) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 设置浅色状态栏时的界面显示
            View decor = window.getDecorView();
            int ui = decor.getSystemUiVisibility();
            if (lightStatusBar) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);

        }
    }

    private static class MIUILightStatusBarImpl implements ILightStatusBar {

        static boolean isMe() {
            return SystemRomUtils.getRomName().equals(SystemRomUtils.ROM_MIUI);
        }

        @Override
        public void setLightStatusBar(Window window, boolean lightStatusBar) {
            Class<? extends Window> clazz = window.getClass();
            try {
                int darkModeFlag;
                Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                extraFlagField.invoke(window, lightStatusBar ? darkModeFlag : 0, darkModeFlag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class MeizuLightStatusBarImpl implements ILightStatusBar {
        static boolean isMe() {
            return SystemRomUtils.getRomName().equals(SystemRomUtils.ROM_FLYME);
        }

        @Override
        public void setLightStatusBar(Window window, boolean lightStatusBar) {
            WindowManager.LayoutParams winParams = window.getAttributes();
            String flagName = "MEIZU_FLAG_DARK_STATUS_BAR_ICON";
            try {
                Field f = winParams.getClass().getDeclaredField(flagName);
                f.setAccessible(true);
                int bits = f.getInt(winParams);
                Field f2 = winParams.getClass().getDeclaredField("meizuFlags");
                f2.setAccessible(true);
                int meizuFlags = f2.getInt(winParams);
                int oldFlags = meizuFlags;
                if (lightStatusBar) {
                    meizuFlags |= bits;
                } else {
                    meizuFlags &= ~bits;
                }
                if (oldFlags != meizuFlags) {
                    f2.setInt(winParams, meizuFlags);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private static class OppoLightStatusBarImpl implements ILightStatusBar {

        static boolean isMe() {
            return SystemRomUtils.getRomName().equals(SystemRomUtils.ROM_OPPO);
        }

        @Override
        public void setLightStatusBar(Window window, boolean lightStatusBar) {
            final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            int vis = window.getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (lightStatusBar) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                if (lightStatusBar) {
                    vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
                } else {
                    vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
                }
            }
            window.getDecorView().setSystemUiVisibility(vis);
        }
    }
}
