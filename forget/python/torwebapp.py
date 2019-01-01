import tornado.web
import tornado.ioloop

# python    面向对象的语法
class IndexHandler(tornado.web.RequestHandler):
    # self 相当于java中的this 当前对象
    def get(self):
        print("接收get请求")
        self.write("好看的皮囊千篇一律");

# 绑定一个舰监听接口，与内网穿透保持一致
# 包含路由  请求的动作名对应着一个类
app = tornado.web.Application([(r'/',IndexHandler)])

if __name__=="__main__":
    app.listen(8100)
    #启动程序 开始监听端口的连接
    tornado.ioloop.IOLoop.current().start()