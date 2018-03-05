package com.github.joraclista.shared.service;

import com.github.joraclista.shared.model.JobVacancy;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("DouJobsService")
public interface DouJobsService extends RemoteService {

    List<JobVacancy> getJobs(String city, String category);

    List<String> getCities();

    List<String> getCategories();

}
