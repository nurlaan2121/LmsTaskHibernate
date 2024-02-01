package java12.daos;

import java12.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDaoInterface {
    String saveAddress(Address newAddress);
    String deleteAddress(Long addressId);
    Optional<Address> findAddressById(Long idAddress);
    List<Address> getAllAddresses();
    String updateAddressById(Long addressId,Address newAddress);
}
