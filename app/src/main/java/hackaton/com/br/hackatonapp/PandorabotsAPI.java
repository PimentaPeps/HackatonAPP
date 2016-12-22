package hackaton.com.br.hackatonapp;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PandorabotsAPI {

    private String host = "";
    private String userkey = "";
    private String username = "";
    private String sessionid = "";
    private String TAG = "PandorabotsAPI";
    private String protocol = "https:";
    private String client_name = "";

    public PandorabotsAPI(String host, String username, String userkey, String client_name) {
        this.host = host;
        this.username = username;
        this.userkey = userkey;
        this.client_name = client_name;
    }

    public String readResponse (HttpResponse httpResp) {
        String response = "";
        try {
            int code = httpResp.getStatusLine().getStatusCode();
            InputStream is = httpResp.getEntity().getContent();
            BufferedReader inb = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder("");
            String line;
            String NL = System.getProperty("line.separator");
            while ((line = inb.readLine()) != null) {
                sb.append(line).append(NL);
            }
            inb.close();
            response = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public String talk(String botname, String input) {
        return debugBot(botname, input, false, false, false, false);
    }

    public String debugBot(String botname, String input, boolean reset, boolean trace, boolean recent , boolean createCustId) {
        String response = "";
        try {
            HttpClient client = new DefaultHttpClient();
            String url = protocol+"//" + host + "/talk/" + username + "/" + botname;
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
//            if (sessionid.length() > 0) nameValuePairs.add(new BasicNameValuePair("sessionid", sessionid));
//            if (client_name.length() > 0) nameValuePairs.add(new BasicNameValuePair ("client_name",client_name));
            nameValuePairs.add(new BasicNameValuePair("input", input));
            nameValuePairs.add(new BasicNameValuePair("user_key", userkey));
            if (reset) nameValuePairs.add(new BasicNameValuePair("reset", "true"));
//            if (trace) nameValuePairs.add(new BasicNameValuePair("trace", "true"));
            if (recent) nameValuePairs.add(new BasicNameValuePair("recent", "true"));

            String entityValue = URLEncodedUtils.format(nameValuePairs, HTTP.UTF_8);
            StringEntity entity = new StringEntity(entityValue, HTTP.UTF_8);
            entity.setContentType(URLEncodedUtils.CONTENT_TYPE);

            //HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs);
            request.setEntity(entity);
            HttpResponse httpResp = client.execute(request);
            String jsonStringResponse = readResponse(httpResp);
            JSONObject jsonObj = new JSONObject(jsonStringResponse);
            JSONArray responses = jsonObj.getJSONArray("responses");
            for (int i = 0; i < responses.length(); i++) response += " "+responses.getString(i);
            response = response.trim();
            sessionid = jsonObj.getString("sessionid");
            if (createCustId) {
                client_name = sessionid;
                return client_name;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;


    }
}