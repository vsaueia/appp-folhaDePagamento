package br.com.folhadepagamento.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public final class DateUtil {

    public static long extrairQuantidadeDeSextasFeirasNoPeriodo(LocalDate periodoInicial, LocalDate periodoFinal) {
        return Stream.iterate(periodoInicial, dia -> dia.plusDays(1))
                .limit(ChronoUnit.DAYS.between(periodoInicial, periodoFinal))
                .filter(dia -> dia.getDayOfWeek() == DayOfWeek.FRIDAY)
                .count();
    }
}
