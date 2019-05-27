let webBaseUrl = ''
switch(process.env.NODE_ENV) {
  case 'development':
    webBaseUrl = "http://localhost:8080/ef"
    break
  case 'production':
    webBaseUrl = "http://localhost:1003/ef"
    break
}
export default webBaseUrl