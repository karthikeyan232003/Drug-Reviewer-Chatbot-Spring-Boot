package com.example.pharmapulse.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScraperService {

    public String scrape(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.body().text();
    }
}