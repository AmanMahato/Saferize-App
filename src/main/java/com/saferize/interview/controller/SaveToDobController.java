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
import java.util.ArrayList;
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
    public String saveToDb(@ModelAttribute("urlDto") @Validated UrlDto urlDtoFromBack, Model modelAfterDbPush){
        //System.out.println(urlDtoFromBack.getUrl());
        //List<String> pathUrls=new ArrayList<>();
        String input=urlDtoFromBack.getUrl();
        if (!findUrlService.isValidUrl(input)) {
            System.out.println("Please format input as a valid URL, for example: http://en.wikipedia.org/wiki/Nocturnality\n");
        }
        try {
            findUrlService.findPhilosophy(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(findUrlService.getLinkCounter());
        //findUrlService.getUrlList().forEach(i-> System.out.println(i));
        //findUrlService.getUrlList().forEach(i->pathUrls.add(i));
        //SaferizeInfo saferizeInfo=new SaferizeInfo();
        //saferizeInfo.setUrl();
        //pathInfoToDbService.addUrlToDb();
        Integer id=1;
        List<String> pathUrls=findUrlService.getUrlList();
        for(String individualUrl:pathUrls){
            SaferizeInfo saferizeInfo=new SaferizeInfo();
            saferizeInfo.setUrl(individualUrl);
            saferizeInfo.setId(id++);
            pathInfoToDbService.addUrlToDb(saferizeInfo);
        }
        return "path";
    }
}
