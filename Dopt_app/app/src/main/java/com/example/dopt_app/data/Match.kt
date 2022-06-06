package com.example.dopt_app.data

import java.io.Serializable

data class Match(
    val `data`: List<DataX>,
    val schema: Schema
): Serializable