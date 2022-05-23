package com.example.dopt_app.data

data class Schema(
    val fields: List<Field>,
    val pandas_version: String,
    val primaryKey: List<String>
)