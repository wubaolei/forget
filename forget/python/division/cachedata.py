import suds
from suds import client
from division.pcphone import *
import json

class CacheService():
    # 全局变量
    cachedata = {}

    def __init__(self):
        print("---------产生一个对象初始化一次-----------")

    def getMenuData(self,key):
        # 判断这个key在不在 缓存中
        if key in self.cachedata:
            print("缓存中有数据")
            return self.cachedata[key]
        else:
            print("缓存中没有数据")
            url = "http://127.0.0.1:8989/userdataservice/user?wsdl"
            service = suds.client.Client(url)
            data = service.service.queryMenuData()
            # 注意点：从任何地方获取数据 一定要判断数据的数据类型
            print("data-->",data)
            print("data类型为-->",type(data))

            # 因为获取的数据为 string，转换为json格式
            jsondata = json.loads(data)

            self.cachedata[key] = jsondata
            return jsondata




