package com.eig.eicore.interfaces

import com.eig.eicore.enumeration.ActionBody
import java.io.Serializable

interface IBody<T> : Serializable {
    fun  toEntity(action: ActionBody = ActionBody.SAVE): T
}