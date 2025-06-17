package lk.ijse.paymentservice;

import lk.ijse.parkingspaceservice.dto.ParkingDTO;
import lk.ijse.parkingspaceservice.service.ParkingService;
import lk.ijse.parkingspaceservice.service.ParkingServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper() {
            return new ModelMapper();
    }

    @Bean
    public ParkingService parkingService() {
        return new ParkingServiceImpl();
    }
}
