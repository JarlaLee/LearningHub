"""
    大型项目中，需要为不同的模块或者组件创建独立的日志记录器。
    以下方式实现。
"""

# Python 的代码顺序是按执行顺序决定的，类和方法定义后才能被调用
# 代码是从上到下依次执行的。这意味着你需要在调用函数、类或变量之前定义它们

import logging

class MyFilter(logging.Filter):
    def filter(self, record):
        return record.levelno == logging.ERROR

logger = logging.getLogger("my_logger")
logger.setLevel(logging.DEBUG)

# 创建文件处理器
file_handler = logging.FileHandler('C:\\Users\\Jiale\\AAA-Private\\Project\\LearningHub\\Python\\logging\\log\\app.log', mode='a', encoding='utf-8')
file_handler.setLevel(logging.DEBUG)

# 创建控制台处理器
console_handler = logging.StreamHandler()
console_handler.setLevel(logging.INFO)

# 设置日志格式
formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
file_handler.setFormatter(formatter)
console_handler.setFormatter(formatter)

# 将处理器添加到日志记录器
logger.addHandler(file_handler)
logger.addHandler(console_handler)
logger.addFilter(MyFilter())

# 记录日志
logger.debug("这是一条debug信息")
logger.info("这是一条info信息")
logger.error("这是一条ERROR信息")

# TODO
# 使用过滤器控制哪些日志需要被记录。


# 日志轮转
# 当日志文件过大时，可以使用 RotatingFileHandler 或 TimedRotatingFileHandler 实现日志轮转：