package com.example.fypproject;

import java.util.Date;

public class post {
    public String Username,postContent, image, video;
    Date post;


    post(String Username,String postContent,String image, String video,Date post){
        this.Username= Username;
        this.postContent= postContent;
        this.image= image;
        this.video= video;
        this.post = post;


    }


}
