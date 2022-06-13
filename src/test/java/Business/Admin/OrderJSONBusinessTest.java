package Business.Admin;

import DTO.JSON.CategoryJSON;
import DTO.JSON.OrderJSON;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderJSONBusinessTest {
    @Test
    void getListOrder() {

        Gson gson = new Gson();
        List<OrderJSON> list = OrderJSONBusiness.getListOrder();
        String JSON = gson.toJson(list);
        System.out.println(JSON);
    }

    @Test
    void getOrder() {
    }


    @Test
    void testGetListOrder() {
    }

    @Test
    void testGetOrder() {
    }
}