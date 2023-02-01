package dataWorker.sql;

import basket.CustomersBasket;
import org.junit.jupiter.api.Test;
import shoppingCart.Devices;
import shoppingCart.TypeOfDevices;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SQLWorkerTest {

    @Test
    void getCartData(){
        CustomersBasket expected = new CustomersBasket();
        SQLWorker sqlWorker = new SQLWorker("/C:/new/target/classes/database/dataSQL.db");
        CustomersBasket actual = sqlWorker.getCartData();
        sqlWorker.close();
        assertEquals(expected.getCart(), actual.getCart());
    }

    @Test
    void getStorageData(){
        Map<Object, Object> expected = new HashMap<>(Map.of(
                TypeOfDevices.HEADPHONE, new Devices(TypeOfDevices.HEADPHONE, 50., 0),
                TypeOfDevices.PHONE, new Devices(TypeOfDevices.PHONE, 100., 6),
                TypeOfDevices.LAPTOP, new Devices(TypeOfDevices.LAPTOP, 500., 4)
        ));
        SQLWorker sqlWorker = new SQLWorker("/C:/new/target/classes/database/dataSQL.db");
        Map<Object, Object> actual = sqlWorker.getStorageData();
        sqlWorker.close();

        for (TypeOfDevices type : TypeOfDevices.values()){
            assertEquals(expected.get(type), actual.get(type));
            expected.remove(type);
            actual.remove(type);
        }

        assertTrue(expected.isEmpty());
        assertTrue(actual.isEmpty());
    }

    @Test
    void updateTest(){
        
    }

}