package com.my.cinema.booking.mapper;

import com.my.cinema.booking.dao.mapper.MovieMapper;
import com.my.cinema.booking.dao.mapper.SeatMapper;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.Seat;
import com.my.cinema.booking.model.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SeatMapperTest {

    @Test
    void getEntityTest() throws SQLException {
        SeatMapper seatMapper = new SeatMapper();
        ResultSet rs = mock(ResultSet.class);
        Seat expected = new Seat();
        expected.setId(1L);
        expected.setNumber(5);
        expected.setRowId(5L);

        lenient().when(rs.getLong("id")).thenReturn(1L);
        lenient().when(rs.getLong("row_id")).thenReturn(5L);
        lenient().when(rs.getInt("number")).thenReturn(5);

        Seat result = seatMapper.getEntity(rs);

        Assertions.assertAll(() -> {
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getRowId(), result.getRowId());
            assertEquals(expected.getNumber(), result.getNumber());
        });
    }
}
