package hackaton.com.br.hackatonapp.network.core.youtuberesponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gustefr on 22/03/2016.
 */
public class Thumbnails {
    @SerializedName("default")
    private Default _default;

    private Default medium;
    private Default high;

    public Default get_default() {
        return _default;
    }

    public void set_default(Default _default) {
        this._default = _default;
    }

    public Default getMedium() {
        return medium;
    }

    public void setMedium(Default medium) {
        this.medium = medium;
    }

    public Default getHigh() {
        return high;
    }

    public void setHigh(Default high) {
        this.high = high;
    }
}
