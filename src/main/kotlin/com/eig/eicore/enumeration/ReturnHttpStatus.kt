package com.eig.eicore.enumeration

import org.springframework.http.HttpStatus

class ReturnHttpStatus{

    companion object{

        val VIEW = HttpStatus.OK

        val CREATED = HttpStatus.CREATED

        val UPDATED = HttpStatus.ACCEPTED

        val DELETED = HttpStatus.NO_CONTENT

    }


}