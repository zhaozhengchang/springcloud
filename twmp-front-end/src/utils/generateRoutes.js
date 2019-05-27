import lazyImport from './lazyImport'

export function generateRoutes(authority) {
  let dynamicRoutes = []
  if (authority.length > 0) {
    authority.forEach((v)=>{
      let menu = Object.assign({}, v)
      menu.path = menu.authorityUrl
      menu.title = menu.authorityName
      menu.component = lazyImport(menu.authorityComponent)
      menu.children = []
      if (v.childrenList && v.childrenList.length > 0) {
        menu.children = generateRoutes(v.childrenList)
      }
      dynamicRoutes.push(menu)
    })
  }
  return dynamicRoutes
}
