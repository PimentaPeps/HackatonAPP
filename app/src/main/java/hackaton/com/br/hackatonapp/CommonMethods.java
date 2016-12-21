package hackaton.com.br.hackatonapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gustefr on 23/03/2016.
 */
public class CommonMethods {
    public String formatURLSpacesToPluses(String originalString){
        return originalString.replace(" ", "+");
    }

    public void copyAssets(Context context) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;

        try {
            files = assetManager.list("");
        }

        catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }

        for(String prova : files) {
            InputStream in = null;
            OutputStream out = null;

            try {
                in = assetManager.open(prova);
                //CRIAR ARQUIVO ou conseguir gravar em outro path fora da pasta data
                File dir = new File(Environment.getExternalStorageDirectory() +"/sdcard/backup/");
                dir.mkdir();
                File outFile = new File(dir, prova);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            }

            catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + prova, e);
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;

        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
