package com.hackathon.hackathonmanageapp.api.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Items",
        "Count",
        "ScannedCount"
})
public class GetGoodsListResponse {

    @JsonProperty("Items")
    private List<Item> items = null;
    @JsonProperty("Count")
    private Integer count;
    @JsonProperty("ScannedCount")
    private Integer scannedCount;

    @JsonProperty("Items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("Items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonProperty("Count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("Count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("ScannedCount")
    public Integer getScannedCount() {
        return scannedCount;
    }

    @JsonProperty("ScannedCount")
    public void setScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "price",
            "source"
    })
    public static class Competitor {

        @JsonProperty("price")
        private String price;
        @JsonProperty("source")
        private String source;

        @JsonProperty("price")
        public String getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(String price) {
            this.price = price;
        }

        @JsonProperty("source")
        public String getSource() {
            return source;
        }

        @JsonProperty("source")
        public void setSource(String source) {
            this.source = source;
        }

    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "competitor",
            "imageUrl",
            "createtime",
            "last_price",
            "updatetime",
            "recommend_status",
            "stock",
            "price",
            "id",
            "name"
    })
    public static class Item {

        @JsonProperty("competitor")
        private List<Competitor> competitor = null;
        @JsonProperty("imageUrl")
        private String imageUrl;
        @JsonProperty("createtime")
        private String createtime;
        @JsonProperty("last_price")
        private String lastPrice;
        @JsonProperty("updatetime")
        private String updatetime;
        @JsonProperty("recommend_status")
        private String recommendStatus;
        @JsonProperty("stock")
        private String stock;
        @JsonProperty("price")
        private String price;
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;

        @JsonProperty("competitor")
        public List<Competitor> getCompetitor() {
            return competitor;
        }

        @JsonProperty("competitor")
        public void setCompetitor(List<Competitor> competitor) {
            this.competitor = competitor;
        }

        @JsonProperty("imageUrl")
        public String getImageUrl() {
            return imageUrl;
        }

        @JsonProperty("imageUrl")
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @JsonProperty("createtime")
        public String getCreatetime() {
            return createtime;
        }

        @JsonProperty("createtime")
        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        @JsonProperty("last_price")
        public String getLastPrice() {
            return lastPrice;
        }

        @JsonProperty("last_price")
        public void setLastPrice(String lastPrice) {
            this.lastPrice = lastPrice;
        }

        @JsonProperty("updatetime")
        public String getUpdatetime() {
            return updatetime;
        }

        @JsonProperty("updatetime")
        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        @JsonProperty("recommend_status")
        public String getRecommendStatus() {
            return recommendStatus;
        }

        @JsonProperty("recommend_status")
        public void setRecommendStatus(String recommendStatus) {
            this.recommendStatus = recommendStatus;
        }

        @JsonProperty("stock")
        public String getStock() {
            return stock;
        }

        @JsonProperty("stock")
        public void setStock(String stock) {
            this.stock = stock;
        }

        @JsonProperty("price")
        public String getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(String price) {
            this.price = price;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

    }
}