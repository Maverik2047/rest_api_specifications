package models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class UserDataResponseModel {


    private String job;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
