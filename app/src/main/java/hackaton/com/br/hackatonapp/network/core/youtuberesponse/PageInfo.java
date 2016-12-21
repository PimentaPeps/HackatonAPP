package hackaton.com.br.hackatonapp.network.core.youtuberesponse;

/**
 * Created by gustefr on 22/03/2016.
 */
public class PageInfo {
    private String totalResults;
    private String resultsPerPage;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(String resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
