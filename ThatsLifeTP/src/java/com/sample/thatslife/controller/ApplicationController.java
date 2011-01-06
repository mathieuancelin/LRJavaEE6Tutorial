package com.sample.thatslife.controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mathieuancelin
 */
@Named
@RequestScoped
public class ApplicationController implements Serializable {

    private String text = "";
    
    public void doNavigate() {
        text = "Hello World!";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
 
}
