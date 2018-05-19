package com.hackathon.hackathonmanageapp.api;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackathon.hackathonmanageapp.api.request.UpdatePriceRequest;
import com.hackathon.hackathonmanageapp.api.response.GetBeeGoodsResponse;
import com.hackathon.hackathonmanageapp.api.response.GetGoodsListResponse;
import com.hackathon.hackathonmanageapp.api.response.RawDatasResponse;
import com.hackathon.hackathonmanageapp.api.response.UpdatePriceResponse;
import com.shiho.mtool.expection.LibStatusNullException;
import com.shiho.mtool.utils.JsonApiUtils;

import java.util.HashMap;
import retrofit2.Call;

public class BeeDataApiUtils {

    private static final String BEE_API_URL = "https://core.honestbee.com/api/departments/32992?page=1&pageSize=20&sort=ranking";
    private static final String API_URL = "https://9f3ijn5rh4.execute-api.us-west-2.amazonaws.com/hb/";

    private JsonApiUtils apiUtils;
    private HackathonApiService apiService;

    public BeeDataApiUtils(){
        apiUtils = new JsonApiUtils(API_URL);
        apiService = apiUtils.createRetrofitApiService(HackathonApiService.class);
    }

    public GetBeeGoodsResponse getBeeGoods(){
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/vnd.honestbee+json;version=2");
        header.put("Accept-Language", "zh-TW");
        Call<String> apiReponse = apiService.getBeeGoods(header, BEE_API_URL);
        String response = apiUtils.doApi(apiReponse);

        Log.d("test", response);

        GetBeeGoodsResponse goodsResponse = null;
        try {
            goodsResponse = apiUtils.parseJsonResponse(response, GetBeeGoodsResponse.class);
        } catch (LibStatusNullException e) {
            e.printStackTrace();
        }
        return goodsResponse;
    }

    public GetGoodsListResponse getGoodsList(){
        Call<String> apiResponse = apiService.getGoodsList();
        String response = apiUtils.doApi(apiResponse);

        Log.d("test", response);

        GetGoodsListResponse listResponse = null;

        try {
            listResponse = apiUtils.parseJsonResponse(response, GetGoodsListResponse.class);
        } catch (LibStatusNullException e) {
            e.printStackTrace();
        }

        return listResponse;
    }

    public RawDatasResponse getRawDatas(){
        Call<String> apiResponse = apiService.getRawDatas();
        String response = apiUtils.doApi(apiResponse);

        Log.d("test", response);

        RawDatasResponse rawDatasResponse = null;

        try {
            rawDatasResponse = apiUtils.parseJsonResponse(response, RawDatasResponse.class);
        } catch (LibStatusNullException e) {
            e.printStackTrace();
        }
        return rawDatasResponse;
    }

    public UpdatePriceResponse updatePrice(UpdatePriceRequest request){

        UpdatePriceResponse updatePriceResponse = null;

        try {
            Call<String> apiResposne = apiService.updatePrice(apiUtils.convertRequestToJsonString(request));
            String response = apiUtils.doApi(apiResposne);

            Log.d("test", response);

            updatePriceResponse = apiUtils.parseJsonResponse(response, UpdatePriceResponse.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (LibStatusNullException e) {
            e.printStackTrace();
        }

        return updatePriceResponse;
    }

}
