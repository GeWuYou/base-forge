export interface ResultTable<T> {
  /** 总条目数 */
  count: number
  /** 列表数据 */
  rows: T[]
}

export interface Department {
  id: number
  parentId: number
  name: string
  sort: number
  isEnable: number
  createdAt: string
  children?: Department[]
}

// 请求响应参数（不包含data）
export interface Result {
  success: boolean
  code: number
  msg: string
}

// 请求响应参数（包含data）
export interface ResultData<T> extends Result {
  data: T
}



