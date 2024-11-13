/**
 * @description pinia 持久化参数配置
 * @param {String} key 存储到持久化的 name
 * @param {Array} paths 需要持久化的 state name
 * @param {Object} storage 存储方式，默认 localStorage
 * @return persist
 * */
const piniaPersistConfig = (key: string, paths?: string[], storage: Storage = localStorage) => {
  return {
    key,
    storage: storage,
    // storage: sessionStorage,
    pick: paths
  }
}

export default piniaPersistConfig
