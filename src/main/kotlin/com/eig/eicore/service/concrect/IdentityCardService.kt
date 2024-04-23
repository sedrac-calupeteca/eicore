package com.eig.eicore.service.concrect


import com.eig.eicore.model.concrect.IdentityCard
import com.eig.eicore.repository.concrect.IdentityCardRepository
import com.eig.eicore.service.ConcrectService
import org.springframework.stereotype.Service

@Service
class IdentityCardService(
    val identityCardRepository: IdentityCardRepository
): ConcrectService<IdentityCard>(identityCardRepository) {

    fun saveOrUpdate(identityCard: IdentityCard): IdentityCard{
        identityCardRepository.findByPerson(identityCard.person).ifPresent { identityCard.id = it.id }
        return identityCardRepository.save(identityCard)
    }

}