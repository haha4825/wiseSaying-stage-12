package org.back;

public class WiseSaying{
    private Long id;
    private String saying;
    private String author;

    public WiseSaying(String saying, String author) {
        this.saying = saying;
        this.author = author;
    }

    public WiseSaying(Long id, String saying, String author) {
        this.id = id;
        this.saying = saying;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getSaying() {
        return saying;
    }

    public String getAuthor() {
        return author;
    }

    public void modify(String newSaying, String newAuthor){
        this.saying = newSaying;
        this.author = newAuthor;
    }
}
