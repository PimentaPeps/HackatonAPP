package hackaton.com.br.hackatonapp.network.async;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import hackaton.com.br.hackatonapp.R;

/**
 * Created by gustefr on 22/03/2016.
 */
public class GetThumbnailsAsyncTask extends AsyncTask<Void, Void, Drawable> {
    private final Context context;
    private final ImageView imageView;

    public GetThumbnailsAsyncTask(Context context, ImageView imageView){
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        /**
         * show dialog
         */
        super.onPreExecute();
    }

    @Override
    protected Drawable doInBackground(Void... params) {

        InputStream is = null;
        try {
            is = (InputStream) new URL("http://image.prntscr.com/image/968dbce464684ac1a63c9efc84151287.png").getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(is, "src name");
        return d;
    }

    @Override
    protected void onPostExecute(Drawable result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imageView.setBackground(result);
        }
        super.onPostExecute(result);
    }
}
