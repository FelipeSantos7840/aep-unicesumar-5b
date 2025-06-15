package com.felipesantos.projetosecurity.mapper;

import com.felipesantos.projetosecurity.dto.CardDTO;
import com.felipesantos.projetosecurity.model.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card toCard(CardDTO dto);
    CardDTO toCardDTO(Card entity);
    List<Card> toCardList(List<CardDTO> dtos);
    List<CardDTO> toCardDTOList(List<Card> entities);
}
