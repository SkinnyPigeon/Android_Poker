package com.example.user.cards;

import java.util.Comparator;

/**
 * Created by user on 17/08/2016.
 */

public class PlayerSorter implements Comparator<Player> {

    public int compare( Player self, Player other ) {
        return self.number().compareTo( other.number() );
    }

}