package com.felipesantos.projetosecurity.service;

import com.felipesantos.projetosecurity.dto.CardDTO;
import com.felipesantos.projetosecurity.mapper.CardMapper;
import com.felipesantos.projetosecurity.model.Card;
import com.felipesantos.projetosecurity.model.Role;
import com.felipesantos.projetosecurity.model.User;
import com.felipesantos.projetosecurity.repository.CardRepository;
import com.felipesantos.projetosecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

import static com.felipesantos.projetosecurity.service.util.ValidateEntityService.validateOptional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardMapper cardMapper;

    public List<CardDTO> findAll(){
        User currentUser = getAuthenticatedUser();
        List<Card> cards = cardRepository.findAll();

        if (isRole(currentUser,Role.ADMIN)) {
            return cardMapper.toCardDTOList(cards);
        }

        return cardMapper.toCardDTOList(cards.stream().filter(
                card -> card.isStatus() || currentUser.getId() == card.getUser().getId()
        ).toList());
    }

    public CardDTO findById(Long id){
        return cardMapper.toCardDTO(validateOptional(cardRepository.findById(id)));
    }

    public CardDTO create(CardDTO dto){
        User currentUser = getAuthenticatedUser();

        if (isRole(currentUser,Role.USER)){
            dto.setStatus(false);
        }
        Card entity = cardMapper.toCard(dto);
        entity.setUser(currentUser);
        cardRepository.save(entity);

        return cardMapper.toCardDTO(entity);
    }

    public CardDTO update(Long id, CardDTO dto){
        User currentUser = getAuthenticatedUser();
        Card entity = validateOptional(cardRepository.findById(id));

        if (isRole(currentUser,Role.ADMIN)){
            entity.setStatus(dto.isStatus());
        }

        if(isRole(currentUser,Role.ADMIN) || currentUser.getId() == entity.getUser().getId()){
            entity.setDescription(dto.getDescription());
            entity.setType(dto.getType());
            entity.setLocal(dto.getLocal());
        }

        cardRepository.save(entity);
        return cardMapper.toCardDTO(entity);
    }

    public void delete(Long id){
        User currentUser = getAuthenticatedUser();
        Card entity = validateOptional(cardRepository.findById(id));
        if(isRole(currentUser,Role.ADMIN) || currentUser.getId() == entity.getUser().getId()) {
            cardRepository.deleteById(entity.getId());
        } else {
            throw new InvalidParameterException("User without permission to delete this card");
        }
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    private boolean isRole(User user, Role role) {
        return user.getRole() == role;
    }
}
