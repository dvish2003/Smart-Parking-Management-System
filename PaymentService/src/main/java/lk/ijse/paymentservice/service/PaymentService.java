package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.dto.ResponseDTO;

public interface PaymentService {
    ResponseDTO savePaymentAndTransaction(PaymentDTO paymentDTO);
}
