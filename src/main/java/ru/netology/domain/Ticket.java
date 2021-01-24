package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ticket implements Comparable<Ticket> {

    int id;
    int price;
    String airportFrom;
    String airportTo;
    int flyTime;
    //flyTime в минутах!


    @Override
    public int compareTo(Ticket ticket) {
        return this.price - ticket.price;
    }
}
