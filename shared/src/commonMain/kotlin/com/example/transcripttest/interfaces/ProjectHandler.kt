package com.example.transcripttest.interfaces

interface ProjectHandler {
    fun initProject(path: String)
    fun readFromProject(path: String)
    fun writeProjectList(path: String)
    fun readProjectList(path: String)
}