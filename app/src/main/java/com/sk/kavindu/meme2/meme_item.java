package com.sk.kavindu.meme2;

import java.util.Comparator;

public class meme_item implements Comparable<meme_item> {
    private String image_url;
    private String mtitle;

    public meme_item(String imageurl, String content) {
        image_url = imageurl;
        mtitle = content;

    }
    public String getImageUrl() {
        return image_url;
    }


    @Override
    public int compareTo(meme_item o) {
        return mtitle.compareTo(o.mtitle);
    }
}
