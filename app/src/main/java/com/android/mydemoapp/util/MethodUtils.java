package com.android.mydemoapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class MethodUtils {

    private static final String SP_DIR_NAME ="SP" ;

    /**
     * @param context
     * @param fileName skin_beta2.json
     * @param key
     * @return
     */
    public int getValueByJson(Context context, String fileName, String key) {
        File jsonFile = new File(context.getFilesDir().getParent() + File.separator + SP_DIR_NAME, "skin_beta2.json");
        FileInputStream fis = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(jsonFile);
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1 << 13); // 8K
            int i1;
            while ((i1 = channel.read(buffer)) != -1) {
                buffer.flip();
                bao.write(buffer.array(), 0, i1);
                buffer.clear();
            }

            String content = bao.toString();
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.getInt(key);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException("not a json file");
        } finally {
            try {
                fis.close();
                bao.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    @SuppressLint("MissingPermission")
    public static String getUUID() {

        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serial = android.os.Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

}
