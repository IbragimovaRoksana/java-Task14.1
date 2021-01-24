package ru.netology.domain;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket[] findAll() {
        return repository.findAll();
    }

    public Ticket[] findAllByFromTo(String airportFrom, String airportTo) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (ticket.airportFrom.equals(airportFrom) && ticket.airportTo.equals(airportTo)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public void add(Ticket item) {
        repository.save(item);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }


}
