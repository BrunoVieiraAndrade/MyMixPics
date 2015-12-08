package com.example.bruno.mymixpics.model;

/**
 * Created by Bruno on 12/3/2015.
 */
import java.util.List;

public class Recent {

    private Pagination pagination;
    private List<Media> data;

    public Pagination getPagination() {
        return pagination;
    }

    public List<Media> getMediaList() {
        return data;
    }

}
