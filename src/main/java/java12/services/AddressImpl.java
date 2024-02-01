package java12.services;

import java12.daos.daoImpls.AddressDaoImpl;
import java12.entities.Address;
import java12.exceptions.NotFoundException;
import java12.interfaces.AddressInterface;

import java.util.List;
import java.util.Optional;

public class AddressImpl implements AddressInterface {
    AddressDaoImpl addressDao = new AddressDaoImpl();
    @Override
    public String create(Address newAddress) {
       return addressDao.saveAddress(newAddress);
    }

    @Override
    public String removeAddress(Long addressId) {
        return addressDao.deleteAddress(addressId);
    }

    @Override
    public Optional<Address> findAddressById(Long idAddress) {
        try {
            return addressDao.findAddressById(idAddress);
        }catch (NotFoundException e){
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public String updateAddressById(Long addressId, Address newAddress) {
        try {
            return addressDao.updateAddressById(addressId,newAddress);
        }catch (NotFoundException e){
        return e.getMessage();
        }

    }
}
