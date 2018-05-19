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
public class RawDatasResponse {

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
            "ip",
            "source",
            "time",
            "price",
            "email",
            "id"
    })
    public static class Item {

        @JsonProperty("ip")
        private String ip;
        @JsonProperty("source")
        private String source;
        @JsonProperty("time")
        private String time;
        @JsonProperty("price")
        private String price;
        @JsonProperty("email")
        private String email;
        @JsonProperty("id")
        private String id;

        @JsonProperty("ip")
        public String getIp() {
            return ip;
        }

        @JsonProperty("ip")
        public void setIp(String ip) {
            this.ip = ip;
        }

        @JsonProperty("source")
        public String getSource() {
            return source;
        }

        @JsonProperty("source")
        public void setSource(String source) {
            this.source = source;
        }

        @JsonProperty("time")
        public String getTime() {
            return time;
        }

        @JsonProperty("time")
        public void setTime(String time) {
            this.time = time;
        }

        @JsonProperty("price")
        public String getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(String price) {
            this.price = price;
        }

        @JsonProperty("email")
        public String getEmail() {
            return email;
        }

        @JsonProperty("email")
        public void setEmail(String email) {
            this.email = email;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

    }
}