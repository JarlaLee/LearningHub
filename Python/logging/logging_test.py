# 基本概念
# Logger
# Handler
# Formatter
# Level

import logging

# 在实际的项目中，一般将Logger封装成一个函数来使用。
# 然后使用这个函数进行一些打印。

def file_log(logger_name, file_path):
    # 创建一个 Logger
    logger = logging.getLogger(logger_name)
    logger.setLevel(logging.DEBUG)  # 设置Logger的级别

    # 创建一个handler, 用于写入日志文件。
    handler = logging.FileHandler(file_path)
    handler.setLevel(logging.DEBUG) # handdle 也设置一个等级, 只会debug 才能写入文件。这样和 logger有一些差别

    formatter = logging.Formatter(
        fmt = '%(asctime)s - %(name)s - %(levelname)s - %(message)s',
        datefmt= '%Y-%m-%d %H:%M:%S',
    )
    handler.setFormatter(formatter)

    # 给logger添加handler
    logger.addHandler(handler)

    return logger

if __name__ == '__main__':
    logger = file_log('test_logger', 'C:\\Users\\Jiale\\AAA-Private\\Project\\LearningHub\\Python\\logging\\log\\test.log')
    logger.debug('This is a debug message')
    logger.info('This is an info message')
    logger.warning('This is a warning message')
    logger.error('This is an error message')


