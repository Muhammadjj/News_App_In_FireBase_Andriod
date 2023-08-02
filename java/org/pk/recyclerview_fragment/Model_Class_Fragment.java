package org.pk.recyclerview_fragment;

public class Model_Class_Fragment {

    String Image, Link, Name;

    public Model_Class_Fragment() {
    }

    public Model_Class_Fragment(String image, String link, String name) {
        Image = image;
        Link = link;
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
