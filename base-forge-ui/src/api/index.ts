import axios, {
  type AxiosInstance,
  type AxiosRequestConfig,
  type AxiosResponse,
  type InternalAxiosRequestConfig
} from 'axios'

import type { ResultData } from '@/models/entity/api'
import { ElMessage } from 'element-plus'
import { useAuthStore } from '@/stores/modules/auth'

const config = {
  // 默认地址请求地址，可在 .env.** 文件中修改
  baseURL: import.meta.env.VITE_API_URL as string,
  // 设置超时时间
  timeout: 30000,
}

class Http {
  private static axiosInstance: AxiosInstance

  constructor(config: AxiosRequestConfig) {
    Http.axiosInstance = axios.create(config)
    this.interceptorsRequest()
    // this.interceptorsResponse()
  }

  /**
   * 发送 get 请求
   * @param url 请求地址
   * @param successCallback 成功回调函数
   * @param failureCallback 失败回调函数
   * @param errorCallback 错误回调函数
   * @param params 请求参数
   * @param _other 额外参数(配置项)
   */
  public async get<T>(
    url: string,
    params?: object,
    successCallback: (result: ResultData<T>) => void = this
      .defaultSuccessCallback,
    failureCallback: (result: ResultData<T>) => void = this
      .defaultFailureCallback,
    errorCallback: (result: ResultData<T>) => Promise<never> = this
      .defaultErrorCallback,
    _other = {},
  ) {
    await Http.axiosInstance
      .get<ResultData<T>>(url, {
        params,
        ..._other,
      })
      .then((response: AxiosResponse<ResultData<T>>) => {
        const { data } = response
        if (data.success) {
          successCallback(data)
        } else {
          failureCallback(data)
        }
      })
      .catch(errorCallback)
  }

  /**
   * 发送 post 请求
   * @param url 请求地址
   * @param successCallback 成功回调函数
   * @param failureCallback 失败回调函数
   * @param errorCallback 错误回调函数
   * @param params 请求参数
   * @param _other 额外参数(配置项)
   */
  public async post<T>(
    url: string,
    successCallback: (result: ResultData<T>) => void,
    failureCallback: (result: ResultData<T>) => void = this
      .defaultFailureCallback,
    errorCallback: (result: ResultData<T>) => Promise<never> = this
      .defaultErrorCallback,
    params?: object | string,
    _other = {},
  ) {
    await Http.axiosInstance
      .post(url, params, _other)
      .then((response: AxiosResponse<ResultData<T>>) => {
        const { data } = response
        if (data.success) {
          successCallback(data)
        } else {
          failureCallback(data)
        }
      })
      .catch(errorCallback)
  }

  /**
   * 发送 put 请求
   * @param url 请求地址
   * @param successCallback 成功回调函数
   * @param failureCallback 失败回调函数
   * @param errorCallback 错误回调函数
   * @param params 请求参数
   * @param _other 额外参数(配置项)
   */
  public async put<T>(
    url: string,
    successCallback: (result: ResultData<T>) => void,
    failureCallback: (result: ResultData<T>) => void = this
      .defaultFailureCallback,
    errorCallback: (result: ResultData<T>) => Promise<never> = this
      .defaultErrorCallback,
    params?: object,
    _other = {},
  ) {
    await Http.axiosInstance
      .put(url, params, _other)
      .then((response: AxiosResponse<ResultData<T>>) => {
        const { data } = response
        if (data.success) {
          successCallback(data)
        } else {
          failureCallback(data)
        }
      })
      .catch(errorCallback)
  }

  /**
   * 发送 delete 请求
   * @param url 请求地址
   * @param successCallback 成功回调函数
   * @param failureCallback 失败回调函数
   * @param errorCallback 错误回调函数
   * @param params 请求参数
   * @param _other 额外参数(配置项)
   */
  public async delete<T>(
    url: string,
    successCallback: (result: ResultData<T>) => void,
    failureCallback: (result: ResultData<T>) => void = this
      .defaultFailureCallback,
    errorCallback: (result: ResultData<T>) => Promise<never> = this
      .defaultErrorCallback,
    params?: string | object,
    _other = {},
  ) {
    await Http.axiosInstance
      .post(url, params, _other)
      .then((response: AxiosResponse<ResultData<T>>) => {
        const { data } = response
        if (data.success) {
          successCallback(data)
        } else {
          failureCallback(data)
        }
      })
      .catch(errorCallback)
  }

  // //响应拦截器
  // private interceptorsResponse() {
  //   Http.axiosInstance.interceptors.response.use(
  //     async (response: AxiosResponse) => {
  //       const { data } = response
  //       return data
  //     },
  //     (error: string) => {
  //       return Promise.reject(error)
  //     }
  //   )
  // }

  /**
   * 默认的成功回调函数
   * @param response 请求响应
   * @private
   */
  private defaultSuccessCallback<T>(response: ResultData<T>) {
    ElMessage.success(response.msg)
  }

  /**
   * 默认的错误回到函数
   * @param response
   * @private
   */
  private defaultErrorCallback<T>(response: ResultData<T>): Promise<never> {
    console.log(response.msg)
    ElMessage.error(response.msg)
    return Promise.reject(response.msg)
  }

  /**
   * 默认的失败回调函数
   * @param response
   * @private
   */
  private defaultFailureCallback<T>(response: ResultData<T>) {
    console.log(response.msg)
    ElMessage.warning(response.msg)
  }

  /**
   * 请求拦截器
   * @private
   */
  private interceptorsRequest() {
    Http.axiosInstance.interceptors.request.use(
      (config: InternalAxiosRequestConfig) => {
        // 为请求头对象，添加 Token 验证的 Authorization 字段
        const authStore = useAuthStore()
        config.headers.Authorization = authStore.token
        return config
      },
      (error: string) => {
        return Promise.reject(error)
      },
    )
  }
}

const http = new Http(config)
export default http
