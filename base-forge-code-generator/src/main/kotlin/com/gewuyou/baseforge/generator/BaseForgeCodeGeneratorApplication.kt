package com.gewuyou.baseforge.generator

import com.gewuyou.baseforge.core.app.BaseForgeApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BaseForgeCodeGeneratorApplication: BaseForgeApplicationRunner()

fun main(args: Array<String>) {
	runApplication<BaseForgeCodeGeneratorApplication>(*args)
}
