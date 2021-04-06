package com.example.db_task.Model;

import java.io.Serializable;
import java.util.Objects;

public class CarsOfPeoplePK implements Serializable {
    private int person_id;
    private int car_id;

    public CarsOfPeoplePK(int person_id, int car_id) {
        this.person_id = person_id;
        this.car_id = car_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsOfPeoplePK that = (CarsOfPeoplePK) o;
        return person_id == that.person_id && car_id == that.car_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, car_id);
    }
}
