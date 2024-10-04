package com.goutam.CarSpringProject;

import org.springframework.stereotype.Component;

@Component
public class Engine
{
    private String engineModel = "BSIII";
    private int cc = 125;

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }
}
