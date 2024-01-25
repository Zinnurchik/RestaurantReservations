package resource;

import bean.ApiResponce;
import bean.Reservation;
import db.and.service.ReservationRepository;

public class ReservationResource implements BaseCrudResource<Reservation> {
    ReservationRepository reservationRepository = new ReservationRepository();


    @Override
    public ApiResponce add(Reservation reservation) {
        Reservation newReservation = reservationRepository.add(reservation);
        return newReservation == null ? new ApiResponce(400, "You can not reserve (reserved time)", null) :
                new ApiResponce(200, "Successfully created", newReservation);
    }

    @Override
    public ApiResponce get(Integer id) {
        Reservation newReservation = reservationRepository.get(id);
        return newReservation == null ? new ApiResponce(400, "not found", null) :
                new ApiResponce(200, "Success", newReservation);
    }

    @Override
    public ApiResponce update(Reservation reservation) {
        Boolean isUpdate = reservationRepository.update(reservation);
        return !isUpdate ? new ApiResponce(400, "Reservation not found", false) :
                new ApiResponce(200, "Updated", true);
    }

    @Override
    public ApiResponce delete(Integer id) {
        Boolean isDelete = reservationRepository.delete(id);
        return !isDelete ? new ApiResponce(400, "Reservation id is not found", false) :
                new ApiResponce(200, "Deleted", true);
    }
}
