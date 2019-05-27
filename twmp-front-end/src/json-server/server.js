const jsonServer = require('json-server')
const server = jsonServer.create()
const db = require('./db')
const middlewares = jsonServer.defaults()
server.use(middlewares)
server.use((req, res, next) => {
  req.method = 'GET' // 强制将发送的POST改为GET方法，便于JSON-SERVER直接丢弃POST提交的数据，返回.json中的数据
  next()
})
server.use(jsonServer.rewriter({
  '/authorize/login': '/user_login',
  '/authorize/logout': '/user_logout',
  '/user/userList': '/user_userList',
  '/ef/system/getUserInfo': '/user_config',
  '/ef/monitor/monitorList': '/monitor_list',
  '/ef/monitor/alarmList': '/monitor_alarmList',
  '/ef/systemManagement/departmentList': '/department_list',
  '/ef/systemManagement/roleQuery': '/role_list',
  '/ef/systemManagement/systemParameters': '/parameter_list',
  '/ef/systemManagement/dictTypes': '/dict_types',
  '/ef/systemManagement/roleQuery': '/role_list',
  '/ef/systemManagement/systemParameters': '/parameter_list',
  '/ef/systemManagement/userInfoQuery': '/user_list',
  '/ef/user/messageQuery': '/message_list',
  '/ef/systemManagement/paperworkQuery': '/paperwork_list',
  '/ef/information/personList': '/person_list',
  '/ef/chart/queryAlarm': '/query_alarm',
  '/ef/chart/queryAlarmType': '/query_alarm_type',
  '/ef/task/monitorTaskList': '/task_list',
  '/ef/task/approvalList': '/task_approve_list'
}))
server.use(jsonServer.bodyParser)
server.use(jsonServer.router(db))
server.listen(3000, () => {
  console.log('JSON Server is running...')
})