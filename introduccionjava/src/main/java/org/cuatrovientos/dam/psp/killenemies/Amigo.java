package org.cuatrovientos.dam.psp.killenemies;

import java.io.Serializable;

public class Amigo implements IPersonaje, Serializable {

    @Override
    public boolean isEnemy() {
        return false;
    }

    public void heal() {
        System.out.println("¡Te he curado!");
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Amigo;
    }
}
