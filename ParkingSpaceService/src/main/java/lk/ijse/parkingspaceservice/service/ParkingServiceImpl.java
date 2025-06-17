package lk.ijse.parkingspaceservice.service;


import lk.ijse.parkingspaceservice.dto.ParkingDTO;
import lk.ijse.parkingspaceservice.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ModelMapper modelMapper;


    public int saveParkingPlace(ParkingDTO parkingDTO){}
    public int updateParkingPlace(ParkingDTO parkingDTO){}
    public int deleteParkingPlace(ParkingDTO parkingDTO){}
    public int getParkingPlaceCity(ParkingDTO parkingDTO){}
    public int getAllParkingPlace(ParkingDTO parkingDTO){}
}
