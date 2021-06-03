package com.project.bootcamp.service;


import com.project.bootcamp.exceptions.BusinessExceptions;
import com.project.bootcamp.exceptions.NotFoundExceptions;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Transactional(readOnly =true)
    public StockDTO findById (Long id){
        return repository.findById(id).map(mapper::ToDto).orElseThrow(NotFoundExceptions::new);

    }

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;


    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional <Stock> optionalStock = repository.findByNameAndDate(dto.getName(),dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessExceptions(MessageUtils.STOCK_ALREADY_EXISTS);
        }


        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        dto.setId(stock.getId());
        return mapper.toDto(stock);

    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(),dto.getDate(), dto.getId());
        if (optionalStock.isPresent()){
            throw new BusinessExceptions((MessageUtils.STOCK_ALREADY_EXISTS));
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        dto.setId(stock.getId());
        return mapper.toDto(stock);
    }

    @Transactional(readOnly =true)
    public List<StockDTO> findAll() {
        List<Stock> list= repository.findAll();
        return mapper.ToDto(list);
    }
    @Transactional
    public StockDTO delete(long id){
        StockDTO dto= this.findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }
    @Transactional(readOnly =true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now().map(mapper::toDto).orElserThrow(NotFoundExceptions::new);
    }
}
