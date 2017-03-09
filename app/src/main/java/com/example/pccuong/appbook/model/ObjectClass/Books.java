package com.example.pccuong.appbook.model.ObjectClass;

import java.util.List;

/**
 * Created by PCCuong on 3/1/2017.
 */

public class Books {
    int id;
    int categories_id;
    int thuonghieu_id;

    int readingbook;
    String imagebig;
    String imagenho;
    List<ClassChiTiet> classChiTiets;

    public List<ClassChiTiet> getClassChiTiets() {
        return classChiTiets;
    }

    public void setClassChiTiets(List<ClassChiTiet> classChiTiets) {
        this.classChiTiets = classChiTiets;
    }

    public String getImagenho() {
        return imagenho;
    }

    public void setImagenho(String imagenho) {
        this.imagenho = imagenho;
    }

    String title;
    String cover;


    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThuonghieu_id() {
        return thuonghieu_id;
    }

    public void setThuonghieu_id(int thuonghieu_id) {
        this.thuonghieu_id = thuonghieu_id;
    }

    public int getReadingbook() {
        return readingbook;
    }

    public void setReadingbook(int readingbook) {
        this.readingbook = readingbook;
    }

    public String getImagebig() {
        return imagebig;
    }

    public void setImagebig(String imagebig) {
        this.imagebig = imagebig;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


}
