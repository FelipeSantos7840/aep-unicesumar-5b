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
        return cardMapper.toCardDTOList(cardRepository.findAll());
    }

    public CardDTO findById(Long id){
        return cardMapper.toCardDTO(validateOptional(cardRepository.findById(id)));
    }

    public CardDTO create(CardDTO dto){
        User currentUser = getAuthenticatedUser();

        if (isRole(currentUser,Role.USER)){
            dto.setStatus(false);
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
