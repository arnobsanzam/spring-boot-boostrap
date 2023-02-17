package com.example.springbootboostrap.service.miscellaneous;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class MiscellaneousTest {
    @Test public void testLogger() {

    }

    @Test
    public void testStringImmutability() {
        String s1 = "hello";
        String s2 = s1;
        System.out.printf(" " + s1.hashCode());
        System.out.printf(" " + s2.hashCode());
    }

    @Test
    public void testEnum() {
        CustomerProfileDto customerProfileDto = new CustomerProfileDto();
        customerProfileDto.setCustomertype(Customertype.CORPORATE);
        boolean matched = Customertype.CORPORATE.equals(customerProfileDto.getCustomertype());
    }
}

enum Customertype {
    INDIVIDUAL,
    CORPORATE
}

@Data
class CustomerProfileDto {
    Customertype customertype;
}


