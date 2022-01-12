package com.programmers.kmooc.repositories

import android.util.Log
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.models.LectureList
import com.programmers.kmooc.network.HttpClient
import com.programmers.kmooc.utils.DateUtil
import org.json.JSONObject

class KmoocRepository {

    /**
     * 국가평생교육진흥원_K-MOOC_강좌정보API
     * https://www.data.go.kr/data/15042355/openapi.do
     */

    private val httpClient = HttpClient("http://apis.data.go.kr/B552881/kmooc")
    private val serviceKey =
        "LwG%2BoHC0C5JRfLyvNtKkR94KYuT2QYNXOT5ONKk65iVxzMXLHF7SMWcuDqKMnT%2BfSMP61nqqh6Nj7cloXRQXLA%3D%3D"

    fun list(completed: (LectureList) -> Unit) {
        httpClient.getJson(
            "/courseList",
            mapOf("serviceKey" to serviceKey, "Mobile" to 1)
        ) { result ->
            result.onSuccess {
                completed(parseLectureList(JSONObject(it)))
            }
        }
    }

    fun next(currentPage: LectureList, completed: (LectureList) -> Unit) {
        val nextPageUrl = currentPage.next
        httpClient.getJson(nextPageUrl, emptyMap()) { result ->
            result.onSuccess {
                completed(parseLectureList(JSONObject(it)))
            }
        }
    }

    fun detail(courseId: String, completed: (Lecture) -> Unit) {
        httpClient.getJson(
            "/courseDetail",
            mapOf("CourseId" to courseId, "serviceKey" to serviceKey)
        ) { result ->
            result.onSuccess {
                completed(parseLecture(JSONObject(it)))
            }
        }
    }


    //TODO: JsonParse
    private fun parseLectureList(jsonObject: JSONObject): LectureList {

        return jsonObject.getJSONObject("pagination")
            .run {
                LectureList(
                    getInt("count"),
                    getInt("num_pages"),
                    getString("previous"),
                    getString("next"),
                    jsonObject.getJSONArray("results")
                        .run {
                            mutableListOf<Lecture>().apply {
                                for (i in 0 until length()) //length() ->JsonArray 길이
                                {
                                    add(parseLecture(getJSONObject(i)))
                                }
                            }
                        }
                )
            }
    }

    private fun parseLecture(jsonObject: JSONObject): Lecture {

        return jsonObject.run {
            Log.e("StartDate",getString("start"))
            Lecture(
                getString("id"),
                getString("number"),
                getString("name"),
                getString("classfy_name"),
                getString("middle_classfy_name"),
                getJSONObject("media").getJSONObject("image").getString("small"),
                getJSONObject("media").getJSONObject("image").getString("large"),
                getString("short_description"),
                getString("org_name"),
                DateUtil.parseDate(getString("start")),
                DateUtil.parseDate(getString("end")),
                if(has("teachers")) getString("teachers") else null,
                if(has("overview")) getString("overview") else null,
            )

        }
    }
}