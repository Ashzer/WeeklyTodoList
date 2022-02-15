package com.example.mytodolist.features.repositories.tododb;

import static com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverterTest {
    LocalDate localDateNow;
    String stringNow;

    @Before
    public void setUp() {
        localDateNow = LocalDate.of(2022, 2, 21);
        stringNow = localDateNow.format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 (E)"));
    }

    @Test
    public void testStringToLocalDate() {
        assertThat(LocalDateStringConverter.stringToLocalDate(stringNow)).isEqualTo(localDateNow);
    }

    @Test
    public void testLocalDateToString() {
        assertThat(LocalDateStringConverter.localDateToString(localDateNow)).isEqualTo(stringNow);
    }
}