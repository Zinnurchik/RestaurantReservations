package db.and.service;

import bean.Reservation;
import bean.Table;

import java.util.Date;
import java.util.List;

public class ReservationRepository implements BaseCrudRepository<Reservation> {
    static List<Reservation> reservations = DB.getReservations();
    static List<Table> tables = DB.getTables();


    @Override
    public Reservation add(Reservation reservation) {
        //ignored to compare with current date
        for (int i = 0; i < reservations.size(); i++) {
            Date d1 = reservations.get(i).getDate();
            Date d2 = reservation.getDate();

            if (d1.getYear() == d2.getYear() && !tables.get(reservation.getTableId()).getReserve())
                if (d1.getMonth() == d2.getMonth() && !tables.get(reservation.getTableId()).getReserve())
                    if (d1.getDate() == d2.getDate() && !tables.get(reservation.getTableId()).getReserve())
                        if (d1.getHours() - 4 <= d2.getHours() && d1.getHours() >= d2.getHours() ||
                                d1.getHours() + 4 >= d2.getHours() && d1.getHours() <= d2.getHours() &&
                                        !tables.get(reservation.getTableId()).getReserve())
                            return null;

        }
        DB.getTables().get(reservation.getTableId()).setReserve(true);
        reservation.setId(reservations.size());
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Reservation get(Integer id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(id)) {
                return reservations.get(i);
            }
        }
        return null;
    }

    @Override
    public Boolean update(Reservation reservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(reservation.getId())) {
                reservations.get(i).setMeals(reservation.getMeals());
                reservations.get(i).setNumberOfPeople(reservation.getNumberOfPeople());
                reservations.get(i).setUserLogin(reservation.getUserLogin());
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(id)) {
                reservations.get(i).setDeleted(true);
                tables.get(reservations.get(id).getTableId()).setReserve(false);
                return true;
            }
        }
        return false;
    }
}
