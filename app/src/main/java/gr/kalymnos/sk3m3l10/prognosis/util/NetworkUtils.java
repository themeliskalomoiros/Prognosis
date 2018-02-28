package gr.kalymnos.sk3m3l10.prognosis.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.Scanner;

/**
 * This class will be responsible for handling network operations.
 */

public interface NetworkUtils {

    public static final String CLASS_TAG = NetworkUtils.class.getSimpleName();

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response, null if no response
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        String response = null;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
        } catch (UnknownServiceException u){
            Log.e(CLASS_TAG, u.getMessage());
        } catch (IOException e){
            Log.e(CLASS_TAG,e.getMessage());
        } finally {
            urlConnection.disconnect();
        }
        return response;
    }
}
