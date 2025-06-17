package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.dto.ParkingDTO;

import java.util.List;

public interface ParkingService {
    int saveParkingPlace(ParkingDTO parkingDTO);

    int updateParkingPlace(ParkingDTO parkingDTO);

    int ReservationUpdateParkingPlace(String Location);

    int deleteParkingPlace(String location, String email, int LocationCode);

    List<ParkingDTO> getParkingPlaceCity(String city);

    List<ParkingDTO> getAllParkingPlace();
}
