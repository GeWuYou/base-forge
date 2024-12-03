import { ElMessage } from 'element-plus'

/**
 * @description 正则表达式，用于匹配hex颜色值
 */
const hexReg = /^#?[0-9A-Fa-f]{6}$/

/**
 * @description hex颜色转rgb颜色
 * @param  str 颜色值字符串
 * @returns 返回处理后的颜色值
 */
export function hexToRgb(str: string): number[] {
  if (!hexReg.test(str)) {
    ElMessage.warning('输入错误的hex')
    return []
  }
  const matchResult = str
    // 去掉#号
    .replace('#', '')
    // 将每两个十六进制数字分割成数组
    .match(/../g)
  // 如果没有匹配到有效的十六进制对，则返回空数组
  if (!matchResult) {
    ElMessage.warning('输入错误的hex')
    return []
  }
  return (
    matchResult
      // 使用 map 方法将每个十六进制数字转换成十进制数字
      .map(v => parseInt(v, 16))
  )
}

/**
 * @description rgb颜色转Hex颜色
 * @param  r 代表红色
 * @param  g 代表绿色
 * @param  b 代表蓝色
 * @returns  返回处理后的颜色值
 */
export function rgbToHex(r: number, g: number, b: number): string {
  // 验证输入值是否在 0 到 255 的范围内
  if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
    throw new Error('RGB值必须在0到255之间')
  }
  const hexArr = [
    r.toString(16).padStart(2, '0'),
    g.toString(16).padStart(2, '0'),
    b.toString(16).padStart(2, '0'),
  ]
  return `#${hexArr.join('')}`
}

/**
 * @description 加深颜色值
 * @param  color 颜色值字符串
 * @param  level 加深的程度，限0-1之间
 * @returns 返回处理后的颜色值
 */
export function getDarkColor(color: string, level: number): string | void {
  if (!hexReg.test(color)) {
    ElMessage.warning('输入错误的hex颜色值')
    return
  }
  const [r, g, b] = hexToRgb(color)
  // 确保颜色值范围在0到255之间
  const darkenedColor = [
    Math.max(0, Math.min(255, Math.round(r * (1 - level) + 20.5 * level))),
    Math.max(0, Math.min(255, Math.round(g * (1 - level) + 20.5 * level))),
    Math.max(0, Math.min(255, Math.round(b * (1 - level) + 20.5 * level))),
  ]
  return rgbToHex(darkenedColor[0], darkenedColor[1], darkenedColor[2])
}

/**
 * @description 变浅颜色值
 * @param color 颜色值字符串
 * @param  level 加深的程度，限0-1之间
 * @returns 返回处理后的颜色值
 */
export function getLightColor(color: string, level: number): string | void {
  if (!hexReg.test(color)) {
    ElMessage.warning('输入错误的hex颜色值')
    return
  }
  const [r, g, b] = hexToRgb(color)
  // 计算变浅的颜色值并确保在0到255之间
  const lightenedColor = [
    Math.max(0, Math.min(255, Math.round(255 * level + r * (1 - level)))),
    Math.max(0, Math.min(255, Math.round(255 * level + g * (1 - level)))),
    Math.max(0, Math.min(255, Math.round(255 * level + b * (1 - level)))),
  ]
  return rgbToHex(lightenedColor[0], lightenedColor[1], lightenedColor[2])
}
