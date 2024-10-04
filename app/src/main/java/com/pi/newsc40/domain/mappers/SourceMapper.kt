package com.pi.newsc40.domain.mappers

import com.pi.newsc40.data.api.model.SourceDM
import com.pi.newsc40.domain.model.Source
import javax.inject.Inject

class SourceMapper @Inject constructor(){
    fun mapSourceDMToSource(sourceDM: SourceDM): Source{
        return Source(id = sourceDM.id, name = sourceDM.name ?:"")
    }
    fun mapSourcesDMToSources(sources: List<SourceDM>): List<Source>{
        return sources.map {
            mapSourceDMToSource(it)
        }
    }
}