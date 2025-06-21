"""
    日志记录（logging）是一种非常重要的工具。
    与简单的 print 语句相比，logging 模块更加灵活和强大，能够满足不同场景下的日志需求。
    logging 是python自带一个模块
"""

import logging

# 日志级别用于控制日志的详细程度, 包括DEBUG, INFO, WARNING, ERROR, CRITICAL
# logging.basicConfig(level=logging.INFO)

# 自定义日志输出
# logging.basicConfig(
#     level=logging.DEBUG,
#     format='%(asctime)s - %(filename)s - %(levelname)s - %(message)s',
#     datefmt='%Y-%m-%d %H:%M:%S'
# )

# 默认日志会输出到控制台，如果你希望保存到文件中。
# logging.basicConfig(
#     level=logging.DEBUG,          # 指定日志级别，只有高于或等于此级别的日志消息才会被处理。
#     format='%(asctime)s - %(filename)s - %(levelname)s - %(message)s',
#     datefmt='%Y-%m-%d %H:%M:%S',
#     filename = "C:\\Users\\Jiale\\AAA-Private\\Project\\LearningHub\\Python\\logging\\log\\app.log",
#     encoding="utf-8",   # 设置编码格式
#     filemode="a"        # 指定文件打开模式，默认为 'a'（追加模式）。如果指定为 'w'，则每次运行程序时会清空之前的日志内容。
# )

# def basicConfig(**kwargs): 表示接收任意数量的关键字参数，以字典的形式传递给函数。

# logging.debug("这是一个调试信息!")
# logging.info("这是一个普通信息!")
# logging.warning("这是一个警告信息!")
# logging.error("这是一个错误信息!")
# logging.critical("这是一个严重错误信息!")

# 使用 FileHandler 设置日志文件编码

# 创建日志记录器
logger = logging.getLogger('my_logger')
logger.setLevel(logging.DEBUG)              # 设置级别

# 创建文件处理器, 并设置编码为utf-8
file_handler = logging.FileHandler('C:\\Users\\Jiale\\AAA-Private\\Project\\LearningHub\\Python\\logging\\log\\app.log', mode='a', encoding='utf-8')
file_handler.setLevel(logging.DEBUG)

# 创建日志格式
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
file_handler.setFormatter(formatter)

# 将文件处理器添加到日志记录器
logger.addHandler(file_handler)

# 输出日志信息
logger.debug('这是一个 debug 消息')
logger.info('这是一个 info 消息')
logger.warning('这是一个 warning 消息')
logger.error('这是一个 error 消息')
logger.critical('这是一个 critical 消息')