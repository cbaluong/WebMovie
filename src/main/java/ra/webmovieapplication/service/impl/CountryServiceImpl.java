package ra.webmovieapplication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.webmovieapplication.exception.CustomException;
import ra.webmovieapplication.model.entity.Country;
import ra.webmovieapplication.repository.ICountryRepo;
import ra.webmovieapplication.service.ICountryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements ICountryService {
    private final ICountryRepo countryRepo;

//    @Override
//    public Page<Country> findAllPagination(Pageable pageable) {
//        return countryRepo.findAll(pageable);
//    }

    @Override
    public Page<Country> findAllAdmin(String search,Pageable pageable) {
        return countryRepo.findAllByNameContains(search,pageable);
    }

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }


    @Override
    public Country findById(Integer id) throws CustomException {
        return countryRepo.findById(id).orElseThrow(()-> new CustomException("Not found this country"));
    }


    @Override
    public Country save(Country country) {
        country.setStatus(true);
        return countryRepo.save(country);
    }

    @Override
    public void delete(Integer id) {
        countryRepo.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return countryRepo.existsByName(name);
    }
}
