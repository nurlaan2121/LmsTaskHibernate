package java12.interfaces;

import java12.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressInterface {
    String create(Address newAddress);
    String removeAddress(Long addressId);
    Optional<Address> findAddressById(Long idAddress);
    List<Address> getAllAddresses();
    String updateAddressById(Long addressId,Address newAddress);
}
