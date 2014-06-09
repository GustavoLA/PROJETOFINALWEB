/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 7; i++) {
            images.add("Imgaem" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}