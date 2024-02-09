package org.spacetravel.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Planets {
    MERCURY("Mercury"),
    VENUS("Venus"),
    EARTH("Earth"),
    MARS("Mars"),
    SATURN("Saturn"),
    URANUS("Uranus"),
    NEPTUNE("Neptune"),
    PLUTO("Pluto"),
    MOON("Moon");
    private String planetName;
}
