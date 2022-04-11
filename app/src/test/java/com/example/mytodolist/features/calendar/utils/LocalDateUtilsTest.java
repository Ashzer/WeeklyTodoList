package com.example.mytodolist.features.calendar.utils;

import junit.framework.TestCase;

import org.junit.Before;
import org.mockito.Mock;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class LocalDateUtilsTest extends TestCase {
    @Before
    public void setUp(){
        Clock clock =  Clock.fixed(Instant.parse("2022-02-21T10:10:10.00Z"), ZoneId.of("Asia/Seoul"));
        LocalDate.now(clock);

        System.out.println(LocalDate.now(clock));
    }

    public void testGetFromFutureToToday() {
        List<LocalDate> localDateList = LocalDateUtils.getFromFutureToToday(LocalDate.of(2022,3,5),5);
        localDateList.forEach(localDate -> System.out.println(localDate));
        List<LocalDate> testCaseList = new ArrayList<>();
        testCaseList.add(LocalDate.of(2022,2,23));
        testCaseList.add(LocalDate.of(2022,2,24));
        testCaseList.add(LocalDate.of(2022,2,25));
        testCaseList.add(LocalDate.of(2022,2,26));
        testCaseList.add(LocalDate.of(2022,2,27));
        testCaseList.add(LocalDate.of(2022,2,28));
        testCaseList.add(LocalDate.of(2022,3,1));
        testCaseList.add(LocalDate.of(2022,3,2));
        testCaseList.add(LocalDate.of(2022,3,3));
        testCaseList.add(LocalDate.of(2022,3,4));
        assert(testCaseList).equals(localDateList);
    }

    public void testGetFromPastToToday() {
        List<LocalDate> localDateList = LocalDateUtils.getFromPastToToday(LocalDate.of(2022,2,22),5);
        localDateList.forEach(localDate -> System.out.println(localDate));

        List<LocalDate> testCaseList = new ArrayList<>();
        testCaseList.add(LocalDate.of(2022,2,23));
        testCaseList.add(LocalDate.of(2022,2,24));
        testCaseList.add(LocalDate.of(2022,2,25));
        testCaseList.add(LocalDate.of(2022,2,26));
        testCaseList.add(LocalDate.of(2022,2,27));
        testCaseList.add(LocalDate.of(2022,2,28));
        testCaseList.add(LocalDate.of(2022,3,1));
        testCaseList.add(LocalDate.of(2022,3,2));
        testCaseList.add(LocalDate.of(2022,3,3));
        testCaseList.add(LocalDate.of(2022,3,4));
        testCaseList.add(LocalDate.of(2022,3,5));

        assert(testCaseList).equals(localDateList);
    }
}