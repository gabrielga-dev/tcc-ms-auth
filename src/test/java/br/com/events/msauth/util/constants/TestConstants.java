package br.com.events.msauth.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConstants {

    public static final String TEST_STR = "TEST";
    public static final BigDecimal TEST_BIG_DECIMAL = BigDecimal.ONE;
    public static final Boolean TEST_BOOLEAN = Boolean.TRUE;
    public static final Date TEST_DATE = new Date();
    public static final Double TEST_DOUBLE = (double) 1L;
    public static final Integer TEST_INTEGER = 1;
    public static final LocalDateTime TEST_LOCAL_DATE_TIME = LocalDateTime.now();
    public static final Long TEST_LONG = 1L;
    public static final Float TEST_FLOAT = 1F;
    public static final ZonedDateTime TEST_ZONED_DATE_TIME = ZonedDateTime.now();

}
