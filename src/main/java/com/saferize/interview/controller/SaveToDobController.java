package com.saferize.interview.controller;

import com.saferize.interview.dto.UrlDto;
import com.saferize.interview.model.SaferizeInfo;
import com.saferize.interview.services.FindUrlService;
import com.saferize.interview.services.PathInfoToDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

/**
 * @author Aman Mahato
 */
@Controller
@RequestMapping("/saveToDob")
public class SaveToDobController {

    @Autowired
    private FindUrlService findUrlService;

    @Autowired
    private PathInfoToDbService pathInfoToDbService;

    @RequestMapping(method = RequestMethod.POST)
    public String saveToDb(@ModelAttribute("urlDto") @Validated UrlDto urlDtoFromBack, Model modelAfterDbPush, Model errorMessage) {
        String input = urlDtoFromBack.getUrl();
        errorMessage.addAttribute("startFromPhilosophy", false);
        errorMessage.addAttribute("urlNotValid", false);
        errorMessage.addAttribute("hopExceeded", false);
        if (input.equalsIgnoreCase("https://en.wikipedia.org/wiki/Philosophy")) {
            errorMessage.addAttribute("startFromPhilosophy", true);
            return "webpage";
        }
        if (!findUrlService.isValidUrl(input)) {
            System.out.println("Please format input as a valid URL");
            errorMessage.addAttribute("urlNotValid", true);
            return "webpage";
        }
        try {
            findUrlService.findPhilosophy(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> pathUrls = findUrlService.getUrlList();
        for (String individualUrl : pathUrls) {
            SaferizeInfo saferizeInfo = new SaferizeInfo();
            saferizeInfo.setUrl(individualUrl);
            pathInfoToDbService.addUrlToDb(saferizeInfo);
        }
        modelAfterDbPush.addAttribute("AllPath", pathUrls);
        modelAfterDbPush.addAttribute("count", findUrlService.getLinkCounter());
        return "path";
    }
}