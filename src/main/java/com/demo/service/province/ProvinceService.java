package com.demo.service.province;

import com.demo.model.Province;
import com.demo.repository.IProvinceRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService implements IProvinceService{
  @Autowired
  private IProvinceRepository provinceRepository;
  @Override
  public Iterable<Province> findAll() {
    return provinceRepository.findAll();
  }

  @Override
  public Optional<Province> findById(Long id) {
    return provinceRepository.findById(id);
  }

  @Override
  public void save(Province province) {
    provinceRepository.save(province);
  }

  @Override
  public void remove(Long id) {
    provinceRepository.deleteById(id);
  }
}
