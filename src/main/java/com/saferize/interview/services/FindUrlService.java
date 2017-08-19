package com.saferize.interview.services;

import java.io.IOException;
import java.util.List;

/**
 * @author Aman Mahato
 */
public interface FindUrlService {

    int getLinkCounter();
    List<String> getUrlList();
    public boolean isValidUrl(String input);
    public String findPhilosophy(String article) throws IOException;
    public String getHopMessage();
}
