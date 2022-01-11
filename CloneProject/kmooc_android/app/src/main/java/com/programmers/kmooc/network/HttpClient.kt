package com.programmers.kmooc.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class HttpClient(private val baseUrl: String) {

    fun getJson(path: String, params: Map<String, Any>, completed: (Result<String>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            var queryParams = if (params.isNotEmpty()) params
                .map { entry -> "${entry.key}=${entry.value}" }
                .reduce { s1, s2 -> "${s1}&${s2}" }
            else ""

            if (queryParams.isNotEmpty()) queryParams = "?${queryParams}"

            val url = if (path.startsWith("http")) URL(path + queryParams)
            else URL(baseUrl + path + queryParams)

            val json = url.readText(Charsets.UTF_8)
            completed(Result.success(json))
        }
    }

}