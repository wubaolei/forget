# User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36
import re


def checkPCorMobile(info):
    phoneheads = ["android","mac","windowphone"]

    for data in phoneheads:
        # search() 查询 info里面是否是data    lower()转换小写
        val = re.search(data,info.lower())
        if val is None:
            return "PC"
        else:
            return "Mobile"