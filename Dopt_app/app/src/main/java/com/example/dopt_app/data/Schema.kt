package com.example.dopt_app.data

import java.io.Serializable

data class Schema(
    val fields: List<Field>,
    val pandas_version: String,
    val primaryKey: List<String>
): Serializable