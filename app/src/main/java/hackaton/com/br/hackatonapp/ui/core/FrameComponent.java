package hackaton.com.br.hackatonapp.ui.core;

/**
 * Created by gustefr on 17/03/2016.
 */
public class FrameComponent {

    private String videoName;
    private String urlVideo;
    private int numLikes;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }
}
