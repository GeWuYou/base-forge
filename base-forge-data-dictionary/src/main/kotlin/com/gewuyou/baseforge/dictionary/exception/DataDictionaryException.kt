package com.gewuyou.baseforge.dictionary.exception

import com.gewuyou.baseforge.autoconfigure.i18n.entity.ResponseInformation
import com.gewuyou.baseforge.core.exception.GlobalException

/**
 *数据字典异常
 *
 * @since 2025-01-21 17:31:33
 * @author gewuyou
 */
class DataDictionaryException(responseInformation: ResponseInformation) : GlobalException(responseInformation)