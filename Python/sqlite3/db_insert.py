"""
    数据库创建之后，就可以执行相关数据库操作, Insert,
"""
import sqlite3

conn = sqlite3.connect('db/test.db') # 连接已经存在的数据库，获取数据库对象。
c = conn.cursor()
print("成功打开数据库")

# 执行插入操作
c.execute("INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) \
            VALUES (1, 'Paul', 32, 'California', 20000.00)")

c.execute("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) \
      VALUES (2, 'Allen', 25, 'Texas', 15000.00 )")

c.execute("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) \
      VALUES (3, 'Teddy', 23, 'Norway', 20000.00 )")

c.execute("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) \
      VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 )")

# 提交事务
conn.commit()
print("数据已经被成功插入。")
conn.close()