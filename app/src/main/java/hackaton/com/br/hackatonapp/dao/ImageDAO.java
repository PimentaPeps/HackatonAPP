package hackaton.com.br.hackatonapp.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.volley.ImageRequest;
import hackaton.com.br.hackatonapp.volley.RequestQueue;
import hackaton.com.br.hackatonapp.volley.Response;
import hackaton.com.br.hackatonapp.volley.Volley;
import hackaton.com.br.hackatonapp.volley.VolleyError;

/**
 * Created by gustefr on 13/04/2016.
 */
public class ImageDAO {

    RequestQueue queue;
    Context context;

    public ImageDAO(Context context) {
        this.context = context;
    }

    public void getImageURLAsync(final ImageView image, String url, boolean local) {
        if (!local) {
            // Instantiate the RequestQueue.
            queue = Volley.newRequestQueue(context);
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest imageRequest = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            image.setImageBitmap(bitmap);
//                        final double viewWidthToBitmapWidthRatio = (double)mImageView.getWidth() / (double)bitmap.getWidth();
//                        mImageView.getLayoutParams().height = (int) (bitmap.getHeight() * viewWidthToBitmapWidthRatio);
                        }
                    }, 0, 0, ImageView.ScaleType.FIT_XY, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            queue.add(imageRequest);
        } else
            getImageResource(image, url);
    }

    public void getImageResource(ImageView imageView, String url) {
        imageView.setBackgroundResource(context.getResources().getIdentifier(url, "drawable", context.getPackageName()));
    }
}
