package dataWorker.sql;

import basket.CustomersBasket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLWorkerTest {

    @Test
    void getCartData(){
        CustomersBasket expected = new CustomersBasket();
        SQLWorker sqlWorker = new SQLWorker("/C:/new/target/classes/database/dataSQL.db");
        CustomersBasket actual = sqlWorker.getCartData();
        assertEquals(expected, actual);
    }

}