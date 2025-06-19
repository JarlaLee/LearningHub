"""
    协程之间通信使用 asyncio.Queue
    这是一种异步队列, 用于在协程间安全的传递数据, 无需锁Lock
"""

# 生产 - 消费模型
import asyncio
import random

async def producer(queue):
    for i in range(5):
        item = f"item-{i}"
        print(f"生产者生成: {item}")
        await queue.put(item)
        await asyncio.sleep(random.random())  # 模拟异步 I/O

async def consumer(queue):
    while True:
        item = await queue.get()             # 挂起协程，直到有数据可以使用
        print(f"消费者处理: {item}")
        await asyncio.sleep(random.random()) # 模拟异步 IO
        queue.task_done()                    # 标记任务完成

async def main():
    # 初始化队列

    queue = asyncio.Queue()

    producer_task = asyncio.create_task(producer(queue))
    consumer_task = asyncio.create_task(consumer(queue))

    await producer_task # 等待 producer_task 完成任务（一共5个循环），然后才会执行后面的代码
    await queue.join()   # 阻塞, 直到队列中的所有数据项被消费者处理，也即是所有的queue.task_done() 被调用。
    # 每次生产者调用 queue.put(item) 时，队列内部的 未完成任务计数器 会增加 1
    # 每次消费者调用 queue.get() 并处理完数据后，调用 queue.task_done()，未完成任务计数器减少 1。
    # 当未完成任务计数器归零时，queue.join() 会解除阻塞，程序继续执行
    consumer_task.cancel() # 取消消费者任务
    # 消费者协程（consumer(queue)）是一个无限循环（while True:），会持续从队列中取出数据并处理。
    # 当生产者完成所有数据生成且队列为空后，消费者会一直等待 queue.get()（此时队列为空，协程会挂起）
    # 如果不手动取消消费者任务，程序会永远阻塞在 queue.get() 上，无法退出。

asyncio.run(main())    
