package com.saferize.interview.servicesimpl;

/**
 * @author Ramona Harrison
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

    //Testing
    /*public static void main(String[] args) throws IOException {
        FindUrlServiceImpl test=new FindUrlServiceImpl();
        String input="https://en.wikipedia.org/wiki/Friends";
        if (!test.isValidUrl(input)) {
            System.out.println("Please format input as a valid URL, for example: http://en.wikipedia.org/wiki/Nocturnality\n");
        }
        test.findPhilosophy(input);
        System.out.println(test.getLinkCounter());
        test.getUrlList().forEach(i-> System.out.println(i));
    }*/

    @Override
    public String findPhilosophy(String article) throws IOException {
        if (!article.equalsIgnoreCase("http://en.wikipedia.org/wiki/Philosophy")) {
            String nextLink = grabNextLink(article);
            urlList.add(nextLink);
            linkCounter++;
            findPhilosophy(nextLink);
            //System.out.println("was here");
        }
        return "";
    }

    private String grabNextLink(String article) throws IOException {
        String nextLink = "";
        URL url = stringToURL(article);            // builds URL from string
        Document doc = Jsoup.parse(url, 100000);        // parses wiki page, times out after 100 seconds
        Elements links = doc.select("p > a");           // selects only links within <p> tags

        for (int i = 0; i < links.size(); i++) {        // chooses the first suitable link
            if (isFirstRealLink(links.get(i).toString())) {
                nextLink = links.get(i).toString();
                break;
            }
        }
        return "http://en.wikipedia.org" + nextLink.substring(9, nextLink.indexOf("\"", 10));
    }

   /* public void printTitle(String article) {
        // formats titles for printing to the console
        System.out.println("         ▼\n      " + article.substring(29).replace("_", " "));
    }*/

   @Override
    public boolean isValidUrl(String input) {
        return (input.startsWith("https://en.wikipedia.org/wiki/") || input.startsWith("http://en.wikipedia.org/wiki/"));
    }

    private boolean isFirstRealLink(String link) {
        // filters out most language and pronunciation and non-wiki links
        return (link.contains("wiki") && !link.contains("Greek") && !link.contains("Latin") && !link.contains("wiktionary"));
    }

    /**
     * Reads from 'reader' by 'blockSize' until end-of-stream, and returns its complete contents.
     */
    private String readAll(InputStreamReader reader, int blockSize) throws IOException {
        final char buffer[] = new char[blockSize];
        StringBuilder builder = new StringBuilder();
        while (true) {
            final int readSize = reader.read(buffer);
            if (readSize >= 0)
                builder.append(buffer, 0, readSize);
            else
                break;
        }
        return builder.toString();
    }

    /**
     * Returns from 'reader' until end-of-stream, and returns its complete contents.
     */
    private String readAll(InputStreamReader reader) throws IOException {
        return readAll(reader, 1024 * 1024);
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

    /**
     * Retrieves the body of a URL.
     *
     * Opens a connection to the URL, makes a request, and retrieves the response.  Returns the body.  If the
     * URL cannot be opened or the response cannot be read, prints an error message and returns null.
     */
    private String get(URL url) {
        try {
            final URLConnection connection = url.openConnection();
            final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            try {
                return readAll(reader);
            } finally {
                reader.close();
            }
        } catch (IOException exception) {
            System.err.println("can't open URL: " + url + ": " + exception);
            return null;
        }
    }

}
