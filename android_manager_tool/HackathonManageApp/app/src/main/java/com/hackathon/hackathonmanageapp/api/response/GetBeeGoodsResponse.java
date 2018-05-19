package com.hackathon.hackathonmanageapp.api.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "products",
        "meta"
})
public class GetBeeGoodsResponse {

    @JsonProperty("products")
    private List<Product> products = null;
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("products")
    public List<Product> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "current_page",
            "total_pages",
            "total_count"
    })
    public static class Meta {

        @JsonProperty("current_page")
        private Integer currentPage;
        @JsonProperty("total_pages")
        private Integer totalPages;
        @JsonProperty("total_count")
        private Integer totalCount;

        @JsonProperty("current_page")
        public Integer getCurrentPage() {
            return currentPage;
        }

        @JsonProperty("current_page")
        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        @JsonProperty("total_pages")
        public Integer getTotalPages() {
            return totalPages;
        }

        @JsonProperty("total_pages")
        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        @JsonProperty("total_count")
        public Integer getTotalCount() {
            return totalCount;
        }

        @JsonProperty("total_count")
        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "title",
            "description",
            "imageUrl",
            "previewImageUrl",
            "slug",
            "barcodes",
            "barcode",
            "unitType",
            "soldBy",
            "amountPerUnit",
            "size",
            "status",
            "imageUrlBasename",
            "currency",
            "promotionStartsAt",
            "promotionEndsAt",
            "maxQuantity",
            "customerNotesEnabled",
            "price",
            "normalPrice",
            "nutritionalInfo",
            "productBrand",
            "productInfo",
            "packingSize",
            "descriptionHtml",
            "countryOfOrigin",
            "tags",
            "alcohol"
    })
    public static class Product {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("imageUrl")
        private String imageUrl;
        @JsonProperty("previewImageUrl")
        private String previewImageUrl;
        @JsonProperty("slug")
        private Object slug;
        @JsonProperty("barcodes")
        private List<Object> barcodes = null;
        @JsonProperty("barcode")
        private Object barcode;
        @JsonProperty("unitType")
        private String unitType;
        @JsonProperty("soldBy")
        private String soldBy;
        @JsonProperty("amountPerUnit")
        private String amountPerUnit;
        @JsonProperty("size")
        private String size;
        @JsonProperty("status")
        private String status;
        @JsonProperty("imageUrlBasename")
        private String imageUrlBasename;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("promotionStartsAt")
        private Object promotionStartsAt;
        @JsonProperty("promotionEndsAt")
        private Object promotionEndsAt;
        @JsonProperty("maxQuantity")
        private String maxQuantity;
        @JsonProperty("customerNotesEnabled")
        private Boolean customerNotesEnabled;
        @JsonProperty("price")
        private String price;
        @JsonProperty("normalPrice")
        private String normalPrice;
        @JsonProperty("nutritionalInfo")
        private Object nutritionalInfo;
        @JsonProperty("productBrand")
        private String productBrand;
        @JsonProperty("productInfo")
        private Object productInfo;
        @JsonProperty("packingSize")
        private String packingSize;
        @JsonProperty("descriptionHtml")
        private String descriptionHtml;
        @JsonProperty("countryOfOrigin")
        private Object countryOfOrigin;
        @JsonProperty("tags")
        private List<Object> tags = null;
        @JsonProperty("alcohol")
        private Boolean alcohol;

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("description")
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
            this.description = description;
        }

        @JsonProperty("imageUrl")
        public String getImageUrl() {
            return imageUrl;
        }

        @JsonProperty("imageUrl")
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @JsonProperty("previewImageUrl")
        public String getPreviewImageUrl() {
            return previewImageUrl;
        }

        @JsonProperty("previewImageUrl")
        public void setPreviewImageUrl(String previewImageUrl) {
            this.previewImageUrl = previewImageUrl;
        }

        @JsonProperty("slug")
        public Object getSlug() {
            return slug;
        }

        @JsonProperty("slug")
        public void setSlug(Object slug) {
            this.slug = slug;
        }

        @JsonProperty("barcodes")
        public List<Object> getBarcodes() {
            return barcodes;
        }

        @JsonProperty("barcodes")
        public void setBarcodes(List<Object> barcodes) {
            this.barcodes = barcodes;
        }

        @JsonProperty("barcode")
        public Object getBarcode() {
            return barcode;
        }

        @JsonProperty("barcode")
        public void setBarcode(Object barcode) {
            this.barcode = barcode;
        }

        @JsonProperty("unitType")
        public String getUnitType() {
            return unitType;
        }

        @JsonProperty("unitType")
        public void setUnitType(String unitType) {
            this.unitType = unitType;
        }

        @JsonProperty("soldBy")
        public String getSoldBy() {
            return soldBy;
        }

        @JsonProperty("soldBy")
        public void setSoldBy(String soldBy) {
            this.soldBy = soldBy;
        }

        @JsonProperty("amountPerUnit")
        public String getAmountPerUnit() {
            return amountPerUnit;
        }

        @JsonProperty("amountPerUnit")
        public void setAmountPerUnit(String amountPerUnit) {
            this.amountPerUnit = amountPerUnit;
        }

        @JsonProperty("size")
        public String getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(String size) {
            this.size = size;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
            this.status = status;
        }

        @JsonProperty("imageUrlBasename")
        public String getImageUrlBasename() {
            return imageUrlBasename;
        }

        @JsonProperty("imageUrlBasename")
        public void setImageUrlBasename(String imageUrlBasename) {
            this.imageUrlBasename = imageUrlBasename;
        }

        @JsonProperty("currency")
        public String getCurrency() {
            return currency;
        }

        @JsonProperty("currency")
        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @JsonProperty("promotionStartsAt")
        public Object getPromotionStartsAt() {
            return promotionStartsAt;
        }

        @JsonProperty("promotionStartsAt")
        public void setPromotionStartsAt(Object promotionStartsAt) {
            this.promotionStartsAt = promotionStartsAt;
        }

        @JsonProperty("promotionEndsAt")
        public Object getPromotionEndsAt() {
            return promotionEndsAt;
        }

        @JsonProperty("promotionEndsAt")
        public void setPromotionEndsAt(Object promotionEndsAt) {
            this.promotionEndsAt = promotionEndsAt;
        }

        @JsonProperty("maxQuantity")
        public String getMaxQuantity() {
            return maxQuantity;
        }

        @JsonProperty("maxQuantity")
        public void setMaxQuantity(String maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        @JsonProperty("customerNotesEnabled")
        public Boolean getCustomerNotesEnabled() {
            return customerNotesEnabled;
        }

        @JsonProperty("customerNotesEnabled")
        public void setCustomerNotesEnabled(Boolean customerNotesEnabled) {
            this.customerNotesEnabled = customerNotesEnabled;
        }

        @JsonProperty("price")
        public String getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(String price) {
            this.price = price;
        }

        @JsonProperty("normalPrice")
        public String getNormalPrice() {
            return normalPrice;
        }

        @JsonProperty("normalPrice")
        public void setNormalPrice(String normalPrice) {
            this.normalPrice = normalPrice;
        }

        @JsonProperty("nutritionalInfo")
        public Object getNutritionalInfo() {
            return nutritionalInfo;
        }

        @JsonProperty("nutritionalInfo")
        public void setNutritionalInfo(Object nutritionalInfo) {
            this.nutritionalInfo = nutritionalInfo;
        }

        @JsonProperty("productBrand")
        public String getProductBrand() {
            return productBrand;
        }

        @JsonProperty("productBrand")
        public void setProductBrand(String productBrand) {
            this.productBrand = productBrand;
        }

        @JsonProperty("productInfo")
        public Object getProductInfo() {
            return productInfo;
        }

        @JsonProperty("productInfo")
        public void setProductInfo(Object productInfo) {
            this.productInfo = productInfo;
        }

        @JsonProperty("packingSize")
        public String getPackingSize() {
            return packingSize;
        }

        @JsonProperty("packingSize")
        public void setPackingSize(String packingSize) {
            this.packingSize = packingSize;
        }

        @JsonProperty("descriptionHtml")
        public String getDescriptionHtml() {
            return descriptionHtml;
        }

        @JsonProperty("descriptionHtml")
        public void setDescriptionHtml(String descriptionHtml) {
            this.descriptionHtml = descriptionHtml;
        }

        @JsonProperty("countryOfOrigin")
        public Object getCountryOfOrigin() {
            return countryOfOrigin;
        }

        @JsonProperty("countryOfOrigin")
        public void setCountryOfOrigin(Object countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
        }

        @JsonProperty("tags")
        public List<Object> getTags() {
            return tags;
        }

        @JsonProperty("tags")
        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        @JsonProperty("alcohol")
        public Boolean getAlcohol() {
            return alcohol;
        }

        @JsonProperty("alcohol")
        public void setAlcohol(Boolean alcohol) {
            this.alcohol = alcohol;
        }

    }
}