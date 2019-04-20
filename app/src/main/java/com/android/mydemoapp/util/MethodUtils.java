package com.android.mydemoapp.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
}
