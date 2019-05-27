module.exports = {
  devServer: {
    proxy: 'http://127.0.0.1:3000'
    // proxy: 'http://localhost:1003'
  },
  transpileDependencies: ['ceiec.maps'] // polyfill编译maps插件确保兼容>IE10
}