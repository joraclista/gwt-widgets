package com.github.joraclista.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joraclista.shared.model.JobVacancy;
import com.github.joraclista.shared.service.DouJobsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
public class DouJobsServiceImpl extends RemoteServiceServlet implements DouJobsService {



    public List<JobVacancy> getJobs(String city, String category) {

        try {
            Document doc = Jsoup.connect("https://jobs.dou.ua/vacancies/?city=" + city + "&category=" + category).get();
            Elements vacanciesList = doc.select("div[id='vacancyListId'] ul li");

            return vacanciesList.stream()
                    .map(element -> {
                        Elements title = element.select("div.title");
                        Elements shInfo = element.select("div.sh-info");

                        return JobVacancy.builder()
                                .company(title.select("a[href].company").text())
                                .info(shInfo.text())
                                .url(title.select("a[href].vt").attr("href"))
                                .position(title.select("a[href].vt").text())
                                .cities(title.select("span.cities").text())
                                .build();
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {}
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<String> getCities() {
        return getListFromJson("cities.json");
    }

    @Override
    public List<String> getCategories() {
        return getListFromJson("categories.json");
    }

    private List<String> getListFromJson(String jsonFileName) {
        try {
            String[] values = new ObjectMapper()
                    .readValue(this.getClass().getClassLoader().getResourceAsStream(jsonFileName), String[].class);
            return Arrays.asList(values);
        } catch (Exception e) {

        }
        return Collections.EMPTY_LIST;
    }

}