# 传统线程和线程通信
线程就是操作系统分配给CPU的基本执行单位，多核处理器来说可以并发的执行任务。Python中使用 threading 模块实现多线程的。
**线程创建**
import threading
import time

def worker(name):
    print(f"线程 {name} 开始")
    time.sleep(2)  # 模拟耗时操作
    print(f"线程 {name} 结束")

# 创建并启动线程
thread1 = threading.Thread(target=worker, args=("Thread-1",))
thread2 = threading.Thread(target=worker, args=("Thread-2",))
thread1.start()
thread2.start()

# 等待线程结束
thread1.join()
thread2.join()

**线程通信**
线程间的通信是通过共享变量 + 锁来实现的。需要 Lock 来避免竞争条件。
import threading

counter = 0
lock = threading.Lock()

def increment():
    global counter
    for _ in range(100000):
        with lock:
            counter += 1

thread1 = threading.Thread(target=increment)
thread2 = threading.Thread(target=increment)
thread1.start()
thread2.start()
thread1.join()
thread2.join()

print(f"最终计数器值: {counter}")  # 输出应为 200000

线程通信存在的局限
- Python 的全局解释器锁（GIL）导致多线程无法真正并行执行 CPU 密集型任务。
- 需要手动管理锁，共享变量，会出现死锁或者竞争条件的情况。


# 协程
又被叫做微线程，英文 Coroutine

协程看起来像多线程。但是和多线程相比，协程有很高的执行效率。因为协程切换的是子程序，而不是线程切换。因此，没有线程切换的开销，和多线程相比，线程的数量越多，协程的优势就更加明显。
协程不需要多线程的锁机制，因为只有一个线程，不存在同时写变量冲突，协程中控制资源不加锁，只需要判断状态。

协程是一个线程，如何利用多核CPU呢？
- 最简单的方法是多进程+协程，既充分利用多核，又充分发挥协程的高效率，可获得极高的性能。

# asyncio
是Python 3.4版本引入的标准库，直接内置了对异步IO的支持。
为了简化并更好地标识异步IO，从Python 3.5开始引入了新的语法async和await，可以让coroutine的代码更简洁易读。
使用 asyncio 库的时候，只需要使用 async 把一个函数变成 coroutine 类型，然后把这个 async 函数放在 asynico.run() 中运行。
await 指令，就是当前中断去执行其他指令。

import asyncio

async def hello():
    print("Hello world!")
    # 异步调用asyncio.sleep(1):
    await asyncio.sleep(1)
    print("Hello again!")

asyncio.run(hello())

把asyncio.sleep(1)看成是一个耗时1秒的IO操作，在此期间，主线程并未等待，而是去执行EventLoop中其他可以执行的async函数了，因此可以实现并发执行。
在 async 函数内部，通过 await asyncio.gather() 可以并发的执行若干 async 函数。