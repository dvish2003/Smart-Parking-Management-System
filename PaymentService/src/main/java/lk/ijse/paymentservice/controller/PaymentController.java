package lk.ijse.paymentservice.controller;


import jakarta.ws.rs.POST;
import lk.ijse.parkingspaceservice.dto.ParkingDTO;
import lk.ijse.paymentservice.dto.ResponseDTO;
import lk.ijse.paymentservice.util.VarList;
import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path ="/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "savePayment")
    public ResponseEntity<ResponseDTO> savePayment(@RequestBody PaymentDTO paymentDTO) {
        System.out.println("controller savePayment...........");
        try {
            ResponseDTO res = paymentService.savePaymentAndTransaction(paymentDTO);

            switch(res.getCode()) {
                case VarList.Created -> {
                    System.out.println("Payment Transaction is Successfully created");
                    return ResponseEntity.ok(new ResponseDTO(VarList.Created, "Payment Transaction is Successfully created", res.getData()));
                }
                case VarList.Not_Found -> {
                    System.out.println("Resource not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseDTO(VarList.Not_Found, "Resource not found", null));
                }
                case VarList.Conflict -> {
                    System.out.println("Conflict occurred");
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseDTO(VarList.Conflict, "Conflict occurred", null));
                }
                default -> {
                    System.out.println("Unexpected error occurred");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Unexpected error occurred", null));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal server error", null));
        }
    }
}
