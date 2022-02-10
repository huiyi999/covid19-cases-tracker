package com.covidtracker.covidtracker.api;

import com.covidtracker.covidtracker.models.LocationStats;
import com.covidtracker.covidtracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class trackerController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<LocationStats> allStats = new ArrayList<>();
        allStats = coronavirusDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stats -> stats.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stats -> stats.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }

}
