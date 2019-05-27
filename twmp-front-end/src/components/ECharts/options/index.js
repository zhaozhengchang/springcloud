import _ from 'lodash'
async function loadChartOption (type, callback) {
  let optionImp = await import(`./${type}`)

  let option = _.cloneDeep(optionImp.default)

  if (callback) {
    callback(option)
  }

  return option
}
export default loadChartOption