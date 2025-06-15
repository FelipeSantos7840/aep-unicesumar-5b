package com.felipesantos.projetosecurity.mapper;

import com.felipesantos.projetosecurity.dto.CardDTO;
import com.felipesantos.projetosecurity.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card toCard(CardDTO dto);
    @Mapping(source = "user.id",target = "userId")
    CardDTO toCardDTO(Card entity);
    List<Card> toCardList(List<CardDTO> dtos);
    List<CardDTO> toCardDTOList(List<Card> entities);
}
