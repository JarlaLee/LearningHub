"""
    协程：是一种单线程协作并发模式。比较适合IO密集型任务比如网络请求等。消耗内存低
    创建协程
"""

import asyncio

# 定义协程函数

async def printCoroutine(name):
    print(f"协程 {name} 开始")
    await asyncio.sleep(2)      # 这里模拟程序进入IO操作，需要执行比较长的一段时间。
    print(f"协程 {name} 结束")

# main 方法使用 async 是为了让main 函数能够使用 await
# 来等待其他协程的执行结果，并且通过 asyncio.run(main()) 启动事件循环
# async def 定义的函数是一个 协程函数（coroutine function），调用它会返回一个 协程对象（coroutine object）
async def main():
    # 并发执行两个 协程
    await asyncio.gather(
        printCoroutine("Coroutine-1"),
        printCoroutine("Coroutine-2")
    )

# 启动事件循环
asyncio.run(main())