package com.project.bootcamp.mapper;


import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Component
public class StockMapper {


    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setVariation(dto.getVariation());
        stock.setPrice(dto.getPrice());
        stock.setDate(dto.getDate());
        return stock;
    }

    public StockDTO toDto(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());
        return dto;

    }

    public List<StockDTO> ToDto(List<Stock> listStock) {
        return listStock.stream().map(this::toDto).collect(Collectors.toList());
    }
}

