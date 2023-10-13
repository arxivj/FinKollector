import com.miniproject.finkollector.stock.dto.Prediction.PredictionRequest
import com.miniproject.finkollector.stock.dto.Prediction.PredictionResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PredictionService {

    private val aiServerUrl = "http://localhost--"

    fun getPrediction(request: PredictionRequest): PredictionResponse {
        val restTemplate = RestTemplate()

        // FastAPI에 request ㄱㄱ
        val response = restTemplate.postForObject(
            "$aiServerUrl/predict",
            request,
            PredictionResponse::class.java
        )
        return response ?: throw RuntimeException("데이터 fetch 실패")
    }
}