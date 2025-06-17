package lk.ijse.paymentservice.service;

import lk.ijse.parkingspaceservice.service.ParkingService;
import lk.ijse.parkingspaceservice.util.VarList;
import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.dto.ResponseDTO;
import lk.ijse.paymentservice.entity.Payment;
import lk.ijse.paymentservice.repo.PaymentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepo paymentRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingService parkingService;

@Override
public ResponseDTO savePaymentAndTransaction(PaymentDTO paymentDTO) {
        System.out.println("Transaction handle ..........");
      try{
          Payment payment1 = modelMapper.map(paymentDTO, Payment.class);
          if(payment1 != null) {
              Payment payment = paymentRepo.save(payment1);
              if(payment != null) {
                  int res = parkingService.ReservationUpdateParkingPlace(paymentDTO.getParkingLocation());
                  if(res == 201){
                      return new ResponseDTO(VarList.Created,"transaction is done",payment);
                  } else if (res == 404) {
                      return new ResponseDTO(VarList.Not_Found,"",null);
                  }
              }
          }
          return new ResponseDTO(VarList.Conflict,"",null);
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }
}
