package edu.unimagdalena.api.service.implementations;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import edu.unimagdalena.api.entities.ShipmentDetails;
import edu.unimagdalena.api.entities.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.entities.enums.OrderStatus;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.entities.mapper.ShipmentDetailsMapper;
import edu.unimagdalena.api.repository.ShipmentDetailsRepository;
import edu.unimagdalena.api.service.services.ShipmentDetailsService;

@Service
public class ShipmentDetailsImpl implements ShipmentDetailsService {

    private final ShipmentDetailsRepository shipmentDetailsRepository;
    private final ShipmentDetailsMapper shipmentDetailsMapper;

    public ShipmentDetailsImpl(ShipmentDetailsRepository shipmentDetailsRepository,
            ShipmentDetailsMapper shipmentDetailsMapper) {
        this.shipmentDetailsRepository = shipmentDetailsRepository;
        this.shipmentDetailsMapper = shipmentDetailsMapper;
    }

    @Override
    public ShipmentDetailsDTO create(ShipmentDetailsDTO shipmentDetailDTO) {
        ShipmentDetails shipmentDetailsSaved = shipmentDetailsRepository
                .save(shipmentDetailsMapper.shipmentDetailsDtoToShipmentDetails(shipmentDetailDTO));
        return shipmentDetailsMapper.shipmentDetailsToShipmentDetailsDto(shipmentDetailsSaved);
    }

    @Override
    public ShipmentDetailsDTO getShipmentDetailById(Long shipmentDetailId) {
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findById(shipmentDetailId)
                .orElseThrow(() -> new ObjectNotFoundException("ShipmentDetails not found"));
        return shipmentDetailsMapper.shipmentDetailsToShipmentDetailsDto(shipmentDetails);
    }

    @Override
    public ShipmentDetailsDTO update(ShipmentDetailsDTO shipmentDetailDTO, Long shipmentDetailId) {
        ShipmentDetails shipmentDetailsInDb = shipmentDetailsRepository.findById(shipmentDetailId)
                .orElseThrow(() -> new ObjectNotFoundException("ShipmentDetails not found"));
        shipmentDetailsInDb.setTransporter(shipmentDetailDTO.transporter());
        shipmentDetailsInDb.setGuideNumber(shipmentDetailDTO.guideNumber());
        shipmentDetailsInDb.setShipmentAddress(shipmentDetailDTO.shipmentAddress());
        return shipmentDetailsMapper.shipmentDetailsToShipmentDetailsDto(shipmentDetailsRepository
                .save(shipmentDetailsInDb));
    }

    @Override
    public void delete(Long shipmentDetailId) {
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findById(shipmentDetailId)
                .orElseThrow(() -> new NotAbleToDeleteException("ShipmentDetails not found, not able to delete"));
        shipmentDetailsRepository.delete(shipmentDetails);
    }

    @Override
    public List<ShipmentDetailsDTO> getAllShipmentDetails() {
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findAll();
        return shipmentDetails.stream()
                .map(shipmentDetailsMapper::shipmentDetailsToShipmentDetailsDto)
                .toList();
    }

    @Override
    public ShipmentDetailsDTO getByOrderId(Long orderId) {
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findByOrderId(orderId);
        if (Objects.isNull(shipmentDetails)) {
            throw new ObjectNotFoundException("ShipmentDetails not found");
        }
        return shipmentDetailsMapper.shipmentDetailsToShipmentDetailsDto(shipmentDetails);
    }

    @Override
    public List<ShipmentDetailsDTO> getByTransporter(String transporter) {
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findByTransporter(transporter);
        return shipmentDetails.stream().map(shipmentDetailsMapper::shipmentDetailsToShipmentDetailsDto).toList();
    }

    @Override
    public List<ShipmentDetailsDTO> getByOrderStatus(OrderStatus orderStatus) {
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findByOrderStatus(orderStatus);
        return shipmentDetails.stream().map(shipmentDetailsMapper::shipmentDetailsToShipmentDetailsDto).toList();
    }

}
