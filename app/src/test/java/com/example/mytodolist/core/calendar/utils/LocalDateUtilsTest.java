package com.example.mytodolist.core.calendar.utils;

import static com.google.common.truth.Truth.assertThat;

import com.example.mytodolist.features.calendar.utils.LocalDateUtils;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalDateUtilsTest {
    LocalDate monday;
    LocalDate tuesday;
    LocalDate wednesday;
    LocalDate thursday;
    LocalDate friday;
    LocalDate saturday;
    LocalDate sunday;

    LocalDate Jan;
    LocalDate Feb;
    LocalDate Mar;
    LocalDate Apr;
    LocalDate May;
    LocalDate Jun;
    LocalDate Jul;
    LocalDate Aug;
    LocalDate Sep;
    LocalDate Oct;
    LocalDate Nov;
    LocalDate Dec;

    LocalDate year2001;
    LocalDate year2002;
    LocalDate year2003;
    LocalDate year2004;
    LocalDate year2005;

    @Before
    public void setUp() {
        year2001 = LocalDate.of(2001, 1, 1);
        year2002 = LocalDate.of(2002, 1, 1);
        year2003 = LocalDate.of(2003, 1, 1);
        year2004 = LocalDate.of(2004, 1, 1);
        year2005 = LocalDate.of(2005, 1, 1);

        monday = LocalDate.of(2022, 2, 21);
        tuesday = LocalDate.of(2022, 2, 22);
        wednesday = LocalDate.of(2022, 2, 23);
        thursday = LocalDate.of(2022, 2, 24);
        friday = LocalDate.of(2022, 2, 25);
        saturday = LocalDate.of(2022, 2, 26);
        sunday = LocalDate.of(2022, 2, 27);

        Jan = LocalDate.of(2022, 1, 1);
        Feb = LocalDate.of(2022, 2, 1);
        Mar = LocalDate.of(2022, 3, 1);
        Apr = LocalDate.of(2022, 4, 1);
        May = LocalDate.of(2022, 5, 1);
        Jun = LocalDate.of(2022, 6, 1);
        Jul = LocalDate.of(2022, 7, 1);
        Aug = LocalDate.of(2022, 8, 1);
        Sep = LocalDate.of(2022, 9, 1);
        Oct = LocalDate.of(2022, 10, 1);
        Nov = LocalDate.of(2022, 11, 1);
        Dec = LocalDate.of(2022, 12, 1);
    }

    @Test
    public void testGetDayName() {
        assertThat(LocalDateUtils.getDayName(monday)).isEqualTo("월요일");
        assertThat(LocalDateUtils.getDayName(tuesday)).isEqualTo("화요일");
        assertThat(LocalDateUtils.getDayName(wednesday)).isEqualTo("수요일");
        assertThat(LocalDateUtils.getDayName(thursday)).isEqualTo("목요일");
        assertThat(LocalDateUtils.getDayName(friday)).isEqualTo("금요일");
        assertThat(LocalDateUtils.getDayName(saturday)).isEqualTo("토요일");
        assertThat(LocalDateUtils.getDayName(sunday)).isEqualTo("일요일");
    }

    @Test
    public void testGetDayShortName() {
        assertThat(LocalDateUtils.getDayShortName(monday)).isEqualTo("월");
        assertThat(LocalDateUtils.getDayShortName(tuesday)).isEqualTo("화");
        assertThat(LocalDateUtils.getDayShortName(wednesday)).isEqualTo("수");
        assertThat(LocalDateUtils.getDayShortName(thursday)).isEqualTo("목");
        assertThat(LocalDateUtils.getDayShortName(friday)).isEqualTo("금");
        assertThat(LocalDateUtils.getDayShortName(saturday)).isEqualTo("토");
        assertThat(LocalDateUtils.getDayShortName(sunday)).isEqualTo("일");
    }

    @Test
    public void testGetMonthNumber() {
        assertThat(LocalDateUtils.getMonthNumber(Jan)).isEqualTo("1");
        assertThat(LocalDateUtils.getMonthNumber(Feb)).isEqualTo("2");
        assertThat(LocalDateUtils.getMonthNumber(Mar)).isEqualTo("3");
        assertThat(LocalDateUtils.getMonthNumber(Apr)).isEqualTo("4");
        assertThat(LocalDateUtils.getMonthNumber(May)).isEqualTo("5");
        assertThat(LocalDateUtils.getMonthNumber(Jun)).isEqualTo("6");
        assertThat(LocalDateUtils.getMonthNumber(Jul)).isEqualTo("7");
        assertThat(LocalDateUtils.getMonthNumber(Aug)).isEqualTo("8");
        assertThat(LocalDateUtils.getMonthNumber(Sep)).isEqualTo("9");
        assertThat(LocalDateUtils.getMonthNumber(Oct)).isEqualTo("10");
        assertThat(LocalDateUtils.getMonthNumber(Nov)).isEqualTo("11");
        assertThat(LocalDateUtils.getMonthNumber(Dec)).isEqualTo("12");

    }

    @Test
    public void testGetMonthName() {
        assertThat(LocalDateUtils.getMonthName(Jan)).isEqualTo("1월");
        assertThat(LocalDateUtils.getMonthName(Feb)).isEqualTo("2월");
        assertThat(LocalDateUtils.getMonthName(Mar)).isEqualTo("3월");
        assertThat(LocalDateUtils.getMonthName(Apr)).isEqualTo("4월");
        assertThat(LocalDateUtils.getMonthName(May)).isEqualTo("5월");
        assertThat(LocalDateUtils.getMonthName(Jun)).isEqualTo("6월");
        assertThat(LocalDateUtils.getMonthName(Jul)).isEqualTo("7월");
        assertThat(LocalDateUtils.getMonthName(Aug)).isEqualTo("8월");
        assertThat(LocalDateUtils.getMonthName(Sep)).isEqualTo("9월");
        assertThat(LocalDateUtils.getMonthName(Oct)).isEqualTo("10월");
        assertThat(LocalDateUtils.getMonthName(Nov)).isEqualTo("11월");
        assertThat(LocalDateUtils.getMonthName(Dec)).isEqualTo("12월");
    }

    @Test
    public void testGetYearNumber() {
        assertThat(LocalDateUtils.getYearNumber(year2001)).isEqualTo("2001");
        assertThat(LocalDateUtils.getYearNumber(year2002)).isEqualTo("2002");
        assertThat(LocalDateUtils.getYearNumber(year2003)).isEqualTo("2003");
        assertThat(LocalDateUtils.getYearNumber(year2004)).isEqualTo("2004");
        assertThat(LocalDateUtils.getYearNumber(year2005)).isEqualTo("2005");
    }

    @Test
    public void testGetYearShortNumber() {
        assertThat(LocalDateUtils.getYearShortNumber(year2001)).isEqualTo("01");
        assertThat(LocalDateUtils.getYearShortNumber(year2002)).isEqualTo("02");
        assertThat(LocalDateUtils.getYearShortNumber(year2003)).isEqualTo("03");
        assertThat(LocalDateUtils.getYearShortNumber(year2004)).isEqualTo("04");
        assertThat(LocalDateUtils.getYearShortNumber(year2005)).isEqualTo("05");
    }

    @Test
    public void testGetYearName() {
        assertThat(LocalDateUtils.getYearName(year2001)).isEqualTo("2001년");
        assertThat(LocalDateUtils.getYearName(year2002)).isEqualTo("2002년");
        assertThat(LocalDateUtils.getYearName(year2003)).isEqualTo("2003년");
        assertThat(LocalDateUtils.getYearName(year2004)).isEqualTo("2004년");
        assertThat(LocalDateUtils.getYearName(year2005)).isEqualTo("2005년");
    }

    @Test
    public void testGetYearShortName() {
        assertThat(LocalDateUtils.getYearShortName(year2001)).isEqualTo("01년");
        assertThat(LocalDateUtils.getYearShortName(year2002)).isEqualTo("02년");
        assertThat(LocalDateUtils.getYearShortName(year2003)).isEqualTo("03년");
        assertThat(LocalDateUtils.getYearShortName(year2004)).isEqualTo("04년");
        assertThat(LocalDateUtils.getYearShortName(year2005)).isEqualTo("05년");
    }

    @Test
    public void testGetDayInMonth() {
        assertThat(LocalDateUtils.getDayInMonth(monday)).isEqualTo("21");
        assertThat(LocalDateUtils.getDayInMonth(tuesday)).isEqualTo("22");
        assertThat(LocalDateUtils.getDayInMonth(wednesday)).isEqualTo("23");
        assertThat(LocalDateUtils.getDayInMonth(thursday)).isEqualTo("24");
        assertThat(LocalDateUtils.getDayInMonth(friday)).isEqualTo("25");
        assertThat(LocalDateUtils.getDayInMonth(saturday)).isEqualTo("26");
        assertThat(LocalDateUtils.getDayInMonth(sunday)).isEqualTo("27");
    }

    @Test
    public void testGetDayInMonthName() {
        assertThat(LocalDateUtils.getDayInMonthName(monday)).isEqualTo("21일");
        assertThat(LocalDateUtils.getDayInMonthName(tuesday)).isEqualTo("22일");
        assertThat(LocalDateUtils.getDayInMonthName(wednesday)).isEqualTo("23일");
        assertThat(LocalDateUtils.getDayInMonthName(thursday)).isEqualTo("24일");
        assertThat(LocalDateUtils.getDayInMonthName(friday)).isEqualTo("25일");
        assertThat(LocalDateUtils.getDayInMonthName(saturday)).isEqualTo("26일");
        assertThat(LocalDateUtils.getDayInMonthName(sunday)).isEqualTo("27일");
    }

    @Test
    public void testGetWeekInYear() {
        assertThat(LocalDateUtils.getWeekInYear(LocalDate.of(2022, 1, 1))).isEqualTo("1");
        assertThat(LocalDateUtils.getWeekInYear(LocalDate.of(2022, 1, 2))).isEqualTo("2");
        assertThat(LocalDateUtils.getWeekInYear(LocalDate.of(2022, 1, 3))).isEqualTo("2");
    }

    @Test
    public void testGetFutureDatesFrom() {
        List<LocalDate> prediction = new ArrayList<>();
        prediction.add(LocalDate.of(2022, 1, 2));
        prediction.add(LocalDate.of(2022, 1, 3));
        assertThat(LocalDateUtils.getFutureDatesFrom(2, LocalDate.of(2022, 1, 1))).isEqualTo(prediction);
    }

    @Test
    public void testGetPastDatesFrom() {
        List<LocalDate> prediction = new ArrayList<>();
        prediction.add(LocalDate.of(2021, 12, 30));
        prediction.add(LocalDate.of(2021, 12, 31));
        assertThat(LocalDateUtils.getPastDatesFrom(2, LocalDate.of(2022, 1, 1))).isEqualTo(prediction);
    }
}