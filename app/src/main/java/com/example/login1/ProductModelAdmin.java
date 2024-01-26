package com.example.login1;
import java.io.Serializable;

//each category different model class should be created
public class ProductModelAdmin implements Serializable {

    String admname, admdescription, type;
    int price;
    String documentId;
    String admimg_url;

    public ProductModelAdmin() {
    }

    public ProductModelAdmin(String name, String description, int price, String img_url, String documentId) {
        this.admname = name;
        this.admdescription = description;
        this.price = price;
        this.type = type;
        this.admimg_url = img_url;
        this.documentId=documentId;
    }
    //  public int getProductId(){return productId;}

    public String getName() {
        return admname;
    }

    public void setName(String name) {
        this.admname = name;
    }

    public String getDescription() {
        return admdescription;
    }

    public void setDescription(String description) {
        this.admdescription = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return admimg_url;
    }

    public void setImg_url(String img_url) {
        this.admimg_url = img_url;
    }

    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
