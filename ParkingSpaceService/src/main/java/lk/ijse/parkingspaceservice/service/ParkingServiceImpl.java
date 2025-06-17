package lk.ijse.parkingspaceservice.service;


import lk.ijse.parkingspaceservice.config.RandomNumberGenarator;
import lk.ijse.parkingspaceservice.dto.ParkingDTO;
import lk.ijse.parkingspaceservice.entity.Parking;
import lk.ijse.parkingspaceservice.repo.ParkingRepo;
import lk.ijse.parkingspaceservice.repo.UserRepository;
import lk.ijse.parkingspaceservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingRepo parkingRepo;

    @Autowired
    private ModelMapper modelMapper;


@Override
public int saveParkingPlace(ParkingDTO parkingDTO){
        System.out.println("saveParkingPlace");
        try{
            if(userRepository.existsUserByEmail(parkingDTO.getEmail().toLowerCase())){
                if(parkingRepo.existsParkingByLocation(parkingDTO.getLocation())){
                    return VarList.All_Ready_Added;
                }
                Parking parking = modelMapper.map(parkingDTO, Parking.class);
                int code = Integer.parseInt(RandomNumberGenarator.generateCode(6));
                parking.setLocationCode(code);
                String email = parking.getEmail().toLowerCase();
                String location = parking.getLocation().toLowerCase();
                String city = parking.getCity().toLowerCase();
                parking.setAvailable(true);
                parking.setEmail(email);
                parking.setLocation(location);
                parking.setCity(city);
                parkingRepo.save(parking);
                return VarList.Created;
            }
            return VarList.Not_Found;
        }catch (Exception e){
            System.out.println("Exception"+e.getMessage());
            throw  new RuntimeException();
        }
        }


        @Override
        public int updateParkingPlace(ParkingDTO parkingDTO){
        System.out.println("updateParkingPlace"+parkingDTO);
        try {
            if(userRepository.existsUserByEmail(parkingDTO.getEmail().toLowerCase())){
                if(parkingRepo.existsParkingByLocation(parkingDTO.getLocation().toLowerCase()) || parkingRepo.existsParkingByLocationCode(parkingDTO.getLocationCode())){
                    Parking parking = parkingRepo.findByLocation(parkingDTO.getLocation().toLowerCase());
                    parking.setAvailable(true);
                    parking.setPayAmount(parkingDTO.getPayAmount());
                    parking.setEmail(parkingDTO.getEmail().toLowerCase());
                    parking.setLocation(parkingDTO.getLocation().toLowerCase());
                    parking.setCity(parkingDTO.getCity().toLowerCase());
                    parkingRepo.save(parking);
                    return VarList.Created;
                }
            }
            return VarList.Not_Found;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

@Override
public int ReservationUpdateParkingPlace(String Location, int LocationCode){
        System.out.println("Booking location "+Location);
        try{
            if(parkingRepo.existsParkingByLocation(Location.toLowerCase()) || parkingRepo.existsParkingByLocationCode(LocationCode)){
                Parking parking = parkingRepo.findByLocation(Location.toLowerCase());
                parking.setAvailable(false);
                parkingRepo.save(parking);
                return VarList.Created;
            }
            return VarList.Not_Found;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteParkingPlace(String location, String email, int LocationCode){
        System.out.println("deleteParkingPlace");
        try{
            if(userRepository.existsUserByEmail(email.toLowerCase())){
                if(parkingRepo.existsParkingByLocation(location.toLowerCase()) || parkingRepo.existsParkingByLocationCode(LocationCode)){
                    Parking parking = parkingRepo.findByLocation(location.toLowerCase());
                    parkingRepo.delete(parking);
                    return VarList.OK;
                }
            }
            return VarList.Not_Found;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<ParkingDTO> getParkingPlaceCity(String city){
        System.out.println("getParkingPlaceCity");
        if(parkingRepo.existsParkingByCity(city.toLowerCase())){
            List<Parking> parkingList = parkingRepo.findAllByCity(city);
            List<Parking> parkingList2 = new ArrayList<>();
            for(Parking parking:parkingList){
                if(!parking.isAvailable()){
                    parkingList2.add(parking);
                }
            }
            return modelMapper.map(parkingList2,new TypeToken<List<ParkingDTO>>(){}.getType());
        }
        return null;

    }

    @Override
    public List<ParkingDTO> getAllParkingPlace(){
        List<Parking> parkingList = parkingRepo.findAll();
        return modelMapper.map(parkingList,new TypeToken<List<ParkingDTO>>(){}.getType());
    }

}
