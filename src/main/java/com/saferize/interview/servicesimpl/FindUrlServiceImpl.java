package com.saferize.interview.servicesimpl;

/**
 * @modified by Aman Mahato
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.saferize.interview.services.FindUrlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class FindUrlServiceImpl implements FindUrlService {

    private int linkCounter;

    private int MAXIMUM_NUMBER_HOP=500;

    private String hopMessage=null;

    @Override
    public String getHopMessage() {
        return hopMessage;
    }

    @Override
    public int getLinkCounter() {
        return linkCounter;
    }

    @Override
    public List<String> getUrlList() {
        return urlList;
    }

    private List<String> urlList;

    public FindUrlServiceImpl() {
        this.urlList = new ArrayList<>();
        this.linkCounter=0;
    }

    @Override
    public String findPhilosophy(String article) throws IOException {
        if (!article.equalsIgnoreCase("http://en.wikipedia.org/wiki/Philosophy")) {
            String nextLink = grabNextLink(article);
            urlList.add(nextLink);
            linkCounter++;
            if(linkCounter>MAXIMUM_NUMBER_HOP){
                hopMessage="HOP LIMIT EXCEEDED";
                return "";
            }
            findPhilosophy(nextLink);
        }
        return "";
    }

    private String grabNextLink(String article) throws IOException {
        String nextLink = "";
        URL url = stringToURL(article);
        Document doc = Jsoup.parse(url, 100000);
        Elements links = doc.select("p > a");

        for (int i = 0; i < links.size(); i++) {
            if (isFirstRealLink(links.get(i).toString())) {
                nextLink = links.get(i).toString();
                break;
            }
        }
        return "http://en.wikipedia.org" + nextLink.substring(9, nextLink.indexOf("\"", 10));
    }

   @Override
    public boolean isValidUrl(String input) {
        return (input.startsWith("https://en.wikipedia.org/wiki/") || input.startsWith("http://en.wikipedia.org/wiki/"));
    }

    private boolean isFirstRealLink(String link) {
        return (link.contains("wiki") && !link.contains("Greek") && !link.contains("Latin") && !link.contains("wiktionary"));
    }

    /**
     * Interprets a string as a URL.  If the string isn't a valid URL, prints an error message and returns null.
     */
    public URL stringToURL(String string) {
        try {
            return new URL(string);
        } catch (MalformedURLException exception) {
            System.err.println("invalid URL: " + string + ": " + exception);
            return null;
        }
    }
}