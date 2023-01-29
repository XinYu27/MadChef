package com.example.madchef.Models;

public class PostObject {
    private String postid;
    private String publisher;
    private String postimage;
    private String caption;
    private String recipe;
    private String ingredient;
    private String tool;
    private String duration;

    public PostObject(String postid, String publisher, String postimage, String caption, String recipe, String ingredient, String tool, String duration) {
        this.postid = postid;
        this.publisher = publisher;
        this.postimage = postimage;
        this.caption = caption;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.tool = tool;
        this.duration = duration;
    }

    public PostObject() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
