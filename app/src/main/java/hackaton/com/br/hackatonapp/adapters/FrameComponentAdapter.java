package hackaton.com.br.hackatonapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.ui.core.FrameComponent;
import hackaton.com.br.hackatonapp.volley.ImageRequest;
import hackaton.com.br.hackatonapp.volley.RequestQueue;
import hackaton.com.br.hackatonapp.volley.Response;
import hackaton.com.br.hackatonapp.volley.Volley;
import hackaton.com.br.hackatonapp.volley.VolleyError;

/**
 * Created by gustefr on 17/03/2016.
 */
public class FrameComponentAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<FrameComponent> frameComponentList;
    // Instantiate the RequestQueue.
    RequestQueue queue;

    public FrameComponentAdapter(Context context, ArrayList<FrameComponent> frameComponentList) {
        this.frameComponentList = frameComponentList;
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public int getCount() {
        return frameComponentList.size();
    }

    @Override
    public Object getItem(int position) {
        return frameComponentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.feed_component, null);
        }
        //((TextView)v.findViewById(R.id.textViewFrameComponent)).setText(frameComponentList.get(position).getVideoName());

        final ImageView mImageView;
        String url = frameComponentList.get(position).getUrlVideo();

        mImageView = (ImageView) v.findViewById(R.id.imageViewFrameComponent);

        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
//                        final double viewWidthToBitmapWidthRatio = (double)mImageView.getWidth() / (double)bitmap.getWidth();
//                        mImageView.getLayoutParams().height = (int) (bitmap.getHeight() * viewWidthToBitmapWidthRatio);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(imageRequest);

        //GetThumbnailsAsyncTask getThumbnailsAsyncTask = new GetThumbnailsAsyncTask(context);
        //getThumbnailsAsyncTask.execute();

        return v;
    }
}
