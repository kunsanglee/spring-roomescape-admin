package roomescape.domain;

import java.time.LocalDate;
import roomescape.domain.strategy.ReservationDateStrategy;

public class Reservation {
    private Long id;
    private String name;
    private LocalDate date;
    private ReservationTime time;

    Reservation(Long id, String name, LocalDate date, ReservationTime time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static Reservation of(String name, LocalDate date, ReservationTime time, ReservationDateStrategy strategy) {
        validateName(name);
        validateDate(date, strategy);
        return new Reservation(null, name, date, time);
    }

    private static void validateDate(LocalDate date, ReservationDateStrategy strategy) {
        if (date == null) {
            throw new IllegalArgumentException("예약 일자는 필수 입력값 입니다.");
        }
        if (strategy.isInvalid(date)) {
            throw new IllegalArgumentException(String.format("예약 날짜는 현재 날짜보다 과거일 수 없습니다. 입력 날짜:%s", date));
        }
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("예약자명은 필수 입력값 입니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReservationTime getTime() {
        return time;
    }
}
