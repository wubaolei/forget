import tornado.web
import tornado.ioloop
from suds import client
from division.cachedata import *


class IndexHandler(tornado.web.RequestHandler):
    def get(self):
        print("接收get请求")
        self.render("index.html")

class UserHandler(tornado.web.RequestHandler):
    def post(self):
        print("接收用户的post请求")

        method = self.get_argument("action")

        if method == "login":
            username = self.get_argument("username")
            password = self.get_argument("password")

            url = "http://127.0.0.1:8989/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            msg = service.service.checkuser(username,password)

            # 这里可以获取到请求头 意思就是 可以看出是pc 还是 mobile访问
            headsinfo = self.request.headers
            # print(headsinfo)

            hinfo = headsinfo["User-Agent"]
            print("info-->",hinfo)

            val = checkPCorMobile(hinfo)
            if msg == 1:
                # 如果登录成功，那么就要跳转页面 并在页面中加载数据
                # 因为这个是菜单 不会经常发生相应的变化，所以考虑应用缓存

                print(val)

                # 调用类中的方法先要获取这个类
                # 这里是 类名 点 方法
                jsondata = CacheService().getMenuData("tmenu")

                # 判断是PC 还是 Mobile发起的请求
                if val == "PC":
                    self.render("login.html",contentdata=jsondata)
                else:
                    #json数据格式
                    self.finish({"msg":"success","contentdata":jsondata})
            else:
                self.finish({"msg":"fail"})



class AntvHandler(tornado.web.RequestHandler):
    def get(self):
        method = self.get_argument("action")
        url = "http://127.0.0.1:8989/userdataservice/user?wsdl"
        service = suds.client.Client(url)
        if method == "two":
            msg = service.service.queryTwoData()
            jsondata = json.loads(msg)
            self.finish({"contentdata":jsondata})
        else:
            msg = service.service.queryThreeData()
            jsondata = json.loads(msg)
            self.finish({"contentdata":jsondata})




# 因为这是tornado框架 与flask框架有一定的区别 这里的templates这个文件夹要在这里进行配置出来
setings = {"template_path":"templates",
           "static_path":"static"}

app = tornado.web.Application([(r'/',IndexHandler),
                               (r'/user',UserHandler),
                               (r'/antvdata',AntvHandler)],**setings)

if __name__=="__main__":
    app.listen(8899)
    tornado.ioloop.IOLoop.current().start()