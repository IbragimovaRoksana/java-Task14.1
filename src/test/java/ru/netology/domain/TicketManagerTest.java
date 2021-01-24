package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    TicketByFlyTimeComparator comparator = new TicketByFlyTimeComparator();

    Ticket toSochiFromUfa = new Ticket(414, 4500_00, "UFA", "AER", 135);
    Ticket toMoscowDomFromUfa = new Ticket(513, 1300_00, "UFA", "DME", 125);
    Ticket toMoscowSherFromUfa = new Ticket(555, 1350_00, "UFA", "SVO", 115);
    Ticket toStPeterFromUfaPobeda = new Ticket(678, 7100_00, "UFA", "LED", 169);
    Ticket toStPeterFromUfaAeroflot = new Ticket(690, 7700_00, "UFA", "LED", 160);

    @BeforeEach
    private void AddAll() {
        manager.add(toSochiFromUfa);
        manager.add(toMoscowDomFromUfa);
        manager.add(toMoscowSherFromUfa);
        manager.add(toStPeterFromUfaAeroflot);
        manager.add(toStPeterFromUfaPobeda);
    }

    @Test
    void shouldFindAll() {
        Ticket[] expected = new Ticket[]{toSochiFromUfa, toMoscowDomFromUfa, toMoscowSherFromUfa, toStPeterFromUfaAeroflot, toStPeterFromUfaPobeda};
        Ticket[] actual = manager.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldFindAllByFromTo() {
        Ticket[] expected = new Ticket[]{toStPeterFromUfaPobeda, toStPeterFromUfaAeroflot};
        Ticket[] actual = manager.findAllByFromTo("UFA", "LED");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindOneByFromTo() {
        Ticket[] expected = new Ticket[]{toSochiFromUfa};
        Ticket[] actual = manager.findAllByFromTo("UFA", "AER");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindAnyAirportToByFromTo() {
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.findAllByFromTo("UFA", "OVB");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindAnyAirportFromByFromTo() {
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.findAllByFromTo("OVB", "AER");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindQuickFlyTimeSeveralFlies() {
        Ticket[] expected = new Ticket[]{toStPeterFromUfaAeroflot, toStPeterFromUfaPobeda};
        Ticket[] actual = manager.findAllByFromTo("UFA", "LED", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindQuickFlyTimeOneFly() {
        Ticket[] expected = new Ticket[]{toSochiFromUfa};
        Ticket[] actual = manager.findAllByFromTo("UFA", "AER", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindQuickFlyTimeNoFly() {
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.findAllByFromTo("OVB", "UFA", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindQuickFlyTimeSeveralSameFlies() {
        manager.add(toStPeterFromUfaAeroflot);
        Ticket[] expected = new Ticket[]{toStPeterFromUfaAeroflot, toStPeterFromUfaAeroflot, toStPeterFromUfaPobeda};
        Ticket[] actual = manager.findAllByFromTo("UFA", "LED", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        manager.removeById(678);
        Ticket[] expected = new Ticket[]{toSochiFromUfa, toMoscowDomFromUfa, toMoscowSherFromUfa, toStPeterFromUfaAeroflot};
        Ticket[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }
}