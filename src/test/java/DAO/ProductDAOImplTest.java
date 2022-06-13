package DAO;

import Utils.SingletonServiceUltils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOImplTest {

    @Test
    void getProductIdByName() {
        System.out.println(SingletonServiceUltils.getProductDAOImpl().getProductIdByName("Dung"));
    }
}