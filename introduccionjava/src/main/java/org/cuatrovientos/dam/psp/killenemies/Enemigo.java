package org.cuatrovientos.dam.psp.killenemies;

import java.io.Serializable;

public class Enemigo implements IPersonaje, Serializable {

    @Override
    public boolean isEnemy() {
        return true;
    }

    public void kill() {
        System.out.println("Ahhhggg, me mataste, bastardo!");
    }


}
