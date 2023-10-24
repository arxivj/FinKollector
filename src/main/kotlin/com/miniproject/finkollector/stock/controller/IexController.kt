package com.miniproject.finkollector.stock.controller

import com.miniproject.finkollector.stock.domain.IexEntity
import com.miniproject.finkollector.stock.dto.IexRequestDto
import com.miniproject.finkollector.stock.service.IexService
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@RestController
@RequestMapping("/iex")
class IexController(private val iexService: IexService) {

    @PostMapping("/chart")
    @Operation(
        summary = "[IEX Cloud 주식 데이터 조회]: 차트 그릴 때 사용! DB에 저장한 데이터가 아니라 IEX Cloud API 응답으로 받아온 데이터라 현재까지의 기간이 아닌 특정 범위의 데이터를 받아오는건 안됨.",
        description = "[현재까지 주어진 기간 동안의 주식 데이터를 조회]: 시작, 끝 날짜는 2023-10-10 포멧으로 작성!",
        responses = [
            ApiResponse(
                responseCode = "200", description = "조회 성공",
                content = [Content(schema = Schema(implementation = IexEntity::class))]
            ),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun getStockData(@RequestBody request: IexRequestDto): List<IexEntity> {
        return iexService.getStockData(request.ticker, request.startDate, request.endDate)
    }
}
