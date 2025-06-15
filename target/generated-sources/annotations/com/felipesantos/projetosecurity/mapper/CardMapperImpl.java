package com.felipesantos.projetosecurity.mapper;

import com.felipesantos.projetosecurity.dto.CardDTO;
import com.felipesantos.projetosecurity.model.Card;
import com.felipesantos.projetosecurity.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T15:04:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public Card toCard(CardDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Card card = new Card();

        card.setId( dto.getId() );
        card.setType( dto.getType() );
        card.setDescription( dto.getDescription() );
        card.setUrlImage( dto.getUrlImage() );
        card.setStatus( dto.isStatus() );

        return card;
    }

    @Override
    public CardDTO toCardDTO(Card entity) {
        if ( entity == null ) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();

        cardDTO.setUserId( entityUserId( entity ) );
        cardDTO.setId( entity.getId() );
        cardDTO.setType( entity.getType() );
        cardDTO.setDescription( entity.getDescription() );
        cardDTO.setUrlImage( entity.getUrlImage() );
        cardDTO.setStatus( entity.isStatus() );

        return cardDTO;
    }

    @Override
    public List<Card> toCardList(List<CardDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Card> list = new ArrayList<Card>( dtos.size() );
        for ( CardDTO cardDTO : dtos ) {
            list.add( toCard( cardDTO ) );
        }

        return list;
    }

    @Override
    public List<CardDTO> toCardDTOList(List<Card> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CardDTO> list = new ArrayList<CardDTO>( entities.size() );
        for ( Card card : entities ) {
            list.add( toCardDTO( card ) );
        }

        return list;
    }

    private Long entityUserId(Card card) {
        if ( card == null ) {
            return null;
        }
        User user = card.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
