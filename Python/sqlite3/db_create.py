"""
    sqlite 数据库包括几个操作，增删改查
    其他：优化，搜索查找优化等
"""
import sqlite3

# 连接一个数据库 test.db；如果数据库不存在, 它就会被创建。最后返回一个数据库对象。
conn = sqlite3.connect('db/test.db')

print("数据库打开")

# 下面在连接并创建的数据库对象中创建一个表
c = conn.cursor()
c.execute('''
            CREATE TABLE COMPANY
                   (ID INT PRIMARY KEY     NOT NULL,
                   NAME           TEXT    NOT NULL,
                   AGE            INT     NOT NULL,
                   ADDRESS        CHAR(50),
                   SALARY         REAL);
          ''')
print("数据库表创建成功")
conn.commit()   # 提交当前数据库事务。commit 将更改永久保存到数据库中。如果不调用commit，所作的更改不会被保存。
conn.close()    # 关闭与数据库的连接，释放资源