package com.example.springbootboostrap.service.miscellaneous;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class MiscellaneousTest {

    @Test
    public void testStringImmutability() {
        String s1 = "hello";
        String s2 = s1;
        System.out.printf(" " + s1.hashCode());
        System.out.printf(" " + s2.hashCode());
    }

    @Test
    public void testSorting() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomerProfileDto customerProfileDto1 = new CustomerProfileDto(dateFormat.parse("2023-01-01"));
        CustomerProfileDto customerProfileDto2 = new CustomerProfileDto(dateFormat.parse("2023-02-01"));
        CustomerProfileDto customerProfileDto3 = new CustomerProfileDto(dateFormat.parse("2023-03-01"));

        List<CustomerProfileDto> customerProfileDtos = new ArrayList<>();

        CustomerProfileDto customerProfileDto = customerProfileDtos.stream().max(Comparator.comparing(CustomerProfileDto::getDerivedTxnDate)).orElse(null);
        System.out.println(customerProfileDto);

    }

    @RequiredArgsConstructor
    @Data
    class CustomerProfileDto {
        private final Date derivedTxnDate;
    }
}

