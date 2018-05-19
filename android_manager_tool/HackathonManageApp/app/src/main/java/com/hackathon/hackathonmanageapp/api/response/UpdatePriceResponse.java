package com.hackathon.hackathonmanageapp.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Attributes"
})
public class UpdatePriceResponse {

    @JsonProperty("Attributes")
    private Attributes attributes;

    @JsonProperty("Attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("Attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "price",
            "last_price"
    })
    public static class Attributes {

        @JsonProperty("price")
        private String price;
        @JsonProperty("last_price")
        private String lastPrice;

        @JsonProperty("price")
        public String getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(String price) {
            this.price = price;
        }

        @JsonProperty("last_price")
        public String getLastPrice() {
            return lastPrice;
        }

        @JsonProperty("last_price")
        public void setLastPrice(String lastPrice) {
            this.lastPrice = lastPrice;
        }

    }

}