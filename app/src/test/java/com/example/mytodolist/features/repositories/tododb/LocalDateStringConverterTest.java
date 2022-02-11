package com.example.mytodolist.features.repositories.tododb;

import static com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverterTest extends TestCase {
    LocalDate localDateNow;
    String stringNow;
    LocalDateStringConverter dateConverter;

    @Before
    public void setUp() {
        localDateNow = LocalDate.of(2022, 2, 21);
        stringNow = localDateNow.format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 (E)"));
        dateConverter = new LocalDateStringConverter();
    }

    @Test
    public void testStringToLocalDate() {
        assertThat(dateConverter.stringToLocalDate(stringNow)).isEqualTo(localDateNow);
    }

    @Test
    public void testLocalDateToString() {
        assertThat(dateConverter.localDateToString(localDateNow)).isEqualTo(stringNow);
    }
}